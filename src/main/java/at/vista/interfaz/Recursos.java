package at.vista.interfaz;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;

public final class Recursos {
	
	private Recursos (){}
	
	/** El Estilo que tendrán todos los dialog de las app de torrent **/
	public static int MY_APP_STYLE = SWT.CLOSE | SWT.TITLE | SWT.MIN;
	
	/** El color azul característico del ayuntamiento de torrent **/
	public static RGB AT_COLOR_AZUL = new RGB(23, 77, 147); 
	
	/** Path relativo donde se encuentra la imagen para el shell de Torrent **/
	public static String shellPath = "/Escudo.ico";
	/** Path relativo donde se encuentra la imagen para los botones de Torrent **/
	public static String imgBotonPath = "/imagenBoton.bmp";
	/** Path relativo donde se encuentra la imagen para salir de la aplicacion **/
	public static String imgSalirPath = "/Salir.bmp";
	/** Path relativo donde se encuentra la imagen para el boton del informe  de la aplicacion **/
	public static String imgInformePath = "/Informe.bmp";
	
	/** Nombre de la fuente para los labels Titulo 1 **/
	public static String nombreFuenteLabelTitulo1 = "Tahoma";
	/** Tamaño de la fuente para los labels Titulo 1 **/
	public static int tamanoFuenteLabelTitulo1 = 14;
	/** Estilo de la fuente para los labels Titulo 1 **/
	public static int estiloFuenteLabelTitulo1 = SWT.BOLD;
	/** Nombre de la fuente para los labels Normal **/
	public static String nombreFuenteLabelNormal = "MS Sans Serif";
	/** Tamaño de la fuente para los labels Normal **/
	public static int tamanoFuenteLabelNormal = 8;
	/** Estilo de la fuente para los labels Normal **/
	public static int estiloFuenteLabelNormal = SWT.BOLD;
	/** Nombre de la fuente para los labels Grupo **/
	public static String nombreFuenteLabelGrupo = "MS Sans Serif";
	/** Tamaño de la fuente para los labels Normal **/
	public static int tamanoFuenteLabelGrupo = 10;
	/** Estilo de la fuente para los labels Normal **/
	public static int estiloFuenteLabelGrupo = SWT.BOLD;
	/** Texto de ayuda para la fecha **/
	public static String tooltipFecha = "La forma de introducir la fecha mediante teclado es:\r\nyyyy/mm/dd\r\nDonde:\r\nyyyy --> El a\u00F1o en cuatro d\u00EDgitos\r\nmm -> el mes en dos d\u00EDgitos\r\ndd -> el d\u00EDa del mes usando dos d\u00EDgitos.\r\nEjemplo:\r\n2014/08/21\r\n";
	/**
	 * Tooltip text para un cLabel que tenga un boton asociado y quiera dársele un shortcut
	 * @param key
	 * @return
	 */
	public static String generarATorrentTooltipTextShortcutLabel(String key){
		return "Pulsando: " + key + " seleccionará el botón asociado";
	}
	/**
	 * Tooltip text para shortcut de un botón
	 * @param key
	 * @return
	 */
	public static String generarATorrentTooltipTextShortcutButton(String key){
		return key;
	}
	
}
