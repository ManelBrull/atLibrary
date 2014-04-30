package at.modelo.entidades;
/**
 * 
 * @author brullp
 *
 * Esta interfaz debe ser implementada por todas aquellas clases que 
 * vayan a ser utilizadas para rellenar los valores de una combo
 * 
 * 
 */
public interface IEsCombo {
	/**
	 * Devuelve el nombre identificativo, y el cual verá el usuario, 
	 * de la instancia.
	 * 
	 * @return
	 */
	public String toCombo(); 
}
