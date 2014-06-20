package at.vista.interfaz;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

/**
 * Imagenes Laterales es una clase que hereda de composite que carga el escudo
 * y las letras del ayuntamiento de Torrent
 * 
 * Para implementarlas en el dialog es imprescindible crear un composite de tamaño
 * 130x230 y crear una nueva instancia de ImagenesLaterarles(compositePadre, SWT.NONE)
 * 
 * 
 * @author ManelBrull
 *
 */
public class ImagenesLaterales extends Composite {

	Image imagenEscudo;
	Image imagenLetras;
	Color azul;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ImagenesLaterales(Composite parent, int style) {
		super(parent, SWT.NONE);
		setSize(130, 230);
		
		imagenEscudo = SWTResourceManager.getImage(ImagenesLaterales.class, "/escudo.jpg");
		imagenLetras = SWTResourceManager.getImage(ImagenesLaterales.class, "/ajuntamentazul.jpg");
		azul = SWTResourceManager.getColor(Recursos.AT_COLOR_AZUL);
		
		setBackground(azul);
		
		Canvas canvasEscudo = new Canvas(this, SWT.NONE);
		canvasEscudo.setBounds(11, 0, 109, 164);
		canvasEscudo.setBackgroundImage(imagenEscudo);
		
		Canvas canvasLetras = new Canvas(this, SWT.NONE);
		canvasLetras.setBounds(5, 173, 120, 48);
		canvasLetras.setBackgroundImage(imagenLetras);
		
	}
	
	public void dispose(){
		imagenEscudo.dispose();
		imagenLetras.dispose();
		azul.dispose();
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	
	
}
