package at.vista.interfaz;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

public abstract class ATorrentDialogEscudo extends Dialog {
		
	/** Titulo que se mostrara en el shell **/
	private String titulo;
	/** Text que se mostrara en la cabecera **/
	private String tituloCabecera;
	/** Color para rellenar los margenes **/
	protected Color azul; 
	/** imagen del shell **/
	private Image shellImage;
	
	protected ImagenesLaterales imagenesLaterales;
	protected Cabecera cabecera;

	/**
	 * Create the Atorrent Dialog
	 * @param parent
	 * @param title titulo del shell
	 * @param titleCabecera titulo de la cabecera
	 */
	public ATorrentDialogEscudo(Shell parent, String title, String titleCabecera) {
		super(parent, Recursos.MY_APP_STYLE);
		setTitulo(title);
		setTituloCabecera(titleCabecera);
		setAzul(SWTResourceManager.getColor(Recursos.AT_COLOR_AZUL));
		setShellImage(SWTResourceManager.getImage(ATorrentDialogEscudo.class, Recursos.shellPath));
		parent.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				dispose();
			}
		});	
	}
	
	/**
	 * Crea el contenido típico de un Dialog de una aplicacion de ATorrent
	 * 
	 * Todas las subclases tienen que llamarlo:
	 *  
	 *  shell = new Shell(getParent(), getStyle());
	 *	shell.setSize(myWidth, myHeight);
	 *	super.createContents(shell);
	 * 
	 * @param shell De la clase que extiende ATorrentDialog
	 */
	public void createContents(Shell shell){
		shell.setText(getTitulo());
		shell.setImage(getShellImage());
		shell.setLayout(null);
		
		Composite compositeImagenes = new Composite(shell, SWT.NONE);
		compositeImagenes.setBounds(0, 0, 130, 230);
		imagenesLaterales = new ImagenesLaterales(compositeImagenes, SWT.NONE);
		
		Composite compositeLogin = new Composite(shell, SWT.NONE);
		compositeLogin.setBounds(130, 0, shell.getBounds().width-130, 64);
		cabecera = new Cabecera(compositeLogin, SWT.NONE, getTituloCabecera(), shell.getBounds().width-130, 64);
		
		Composite compositeLeftBottomMargin = new Composite(shell, SWT.NONE);
		compositeLeftBottomMargin.setBounds(0, 230, 130, shell.getBounds().height-230);
		compositeLeftBottomMargin.setBackground(getAzul());
	}

	public void dispose(){
		imagenesLaterales.dispose();
		cabecera.dispose();
		azul.dispose();
		shellImage.dispose();
		myDispose();
		
	}
	
	/**
	 * Method to implement to dispose that stuff created by me,
	 * like Image, Font, etc.
	 * 
	 */
	protected abstract void myDispose();
	
	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the tituloCabecera
	 */
	public String getTituloCabecera() {
		return tituloCabecera;
	}

	/**
	 * @param tituloCabecera the tituloCabecera to set
	 */
	public void setTituloCabecera(String tituloCabecera) {
		this.tituloCabecera = tituloCabecera;
	}

	/**
	 * @return the azul
	 */
	public Color getAzul() {
		return azul;
	}

	/**
	 * @param azul the azul to set
	 */
	public void setAzul(Color azul) {
		this.azul = azul;
	}

	/**
	 * @return the shellImage
	 */
	public Image getShellImage() {
		return shellImage;
	}

	/**
	 * @param shellImage the shellImage to set
	 */
	public void setShellImage(Image shellImage) {
		this.shellImage = shellImage;
	}
}
