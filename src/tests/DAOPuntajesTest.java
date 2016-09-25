package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import modelo.DAOPuntajes;
import modelo.ManejadorArchivos;
import modelo.Coordenada;
import org.junit.Test;

public class DAOPuntajesTest
{

	private String nombreArchivo;
	
	public DAOPuntajesTest()
	{
		this.nombreArchivo="puntajesTest.json";
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
	public void crearPuntajeTest()throws IOException
	{
		DAOPuntajes dao = obtenerInstanciaDAOPuntajes();
		Coordenada puntaje1 = instanciaPuntaje();
		assertEquals(true,dao.agregarPuntaje(puntaje1));
	}
	
	@Test
	public void obtenerPuntajesTest()throws IOException
	{
		DAOPuntajes dao = obtenerInstanciaDAOPuntajes();
		dao.agregarPuntaje(this.instanciaPuntaje());
		dao.agregarPuntaje(this.instanciaPuntajeOtra());
		assertTrue(dao.obtenerPuntajes() instanceof ArrayList<?>);
		assertTrue(dao.obtenerPuntajes().get(0).getNombreUsuario().equals(this.instanciaPuntaje().getNombreUsuario()));
		assertTrue(dao.obtenerPuntajes().get(0).getPuntaje()==this.instanciaPuntaje().getPuntaje());
	}

	/*--------- Metodos auxiliares ---------*/
	private DAOPuntajes obtenerInstanciaDAOPuntajes()throws IOException
	{
		DAOPuntajes dao=new DAOPuntajes(this.nombreArchivo);
		return dao;
	}
	
	private Coordenada instanciaPuntaje()
	{
		Coordenada puntaje=new Coordenada("Juan quevedo",20);
		return puntaje;
	}
	
	private Coordenada instanciaPuntajeOtra()
	{
		Coordenada puntaje=new Coordenada("Ignacio Salazar",35);
		return puntaje;
	}
}