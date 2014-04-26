package at.vista.interfaz;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

public abstract class ATorrentDialog extends Dialog {
		
	/** Titulo que se mostrara en el shell **/
	private String titulo;
	/** Color para rellenar los margenes **/
	private Color azul; 
	/** imagen del shell **/
	private Image shellImage;
	/** fuente por defecto para las label **/
	private Font labelFont;
	/** fuente por defecto para las etiquetas de grupo **/
	private Font groupFont;
	/** fuente por defecto para titulos **/
	private Font tituloFont;
	

	/**
	 * Create the Atorrent Dialog
	 * @param parent
	 * @param title titulo del shell
	 * @param titleCabecera titulo de la cabecera
	 */
	public ATorrentDialog(Shell parent, String title) {
		super(parent, Recursos.MY_APP_STYLE);
		setTitulo(title);
		setAzul(SWTResourceManager.getColor(Recursos.AT_COLOR_AZUL));
		setShellImage(SWTResourceManager.getImage(ATorrentDialog.class, Recursos.shellPath));
		labelFont = SWTResourceManager.getFont(
				Recursos.nombreFuenteLabelNormal,
				Recursos.tamanoFuenteLabelNormal,
				Recursos.estiloFuenteLabelNormal);
		groupFont = SWTResourceManager.getFont(
				Recursos.nombreFuenteLabelGrupo,
				Recursos.tamanoFuenteLabelGrupo,
				Recursos.estiloFuenteLabelGrupo);
		tituloFont = SWTResourceManager.getFont(
				Recursos.nombreFuenteLabelTitulo1,
				Recursos.tamanoFuenteLabelTitulo1,
				Recursos.estiloFuenteLabelTitulo1);
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
	}
	/**
	 * Dispose the elements of ATorrentDialog
	 */
	public void dispose(){
		getAzul().dispose();
		shellImage.dispose();
		tituloFont.dispose();
		groupFont.dispose();
		labelFont.dispose();
		myDispose();
	}
	protected abstract void myDispose();

	/**
	 * @return the titulo
	 */
	protected String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	protected void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	/**
	 * @return the azul
	 */
	protected Color getAzul() {
		return azul;
	}

	/**
	 * @param azul the azul to set
	 */
	protected void setAzul(Color azul) {
		this.azul = azul;
	}

	/**
	 * @return the shellImage
	 */
	protected Image getShellImage() {
		return shellImage;
	}

	/**
	 * @param shellImage the shellImage to set
	 */
	protected void setShellImage(Image shellImage) {
		this.shellImage = shellImage;
	}
	protected Font getLabelFont() {
		return labelFont;
	}
	protected void setLabelFont(Font labelFont) {
		this.labelFont = labelFont;
	}
	protected Font getGroupFont() {
		return groupFont;
	}
	protected void setGroupFont(Font groupFont) {
		this.groupFont = groupFont;
	}
	protected Font getTituloFont() {
		return tituloFont;
	}
	protected void setTituloFont(Font tituloFont) {
		this.tituloFont = tituloFont;
	}
}
