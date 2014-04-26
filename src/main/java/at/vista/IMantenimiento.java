package at.vista;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

public interface IMantenimiento {
	public Button getBtnBuscar();
	public Button getBtnNuevo();
	public Button getBtnGrabar();
	public Button getBtnSalir();
	public Button getBtnEliminar();
	public Table getTable();
	public Shell getShell();
	
}
