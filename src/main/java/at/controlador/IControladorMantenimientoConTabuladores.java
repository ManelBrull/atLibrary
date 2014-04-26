package at.controlador;

public interface IControladorMantenimientoConTabuladores {
	/** inicializa elementos de la interfaz dinamicamente **/
	public void inicializar();
	/**A�ade atajos de teclado **/
	public void addShortcuts();
	/** Configura la visibilidad de los botones de grabar y eliminar **/
	public void visibilidadBtn();
	
	public void cerrarShell();

}
