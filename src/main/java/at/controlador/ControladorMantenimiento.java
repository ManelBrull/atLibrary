package at.controlador;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableItem;
import org.hibernate.HibernateException;

import at.modelo.entidades.ICrud;
import at.modelo.entidades.IEsFiltro;
import at.vista.IMantenimiento;
import at.vista.interfaz.Recursos;

public abstract class ControladorMantenimiento <T extends ICrud<T> & IEsFiltro> {
	protected IMantenimiento mantenimiento;
	protected Listener shortcut;
	protected T entidadSeleccionado;
	protected ArrayList <T> filtro;
	
	public void inicializar() {
		addShortcuts();
		visibilidadBtn();
	}
	
	public ControladorMantenimiento(IMantenimiento mant){
		this.mantenimiento = mant;
		
	}
	
	public void addShortcuts() {
		shortcut = new Listener(){
			@Override
			public void handleEvent(Event e) {
				if(((e.stateMask & SWT.CTRL) == SWT.CTRL) && (e.keyCode == 'f')){
					mantenimiento.getBtnBuscar().notifyListeners(SWT.Selection, new Event());
					System.out.println("Buscar");
				}
				if(((e.stateMask & SWT.CTRL) == SWT.CTRL) && (e.keyCode == 'n')){
					mantenimiento.getBtnNuevo().notifyListeners(SWT.Selection, new Event());
				}
				if(((e.stateMask & SWT.CTRL) == SWT.CTRL) && (e.keyCode == 'g')){
					mantenimiento.getBtnGrabar().notifyListeners(SWT.Selection, new Event());
				}
				if((((e.stateMask & SWT.ALT) == SWT.ALT) && (e.keyCode == SWT.F4)) || 
						(e.keyCode == SWT.ESC)){
					mantenimiento.getBtnSalir().notifyListeners(SWT.Selection, new Event());
				}
			}
		};
		mantenimiento.getShell().getDisplay().addFilter(SWT.KeyDown, shortcut);
		mantenimiento.getBtnBuscar().setToolTipText(Recursos.generarATorrentTooltipTextShortcutButton("Ctrl + f"));
		mantenimiento.getBtnNuevo().setToolTipText(Recursos.generarATorrentTooltipTextShortcutButton("Ctrl + n"));
		mantenimiento.getBtnGrabar().setToolTipText(Recursos.generarATorrentTooltipTextShortcutButton("Ctrl + g"));
		mantenimiento.getBtnSalir().setToolTipText(Recursos.generarATorrentTooltipTextShortcutButton("Alt + f4 OR esq"));
	}

	public void visibilidadBtn() {
		if(entidadSeleccionado == null){
			mantenimiento.getBtnGrabar().setEnabled(false);
			mantenimiento.getBtnEliminar().setEnabled(false);
		}else {
			mantenimiento.getBtnGrabar().setEnabled(true);
			mantenimiento.getBtnEliminar().setEnabled(true);
		}
		
	}

	public void nuevo() {
		try {
			T usr = creaObjeto();
			if (usr != null){
				usr.save();
				MessageDialog.openInformation(
						mantenimiento.getShell(),
						"Informacion",
						"Se ha creado el elemento satisfactoriamente");
				entidadSeleccionado = usr;
		}
		} catch (HibernateException he){
			MessageDialog.openError(
					mantenimiento.getShell(),
					"Error",
					"No se ha podido guardar el elemento en la base de datos");
		}
	}

	public void grabar() {
		T usr = creaObjeto();
		if (usr != null){
			if(entidadSeleccionado.equals(usr)){
				MessageDialog.openInformation(
						mantenimiento.getShell(),
						"Error",
						"No se puede editar porque no se han realizado cambios");
			}
			else{
				try{
					entidadSeleccionado.update(usr);
					mantenimiento.getBtnBuscar().notifyListeners(SWT.Selection, new Event());
					borrar();
					MessageDialog.openInformation(
							mantenimiento.getShell(),
							"Información",
							"Se ha editado satisfactoriamente");
				} catch(HibernateException he) {
					MessageDialog.openError(
							mantenimiento.getShell(),
							"Error",
							"No se ha podido editar");
				}
			}
		}
	}

	public void eliminar() {
		if(entidadSeleccionado == null){
			MessageDialog.openError(
					mantenimiento.getShell(),
					"Error",
					"No ha seleccionado un elemento para eliminar");
		}
		else{
			MessageDialog dialog = new MessageDialog(
					mantenimiento.getShell(), 
					"Eliminar", 
					null,
					"Va a eliminar el elemento seleccionada"
					+". ¿Desea continuar?",
					MessageDialog.QUESTION,
					new String[]{"Si", "No"},
					0 );
			if(dialog.open() == 0){
				try {
					entidadSeleccionado.delete();
					entidadSeleccionado = null;
					borrar();
					visibilidadBtn();
					MessageDialog.openInformation(
							mantenimiento.getShell(),
							"Informacion",
							"El elemento seleccionado se ha borrado satisfactoriamente "
							);
					mantenimiento.getBtnBuscar().notifyListeners(SWT.Selection, new Event());
				} catch(HibernateException he){
					MessageDialog.openError(
							mantenimiento.getShell(),
							"Error",
							"No se ha podido eliminar la actuacion");
				} 
			}
		}
		
	}

	public void salir() {
		if(comprobarCambiosObjetoActivo()){
			MessageDialog dialog = new MessageDialog(
					mantenimiento.getShell(), 
					"Cambios", 
					null,
					"Has cambiado datos del objeto. ¿Desea grabarlos?",
					MessageDialog.QUESTION,
					new String[]{"Si", "No"},
					0 );
			if(dialog.open() == 0){
				mantenimiento.getBtnGrabar().notifyListeners(SWT.Selection, new Event());
			}
		}
		mantenimiento.getShell().close();
	}

	public void buscar() {
		if(entidadSeleccionado != null){
			borrar();
		}
		mantenimiento.getTable().removeAll();
		filtro = new ArrayList <T>();
		try {
			Iterator <T> iter = getIteratorFiltro();
			while(iter.hasNext()){
				T u = iter.next();
				filtro.add(u);
				TableItem item = new TableItem(mantenimiento.getTable(), SWT.NONE);
				item.setText(u.toTable());
			}
		} catch (HibernateException he){
			MessageDialog.openError(
					mantenimiento.getShell(),
					"Error",
					"Ha ocurrido un error en la base de datos");
		}
	}
	/**
	 * Iterator <T> iter = new T().getFiltro();
	 * @return
	 */
	public abstract Iterator<T> getIteratorFiltro();
	

	public void elementoFiltroSeleccionado() {
		entidadSeleccionado = filtro.get(mantenimiento.getTable().getSelectionIndex());
		if(entidadSeleccionado != null){
			rellenarInterfaz();
			visibilidadBtn();
		}
	}
	public abstract void rellenarInterfaz();

	public void borrar() {
		entidadSeleccionado = null;
		visibilidadBtn();
		mantenimiento.getTable().removeAll();
		filtro = new ArrayList<T>();
		borrarInterfaz();
	}
	public abstract void borrarInterfaz();

	public abstract T creaObjeto();
	
	public abstract T creaObjetoSilencioso();
		
	public boolean comprobarCambiosObjetoActivo() {
		T usr = creaObjetoSilencioso();
		if (usr != null && entidadSeleccionado != null){
			if(entidadSeleccionado.equals(usr)){
				return false;
			}
			else{
				return true;
			}
		}
		else{
			return false;
		}
	}

	public void cerrarShell() {
		mantenimiento.getShell().getDisplay().removeFilter(SWT.KeyDown, shortcut);
	}
}
