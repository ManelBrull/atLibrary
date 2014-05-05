package at.modelo.entidades;

import java.util.Iterator;

import org.hibernate.HibernateException;

public abstract class ComboEntidad implements ICrud<ComboEntidad>, IEsFiltro, IEsCombo{
	
	private int id;
	private String value;
	
	@Override
	public String toCombo() {
		return getValue();
	}
	
	@Override
	public String[] toTable() {
		return new String[]{getValue()};
	}
	@Override
	public void save() throws HibernateException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete() throws HibernateException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(ComboEntidad nuevo) throws HibernateException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ComboEntidad get(int id) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Iterator<ComboEntidad> getAll() throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
