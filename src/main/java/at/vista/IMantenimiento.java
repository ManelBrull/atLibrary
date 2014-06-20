package at.vista;

/**
 * 
 * @author brullp
 * 
 * Métodos necesarios para que una clase Controlador funcione correctamente.
 *  
 *
 */
public interface IMantenimiento extends IMensajes, IFiltro {
	/**
	 * Código que se ejecuta cuando el botón buscar es seleccionado
	 */
	public void btnBuscarSelected();
	/**
	 * Establece si el boton Buscar esta habilitado
	 * @param enabled
	 */
	public void setBtnBuscarEnabled(boolean enabled);
	/**
	 * Código que se ejecuta cuando el botón nuevo es seleccionado
	 */
	public void btnNuevoSelected();
	/**
	 * Establece si el boton nuevo esta habilitado
	 * @param enabled
	 */
	public void setBtnNuevoEnabled(boolean enabled);
	/**
	 * Código que se ejecuta cuando el botón grabar es seleccionado
	 */
	public void btnGrabarSelected();
	/**
	 * Establece si el boton Grabar esta habilitado
	 * @param enabled
	 */
	public void setBtnGrabarEnabled(boolean enabled);
	/**
	 * Código que se ejecuta cuando el botón salir es seleccionado
	 */
	public void btnSalirSelected();
	/**
	 * Establece si el boton Salir esta habilitado
	 * @param enabled
	 */
	public void setBtnSalirEnabled(boolean enabled);
	/**
	 * Código que se ejecuta cuando el botón eliminar es seleccionado
	 */
	public void btnEliminarSelected();
	/**
	 * Establece si el boton Eliminar esta habilitado
	 * @param enabled
	 */
	public void setBtnEliminarEnabled(boolean enabled);
	/**
	 * Acciones que se ejecutan al cerrar la ventana activa
	 */
	public void cerrarDialog();
	
}
