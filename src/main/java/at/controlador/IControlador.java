package at.controlador;
/**
 * 
 * @author brullp
 * 
 * Abstracci�n de las operaciones mas b�sicas de un controlador
 * y que en principio deben ser comunes a todas las user interfaces
 *
 */
public interface IControlador {
	/**
	 * Inicializa la interfaz que se controla
	 * Generalmente se controla la visibilidad de los botones
	 * Se pueblan las combobox y se a�aden los shortcut
	 */
	public void inicializar();

	/**
	 * Configura los botones que deben ser visibles o no
	 */
	public void visibilidadBtn();
	/**
	 * Acci�n que debe ser ejecutada cuando se cierra la ventana activa,
	 * generalmente desregistrar los atajos de teclado del display.
	 */
	public void cerrarShell();
}
