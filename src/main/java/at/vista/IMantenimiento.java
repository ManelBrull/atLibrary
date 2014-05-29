package at.vista;

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
	
	public void btnBuscarSelected();
	public void btnBuscarSetTooltipText(String text);
	public void btnBuscarIsEnabled(boolean enabled);
	
	public void btnNuevoSelected();
	public void btnNuevoSetTooltipText(String text);
	public void btnNuevoIsEnabled(boolean enabled);
	
	public void btnGrabarSelected();
	public void btnGrabarSetTooltipText(String text);
	public void btnGrabarIsEnabled(boolean enabled);
	
	public void btnSalirSelected();
	public void btnSalirSetTooltipText(String text);
	public void btnSalirIsEnabled(boolean enabled);
	
	public void btnEliminarSelected();
	public void btnEliminarSetTooltipText(String text);
	public void btnEliminarIsEnabled(boolean enabled);
	
	
	public Table getTable();
	public Shell getShell();
	
}
