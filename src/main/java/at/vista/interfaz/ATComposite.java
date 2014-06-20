package at.vista.interfaz;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
/**
 * 
 * @author ManelBrull
 *
 * Composite de la organizacion
 *
 */
public class ATComposite extends Composite {

	/** Color para rellenar los margenes **/
	private Color azul; 
	/** fuente por defecto para las label **/
	private Font labelFont;
	

	/** fuente por defecto para las etiquetas de grupo **/
	private Font groupFont;
	/** fuente por defecto para titulos **/
	private Font tituloFont;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ATComposite(Composite parent, int style) {
		super(parent, style);
		inicializarValoresDefecto();
		
	}

	private void inicializarValoresDefecto(){
		setAzul(SWTResourceManager.getColor(Recursos.AT_COLOR_AZUL));
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
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	protected Color getAzul() {
		return azul;
	}

	protected void setAzul(Color azul) {
		this.azul = azul;
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
