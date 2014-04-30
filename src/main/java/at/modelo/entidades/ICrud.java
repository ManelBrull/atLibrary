package at.modelo.entidades;

import java.util.Iterator;
import org.hibernate.HibernateException;

/**
 * @author brullp
 *	
 * @param <T> Nombre de la clase que implementa la interfaz
 * 
 * Esta interfaz debe implementarse para todas aquellas clases 
 * que el usuario pretenda que sus datos persistan.
 *
 */
public interface ICrud <T> {
	/**
	 * Guarda el objeto en la base de datos
	 * @throws HibernateException
	 */
	public void save() throws HibernateException;
	/**
	 * Borra el objeto de la base de datos
	 * @throws HibernateException
	 */
	public void delete() throws HibernateException;
	/**
	 * Edita la informacion del objeto actual con la de nuevo
	 * @param nuevo
	 * @throws HibernateException
	 */
	public void update(T nuevo) throws HibernateException;
	/**
	 * Obtiene el elemento por su id
	 * @param id 
	 * @return
	 * @throws HibernateException
	 */
	public T get(int id) throws HibernateException;
	/**
	 * Obtiene todos los elementos de la base de datos 
	 * @return
	 * @throws HibernateException
	 */
	public Iterator<T> getAll() throws HibernateException;
}
