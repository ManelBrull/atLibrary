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

public class ReportManager {
	
	
	private static String imgIzq = "imagenIzq";
	private static URL imagenIzq = ReportManager.class.getResource(
			"/InformeIzquierdo.jpg");
	private static String imgDer = "imagenDer";
	private static URL imagenDer = ReportManager.class.getResource(
			"/InformeDerecha.gif");
	
	/**
	 * Lanza el reporte añadiendole solo las imagenes
	 * @param jasperReport
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws JRException
	 * @throws IOException
	 */
	public static void lanzarReporte(InputStream jasperReport) throws ClassNotFoundException, SQLException, JRException, IOException{
		JasperPrint jasperPrint;
		HashMap	jasperParameter = fillParametros(null, null);
		jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter);
		JasperViewer.viewReport(jasperPrint, false);
		jasperReport.close();
	}
	/**
	 * Lanza el reporte añadiendole las imágenes de atorrent y los parametros que se le pasan 
	 * @param jasperReport
	 * @param nomParametro
	 * @param valParametro
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws JRException
	 * @throws IOException
	 */
	public static void lanzarReporte(InputStream jasperReport, String[] nomParametro, Object[] valParametro) throws ClassNotFoundException, SQLException, JRException, IOException{
		JasperPrint jasperPrint;
		HashMap	jasperParameter = fillParametros(nomParametro, valParametro);
		jasperPrint = JasperFillManager.fillReport (jasperReport, jasperParameter);
		JasperViewer.viewReport(jasperPrint, false);
		jasperReport.close();
	}
		
	private static HashMap <String, Object> fillParametros(String[] nomParametro, Object[] valParametro){
		HashMap <String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put(imgIzq, imagenIzq);
		jasperParameter.put(imgDer, imagenDer);
		if(nomParametro == null){
			return jasperParameter;
		}
		else {
			for(int i = 0; i < nomParametro.length; i++){
				jasperParameter.put(nomParametro[i], valParametro[i]);
			}
			return jasperParameter;
		}
	}
}
