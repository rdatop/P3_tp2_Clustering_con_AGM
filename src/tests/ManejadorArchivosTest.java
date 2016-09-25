package tests;

import static org.junit.Assert.*;
import java.io.IOException;
import modelo.ManejadorArchivos;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ManejadorArchivosTest {

	private String nombreArchivo;
	private String textoParaTestear;
	private String archivoInexistente;
	
	public ManejadorArchivosTest() {
		this.nombreArchivo="test.json";
		this.textoParaTestear="Texto para test";
		this.archivoInexistente="no_existe.json";
	}
	
	@Before
	public void creaArchivoVacio() throws IOException 
	{
		ManejadorArchivos.escribirGuardarArchivo(this.nombreArchivo,"");
	}
	
	@After
	public void eliminarArchivo() throws IOException 
	{
		ManejadorArchivos.borrarArchivo(this.nombreArchivo);
	}
	
	@Test
	public void crearArchivoTest() throws IllegalArgumentException, IOException
	{
		assertTrue(ManejadorArchivos.escribirGuardarArchivo(this.nombreArchivo,this.textoParaTestear));
	}
	
	@Test
	public void leerArchivoCoincideTextoTest() throws IOException
	{
		ManejadorArchivos.escribirGuardarArchivo(this.nombreArchivo,this.textoParaTestear);
		
		assertTrue(this.textoParaTestear.equals(ManejadorArchivos.leerArchivo(this.nombreArchivo)));
	}
	
	@Test(expected=IOException.class)
	public void leerArchivoExcepcionTest()throws IOException
	{
		ManejadorArchivos.leerArchivo(this.archivoInexistente);
	}
	
	@Test
	public void eliminarArchivoTest()throws IOException
	{
		assertTrue(ManejadorArchivos.borrarArchivo(this.nombreArchivo));
	}
	
	@Test
	public void eliminarArchivoErrorTest()
	{
		assertFalse(ManejadorArchivos.borrarArchivo(this.archivoInexistente));
	}
}