package at.vista.interfaz;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

/**
 * La clase cabecera aglutina los métodos suficientes para crear un label con el texto pasado
 * en el constructor en medio del campo delimitado.
 * 
 *  
 * @author brullp
 *
 */
public class Cabecera extends Composite {

	private Color azul;
	private Font fCabecera;
	private Color blanco;
	
	/** Fuente de letra usada para la cabecera **/
	private  String fuenteCabecera = "Calibri";
	/** Tamalo de la fuente de la cabecera **/
	private  int tamanoFuenteCabecera = 30;
	/** Estilo de la fuente de cabecera **/
	private  int estiloCabecera = SWT.BOLD;
	/** Tamaño en height del label de la cabecera **/
	private static int heightCabecera = 45;
	/** Color de la letra de la cabecera **/
	private  RGB rgbCabecera = new RGB(255, 255, 255);
	
/**
 * 
 * @param parent
 * @param style
 * @param titulo
 * @param width
 * @param height
 */
	public Cabecera(Composite parent, int style, String titulo, int width, int height) {
		super(parent, style);
		setSize(width, height);
		azul = SWTResourceManager.getColor(Recursos.AT_COLOR_AZUL);
		setBackground(azul);
		
		Label lblTitulo = new Label(this, SWT.SINGLE | SWT.CENTER);
		fCabecera = SWTResourceManager.getFont(fuenteCabecera, tamanoFuenteCabecera, estiloCabecera);
		lblTitulo.setFont(fCabecera);
		lblTitulo.setBounds(0, (Integer)((height-heightCabecera)/2)-5, width, heightCabecera);
		lblTitulo.setBackground(azul);
		blanco = SWTResourceManager.getColor(rgbCabecera);
		lblTitulo.setForeground(blanco);
		lblTitulo.setText(titulo);
	}
	
	public void dispose(){
		azul.dispose();
		fCabecera.dispose();
		blanco.dispose();
	}
	
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
