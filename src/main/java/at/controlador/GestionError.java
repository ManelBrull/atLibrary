package at.controlador;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.security.auth.login.LoginException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.hibernate.HibernateException;

import at.modelo.entidades.excepciones.CampoRequeridoException;

public class GestionError {

	private Shell shell;
	
	public GestionError(Shell shell, Exception ex){
		this.shell = shell;
		if(ex instanceof HibernateException)
			gestionError((HibernateException) ex);
		if(ex instanceof ClassNotFoundException)
			gestionError((ClassNotFoundException) ex);
		if(ex instanceof SQLException)
			gestionError((SQLException) ex);
		if(ex instanceof CampoRequeridoException)
			gestionError((CampoRequeridoException) ex);
		
	}
	
	public void gestionError(HibernateException he){
		MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR);
		dialog.setText("Hibernate");
		dialog.setMessage("Ha ocurrido un problema con la base de datos: " + he.getMessage());
		dialog.open();
	}

	public void gestionError(IllegalArgumentException iae){
		MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR);
		dialog.setText("Problema datos entrada");
		dialog.setMessage("Ha ocurrido un problema con los datos de entrada " + iae.getMessage());
		dialog.open();
	}
	
	public void gestionError( NamingException iae){
		MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR);
		dialog.setText("Dominio");
		dialog.setMessage("Ha ocurrido un problema con el login de dominio,"
				+ " revise si ha introducido "
				+ "correctamente sus datos de acceso");
		dialog.open();
	}
	
	public void gestionError( ClassNotFoundException iae){
		MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR);
		dialog.setText("Base de datos");
		dialog.setMessage("Clase no encontrada, Class.forName()");
		dialog.open();
	}
	
	public void gestionError(SQLException iae){
		MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR);
		dialog.setText("Base de datos");
		dialog.setMessage("Problema con la base de datos, revise la conexión");
		dialog.open();
	}
	
	public void gestionError(CampoRequeridoException cre){
		MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR);
		dialog.setText("Error");
		dialog.setMessage(cre.getMessage());
		dialog.open();
	}
	public void gestionError(LoginException cre){
		MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR);
		dialog.setText("Error");
		dialog.setMessage(cre.getMessage());
		dialog.open();
	}
	
}
