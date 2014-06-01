package at.vista;
/**
 * 
 * @author brullp
 *
 * Interfaz para 
 *
 */
public interface IMensajes {
	
	public void openInformation(String cabecera, String mensaje);
	public void openError(String cabecera, String mensaje);
	public int openQuestion(String cabecera, String mensaje, String[] opciones);
}
