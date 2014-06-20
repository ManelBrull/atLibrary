package at.vista;
/**
 * 
 * @author ManelBrull
 * 
 * Conjunto de operaciones b�sicas que una interfaz debe 
 * implementar si incluye una tabla en la que se muestren resultados 
 * de b�squedas de una base de datos.
 *
 */
public interface IFiltro {
	/**
	 * vac�a la tabla de todos sus elementos
	 */
	public void vaciarTabla();
	/**
	 * A�ade el elemento a la tabla.
	 * cada posici�n del vector correspone a una columna
	 * @param elemento
	 */
	public void anadirElemento(String[] elemento);
	/**
	 * Devuelve cual es el elemento seleccionado de una tabla.
	 * @return
	 */
	public int elementoElegidoTabla();
}
