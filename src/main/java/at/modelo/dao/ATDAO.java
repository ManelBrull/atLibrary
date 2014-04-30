package at.modelo.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * 
 * @author brullp
 *
 * @param <T> Clase de la que deseemos crear el DAO
 * 
 * Clase que implementa las acciones mas comunes que realizan las clases
 * que tienen que almacenar informacion en la base de datos.
 * 
 * Hay varios metodos que se han implementado siguiendo el patron template
 * para dotarles de cierta flexibilidad. En concreto son: 
 * 		T sessionGet(int id);
 * 		Query createQueryGetAll();
 * Se ha documentado sus casos mas usuales, pero podran modificarse a gusto
 * del desarrollador
 * 
 */
public abstract class ATDAO <T> implements IDAO <T> {
	protected Session session;
	protected Transaction tx;
	
	/**
	 * Metodo que se ejecuta antes de empezar cualquier operacion con Hibernate
	 * Generalmente es suficiente con anadir estas dos lineas:
	 * 
	 * 	this.session = HibernateUtil.getSessionFactory().openSession();
	 *  tx = session.beginTransaction();
	 *  
	 */
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
	 * El método debe contener por norma general:
	 * 
	 * (T) session.get(T.class, id);
	 * Adaptando T a la clase escogido
	 * 
	 * Se ha dejado la posibilidad de editar por si en algun futuro
	 * se necesita acceder a un elemento con una clave primaria compuesta
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
	 * El método debe contener por norma general
	 * 
	 * session.createQuery("from T")
	 * 
	 * Siendo T el nombre de la clase que se define como entidad en hibernate
	 * 
	 * 
	 * @return
	 */
	protected abstract Query createQueryGetAll();
	
	
}
