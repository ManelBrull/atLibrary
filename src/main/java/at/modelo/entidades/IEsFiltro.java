package at.modelo.entidades;
/**
 * 
 * @author brullp
 *
 * Debe ser implementada por todas aquellas clases que deben hacer búsquedas
 * y mostrar sus resultados en una tabla
 *
 */
public interface IEsFiltro {
	/**
	 * Devuelve el array de String cuya longitud es la misma que el numero
	 * de elementos que tendra la table que deseemos.
	 * 
	 * @return
	 */
	public String[] toTable();
}
