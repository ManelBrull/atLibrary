package at.modelo.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class DAO <T> {
	protected Session session;
	protected Transaction tx;
	
	protected abstract void iniciaOperacion();
	
	public void save(T o) throws HibernateException{
		try {
			iniciaOperacion();
			session.save(o);
			tx.commit();
		} catch(HibernateException he){
			tx.rollback();
			throw he;
		} finally {
			session.close();
		}
	}
	
	public void update(T o) throws HibernateException{
		try{
			iniciaOperacion();
			session.update(o);
			tx.commit();
		} catch(HibernateException he){
			tx.rollback();
			throw he;
		} finally {
			session.close();
		}
	}
	
	public void delete(T o)throws HibernateException{
		try{
			iniciaOperacion();
			session.delete(o);
			tx.commit();
		} catch(HibernateException he){
			tx.rollback();
			throw he;
		} finally {
			session.close();
		}
	}
	
	public T get(int id) throws HibernateException
	{ 
		T t = null;  
		iniciaOperacion();
		t = sessionGet(id);
		session.close(); 
		return t; 
	}
	
	/**
	 * El método debe contener
	 * (T) session.get(T.class, id);
	 * Adaptando T a la clase escogido
	 *  
	 * @return
	 */
	protected abstract T sessionGet(int id);
	
	public List<T> getAll(){
		List<T> list = null;
		try{
			iniciaOperacion();
			Query myQuery = createQueryGetAll();
			list = myQuery.list();
		} finally {
			session.close();
		}
		return list;
	}
	/**
	 * El método debe contener
	 * session.createQuery("from T")
	 * Adaptando T a la clase escogida
	 * @return
	 */
	protected abstract Query createQueryGetAll();
	
	
}
