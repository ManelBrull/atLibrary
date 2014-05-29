package at.vista.informes;

import java.io.InputStream;

public interface IAbrirInformes {
	
	public void lanzarReporte(InputStream jasperReport) throws Exception;
	
	public void lanzarReporte(InputStream jasperReport, String[] nomParametro, Object[] valParametro) throws Exception;
	
}
