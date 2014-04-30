package at;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import at.vista.interfaz.Recursos;

public class RecursosValidos {
	
	@Test
	public void testColorAzul() {
		assertEquals("Se ha cambiado el color rojo", Recursos.AT_COLOR_AZUL.red,23);
		assertEquals("Se ha cambiado el color verde", Recursos.AT_COLOR_AZUL.green,77);
		assertEquals("Se ha cambiado el color azul", Recursos.AT_COLOR_AZUL.blue,147);
	}
	
	@Test
	public void imagenesCargadas(){
		assertNotNull("No encuentra la ruta a el icono del shell",Recursos.class.getResourceAsStream(Recursos.shellPath));
		assertNotNull("No encuentra la ruta de la imagen de un botón", Recursos.class.getResourceAsStream(Recursos.imgBotonPath));
		assertNotNull("No encuentra la ruta de la imagen del boton de informe", Recursos.class.getResourceAsStream(Recursos.imgInformePath));
	}
}