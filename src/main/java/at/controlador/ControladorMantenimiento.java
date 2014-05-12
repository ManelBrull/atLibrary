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
import at.modelo.entidades.excepciones.CampoRequeridoException;
import at.vista.IMantenimiento;
import at.vista.interfaz.Recursos;

public abstract class ControladorMantenimiento <T extends ICrud<T> & IEsFiltro> implements IControladorMantenimiento {
	/**
	 * Interfaz que se controla
	 */
	protected IMantenimiento mantenimiento;
	/**
	 * Referencia a los accesos directos
	 */
	protected Listener shortcut;
	/**
	 * Objeto seleccionado
	 */
	protected T entidadSeleccionado;
	/**
	 * Los resultados del filtro
	 */
	protected ArrayList <T> filtro;
	
	/**
	 * Constructor. Se ejecuta desde la itnerfaz que se controla
	 * @param mant
	 */
	public ControladorMantenimiento(IMantenimiento mant){
		this.mantenimiento = mant;
	}
	
	public void inicializar() {
		addShortcuts();
		visibilidadBtn();
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
				mantenimiento.openInformation(
						"Informacion", 
						"Se ha creado el elemento satisfactoriamente"
						);
				entidadSeleccionado = usr;
		}
		} catch (HibernateException he){
			mantenimiento.openError(
					"Error",
					"No se ha podido guardar el elemento en la base de datos"
					);
		} catch (CampoRequeridoException ex){
			mantenimiento.openError(
					"Error",
					"No se ha podido guardar el elemento porque hay campos requeridos"
					+ " que no se han dado un valor válido");
		}
	}

	public void grabar() {
		try {
			T usr = creaObjeto();
			if (usr != null){
				if(entidadSeleccionado.equals(usr)){
					mantenimiento.openInformation(
							"Error",
							"No se puede editar porque no se han realizado cambios");
				}
				else{
					entidadSeleccionado.update(usr);
					mantenimiento.getBtnBuscar().notifyListeners(SWT.Selection, new Event());
					borrar();
					mantenimiento.openInformation(
							"Información",
							"Se ha editado satisfactoriamente");
				}
			}
		} catch(HibernateException he) {
			mantenimiento.openError(
					"Error",
					"No se ha podido editar");
		} catch (CampoRequeridoException ex){
			mantenimiento.openError(
					"Error",
					"No se ha podido guardar el elemento porque hay campos requeridos"
							+ " que no se han dado un valor válido");
		}
	}



	public void eliminar() {
		if(entidadSeleccionado == null){
			mantenimiento.openError(
					"Error",
					"No ha seleccionado un elemento para eliminar");
		}
		else{
			int result = mantenimiento.openQuestion(
					"Eliminar",
					"Va a eliminar el elemento seleccionada"
							+". ¿Desea continuar?",
					new String[]{"Si", "No"}
					);
			
			if(result == 0){
				try {
					entidadSeleccionado.delete();
					entidadSeleccionado = null;
					borrar();
					visibilidadBtn();
					mantenimiento.openInformation(
							"Informacion",
							"El elemento seleccionado se ha borrado satisfactoriamente "
							);
					mantenimiento.getBtnBuscar().notifyListeners(SWT.Selection, new Event());
				} catch(HibernateException he){
					mantenimiento.openError(
							"Error",
							"No se ha podido eliminar la actuacion");
				} 
			}
		}
		
	}

	public void salir() {
		if(comprobarCambiosObjetoActivo()){
			int result = mantenimiento.openQuestion(
					"Cambios", 
					"Has cambiado datos del objeto. ¿Desea grabarlos?", 
					new String[]{"Si", "No"});
			
			if(result == 0){
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
			mantenimiento.openError(
					"Error",
					"Ha ocurrido un error en la base de datos");
		}
	}
	/**
	 * Debe obtenerse un iterador que devuelva todos los resultados
	 * Obtenidos al buscar mediante el filtro. Se ejecuta desde buscar.
	 * No hay que olvidar de crear el método en la entidad correspondiente
	 * y de pasar los parametros necesarios para filtrar los resultados
	 * de la base de datos.
	 * 
	 * Generalmente, el código que contiene será parecido a esto
	 * 
	 * Iterator <T> iter = new T().getFiltro();
	 * 
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
	/**
	 * Se ejecuta cuando se ha seleccionado un elemento desde la interfaz
	 * y carga los valores en los widgets de al interfaz
	 */
	public abstract void rellenarInterfaz();

	public void borrar() {
		entidadSeleccionado = null;
		visibilidadBtn();
		mantenimiento.getTable().removeAll();
		filtro = new ArrayList<T>();
		borrarInterfaz();
	}
	/**
	 * Se ejecuta cuando se selecciona el boton borrar y tiene que devolver el
	 * valor de los widgets de la interfaz a su estado inicial.
	 */
	public abstract void borrarInterfaz();
	
	/**
	 * Crea un objeto leyendo los datos de la interfaz.
	 * IMPORTANTE!! NO TIENE QUE GUARDARLO EN LA BASE DE DATOS
	 * Lanza la excepción si algún campo requerido no se ha introducido
	 * 
	 * @return
	 * @throws CampoRequeridoException
	 */
	public abstract T creaObjeto() throws CampoRequeridoException;
	
	/**
	 * Crea un objeto leyendo los datos de la interfaz.
	 * IMPORTANTE!! NO TIENE QUE GUARDARLO EN LA BASE DE DATOS
	 * Si algo va mal devuelve null
	 * @return
	 */
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
