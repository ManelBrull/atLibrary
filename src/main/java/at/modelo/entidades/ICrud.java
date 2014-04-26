package at.modelo.entidades;

import java.util.Iterator;

import org.hibernate.HibernateException;
/**
 * 
 * @author brullp
 *
 *	Esta interfaz debe implementarse para todas aquellas clases 
 *  que el usuario pueda modificar.
 *
 * @param <E>
 */
public interface ICrud <E> {
	/**
	 * Saves the current object to the database
	 * @throws HibernateException
	 */
	public void save() throws HibernateException;
	/**
	 * Delete the current object from the database
	 * @throws HibernateException
	 */
	public void delete() throws HibernateException;
	/**
	 * Edit the current object from the information in nuevo
	 * @param nuevo
	 * @throws HibernateException
	 */
	public void update(E nuevo) throws HibernateException;
	/**
	 * Retrieve the element from database
	 * @param id of the element to get
	 * @return
	 * @throws HibernateException
	 */
	public E get(int id) throws HibernateException;
	/**
	 * Retrieve all the elements of the database 
	 * @return
	 * @throws HibernateException
	 */
	public Iterator<E> getAll() throws HibernateException;
}
