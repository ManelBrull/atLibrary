package at.vista.informes;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
/**
 * 
 * @author ManelBrull
 * 
 * Lanzador de informes utilizando JasperReport
 * 
 *
 */
public class JasperReportManager implements IAbrirInformes {
	
	/** Nombre del parámetro para la imagen de la cabecera izquierda **/
	private static String imgIzq = "imagenIzq";
	/** Ruta de la cabecera izquerida **/
	private static URL imagenIzq = JasperReportManager.class.getResource(
			"/InformeIzquierdo.jpg");
	/** Nombre del parámetro para la imagen de la cabecera derecha **/
	private static String imgDer = "imagenDer";
	/** Ruta de la cabecera derecha **/
	private static URL imagenDer = JasperReportManager.class.getResource(
			"/InformeDerecha.gif");
	
	@Override
	public void lanzarReporte(InputStream jasperReport) throws ClassNotFoundException, SQLException, JRException, IOException{
		JasperPrint jasperPrint;
		HashMap	jasperParameter = fillParametros(new String[0],new Object[0]);
		jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter);
		JasperViewer.viewReport(jasperPrint, false);
		jasperReport.close();
	}
	
	@Override
	public void lanzarReporte(InputStream jasperReport, String[] nomParametro, Object[] valParametro) throws ClassNotFoundException, SQLException, JRException, IOException{
		JasperPrint jasperPrint;
		HashMap	jasperParameter = fillParametros(nomParametro, valParametro);
		jasperPrint = JasperFillManager.fillReport (jasperReport, jasperParameter);
		JasperViewer.viewReport(jasperPrint, false);
		jasperReport.close();
	}
	
	/**
	 * Metodo auxiliar para crear el map de parametros
	 * @param nomParametro
	 * @param valParametro
	 * @return
	 */
	private static HashMap <String, Object> fillParametros(String[] nomParametro, Object[] valParametro){
		HashMap <String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("imagenIzq", imagenIzq);
		jasperParameter.put("imagenDer", imagenDer);
		for(int i = 0; i < nomParametro.length; i++)
			jasperParameter.put(nomParametro[i], valParametro[i]);
		return jasperParameter;
	}
}
