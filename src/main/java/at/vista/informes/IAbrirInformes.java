package at.vista.informes;

import java.io.InputStream;
/**
 * 
 * @author ManelBrull
 *
 * Abstrae el lanzamiento de informes, haciendo la aplicacion independiente
 * de la herramienta de reporting elegida.
 *
 */
public interface IAbrirInformes {
	/**
	 * Lanza un reporte que no tiene parámetros
	 * @param reportStream
	 * @throws Exception
	 */
	public void lanzarReporte(InputStream reportStream) throws Exception;
	/**
	 * Lanza un reporte que tiene parametros
	 * @param reportStream
	 * @param nomParametro
	 * @param valParametro
	 * @throws Exception
	 */
	public void lanzarReporte(InputStream reportStream, String[] nomParametro, Object[] valParametro) throws Exception;
	
}
