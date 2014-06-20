package at.controlador;

import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.HibernateException;

import at.modelo.entidades.ICrud;
import at.modelo.entidades.IEsFiltro;
import at.modelo.entidades.excepciones.CampoRequeridoException;
import at.vista.IMantenimiento;
/**
 * 
 * @author ManelBrull
 *
 * @param <T> Entidad a la que hace referencia el controlador
 * 
 * Implementación de un controlador para una interfaz de mantenimiento, que 
 * efectúa sus acciones sobre una entidad que implemente ICrud y que este preparada para 
 * ser un filtro
 */
public abstract class ControladorMantenimiento <T extends ICrud<T> & IEsFiltro> implements IControladorMantenimiento {
	/**
	 * Interfaz que se controla
	 */
	protected IMantenimiento mantenimiento;
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
	/**
	 * Inicializa la interfaz
	 */
	public void inicializar() {
		visibilidadBtn();
	}

	/**
	 * Habilita o no los componentes en función de la entidad
	 * que se ha seleccionado 
	 */
	public void visibilidadBtn() {
		if(entidadSeleccionado == null){
			mantenimiento.setBtnGrabarEnabled(false);
			mantenimiento.setBtnEliminarEnabled(false);
		}else {
			mantenimiento.setBtnGrabarEnabled(true);
			mantenimiento.setBtnEliminarEnabled(true);
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
					mantenimiento.btnBuscarSelected();
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
					mantenimiento.btnBuscarSelected();
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
				mantenimiento.btnGrabarSelected();
			}
		}
		mantenimiento.cerrarDialog();
	}

	public void buscar() {
		if(entidadSeleccionado != null){
			borrar();
		}
		mantenimiento.vaciarTabla();
		filtro = new ArrayList <T>();
		try {
			Iterator <T> iter = getIteratorFiltro();
			while(iter.hasNext()){
				T u = iter.next();
				filtro.add(u);
				mantenimiento.anadirElemento(u.toTable());
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
		entidadSeleccionado = filtro.get(mantenimiento.elementoElegidoTabla());
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
		mantenimiento.vaciarTabla();
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
	
		
	public boolean comprobarCambiosObjetoActivo() {
		T usr;
		try {
			usr = creaObjeto();
		} catch (CampoRequeridoException ex){
			return false;
		}
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
}
