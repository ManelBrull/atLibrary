package at.vista;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
/**
 * 
 * @author brullp
 * 
 * Métodos necesarios para que la clase ControladorMantenimiento funcione
 * Son un conjunto de métodos get con elementos comunes de todas las
 * interfaces de Mantenimiento.
 *
 */
public interface IMantenimiento {
	public void openInformation(String cabecera, String mensaje);
	public void openError(String cabecera, String mensaje);
	public int openQuestion(String cabecera, String mensaje, String[] opciones);
	
	public Button getBtnBuscar();
	public Button getBtnNuevo();
	public Button getBtnGrabar();
	public Button getBtnSalir();
	public Button getBtnEliminar();
	public Table getTable();
	public Shell getShell();
	
}
