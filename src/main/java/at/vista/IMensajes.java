package at.vista;
/**
 * 
 * @author brullp
 *
 * Mensajes que una clase controlador necesita para transimitir información
 * adicional al usuario. Dichos tipos de mensaje son de informacion, 
 * de errores o de preguntas.
 *
 */
public interface IMensajes {
	/**
	 * Informa al usuario con un mensaje
	 * @param cabecera
	 * @param mensaje
	 */
	public void openInformation(String cabecera, String mensaje);
	/**
	 * Abre un mensaje de error
	 * @param cabecera
	 * @param mensaje
	 */
	public void openError(String cabecera, String mensaje);
	/**
	 * Abre un mensaje del que se espera una respuesta por parte del usuario
	 * Se devuelve un entero haciendo referencia a la opción elegida por el usuario, de 0 a n
	 * @param cabecera
	 * @param mensaje
	 * @param opciones
	 * @return
	 */
	public int openQuestion(String cabecera, String mensaje, String[] opciones);
}
