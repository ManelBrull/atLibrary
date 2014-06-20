package at.vista;
/**
 * 
 * @author ManelBrull
 * 
 * Conjunto de operaciones básicas que una interfaz debe 
 * implementar si incluye una tabla en la que se muestren resultados 
 * de búsquedas de una base de datos.
 *
 */
public interface IFiltro {
	/**
	 * vacía la tabla de todos sus elementos
	 */
	public void vaciarTabla();
	/**
	 * Añade el elemento a la tabla.
	 * cada posición del vector correspone a una columna
	 * @param elemento
	 */
	public void anadirElemento(String[] elemento);
	/**
	 * Devuelve cual es el elemento seleccionado de una tabla.
	 * @return
	 */
	public int elementoElegidoTabla();
}
