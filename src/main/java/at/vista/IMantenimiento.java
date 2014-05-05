package at.vista;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
/**
 * 
 * @author brullp
 * 
 * M�todos necesarios para que la clase ControladorMantenimiento funcione
 * Son un conjunto de m�todos get con elementos comunes de todas las
 * interfaces de Mantenimiento.
 *
 */
public interface IMantenimiento {
	public Button getBtnBuscar();
	public Button getBtnNuevo();
	public Button getBtnGrabar();
	public Button getBtnSalir();
	public Button getBtnEliminar();
	public Table getTable();
	public Shell getShell();
	
}
