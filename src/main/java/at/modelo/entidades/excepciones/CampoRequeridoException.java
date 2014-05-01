package at.modelo.entidades.excepciones;
/**
 * 
 * @author brullp
 *
 * Excepcion que se lanza cuando una entidad necesita de un dato 
 * y no se le proporciona
 *
 */
public class CampoRequeridoException extends Exception {
	/**
	 * Mensaje de error que se muestra
	 * @param msg
	 */
	public CampoRequeridoException(String msg){
		super(msg);
	}
}
