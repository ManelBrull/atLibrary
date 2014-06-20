package at.controlador;

/**
 * 
 * @author brullp
 * 
 * Interfaz de un controlador de una clase de mantenimiento
 * 
 */
public interface IControladorMantenimiento extends IControlador {
	/**
	 * Lee la información de la interfaz y crea un nuevo objeto
	 */
	public void nuevo();
	/**
	 * Edita el objeto actual con la información introducida en la itnerfaz
	 */
	public void grabar();
	/**
	 * Elimina el objeto seleccionado de la base de datos
	 */
	public void eliminar();	
	/**
	 * Busca los elementos de acuerdo a un filtro elegido por nosotros
	 */
	public void buscar();
	/**
	 * Acciones a ejecutar cuando se selecciona un elemento del filtro
	 * o de la table
	 */
	public void elementoFiltroSeleccionado();
	/**
	 * Resetea la interfaz a su estado original
	 */
	public void borrar();
	/**
	 * Indica si el objeto actual ha sufrido cambios
	 * @return
	 */
	public boolean comprobarCambiosObjetoActivo();
	
}
