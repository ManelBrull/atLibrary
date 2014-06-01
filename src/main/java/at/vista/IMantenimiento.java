package at.vista;

/**
 * 
 * @author brullp
 * 
 * Métodos necesarios para que la clase ControladorMantenimiento funcione
 * Son un conjunto de métodos get con elementos comunes de todas las
 * interfaces de Mantenimiento.
 *
 */
public interface IMantenimiento extends IMensajes, IFiltro {
	
	public void btnBuscarSelected();
	public void btnBuscarIsEnabled(boolean enabled);
	
	public void btnNuevoSelected();
	public void btnNuevoIsEnabled(boolean enabled);
	
	public void btnGrabarSelected();
	public void btnGrabarIsEnabled(boolean enabled);
	
	public void btnSalirSelected();
	public void btnSalirIsEnabled(boolean enabled);
	
	public void btnEliminarSelected();
	public void btnEliminarIsEnabled(boolean enabled);
	public void cerrarDialog();
	
}
