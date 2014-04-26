package at.controlador;

public interface IControladorComposite <T> {
	/** inicializa elementos de la interfaz dinamicamente **/
	public void inicializar();
	/** Configura la visibilidad de los botones de grabar y eliminar **/
	public void visibilidadBtn();
	
	/** Guarda la informaci�n introducida en datos como un nuevo elemento en la base de datos **/
	public void nuevo();
	/** Edita el objeto seleccionado con la informaci�n introducida en datos */
	public void grabar();
	/** Elimina el objeto seleccionado **/
	public void eliminar();
	/** Sale de la aplicacion */
	public void salir();
	
	/** Busca en la base de datos y puebla la tabla */
	public void buscar();
	/** Accion que se realiza al cargar un elemento de la tabla.
	 * generalmente se cargan los valores del objeto seleccionado en datos **/
	public void elementoFiltroSeleccionado();
	/** Borra toda la informaci�n introducida en filtro y datos */
	public void borrar();
	/**  Lee la informaci�n que hay en datos y devuelve una instancia de la clase que toque **/
	public T creaObjeto();
	/** Comprueba si los datos introducidos han sufrido alguna modificaci�n */
	public boolean comprobarCambiosObjetoActivo();
	/** Acci�n a ejecutar cuando se cierra el shell **/
	public void cerrarShell();
}
