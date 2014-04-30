package at.modelo.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

public interface IDAO<T> {
	/**
	 * Guarda el objeto o en la base de datos
	 * @param o
	 * @throws HibernateException
	 */
	public void save(T o) throws HibernateException;
	/**
	 * Actualiza el objeto o en la base de datos.
	 * El proceso de actualizacion conserva el id del objeto que 
	 * se pasa y se cambian el resto de datos.
	 * 
	 * @param o
	 * @throws HibernateException
	 */
	public void update(T o) throws HibernateException;
	/**
	 * Borra el objeto o de la base de datos.
	 * @param o
	 * @throws HibernateException
	 */
	public void delete(T o)throws HibernateException;
	
	/**
	 * Obtiene el objeto de la base de datos y lo busca en funcion
	 * del id de clave primaria que tenga.
	 * @param id
	 * @return
	 * @throws HibernateException
	 */
	public T get(int id) throws HibernateException;
	
	/** 
	 * deveulve todos los objetos de ese tipo de la base de datos
	 * @return
	 */
	public List<T> getAll() throws HibernateException;
}
