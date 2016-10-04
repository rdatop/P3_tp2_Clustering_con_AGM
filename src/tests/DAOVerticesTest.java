package tests;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import modelo.DAOVertices;
import modelo.Vertice;

public class DAOVerticesTest {

	@Test
	public void ListaVerticesVaciaTest() throws IOException
	{
		DAOVertices dao=new DAOVertices("src/modelo/instancia_vacia.json");
		
		ArrayList<Vertice> listaVertices=dao.obtenerVertices();
		
		assertEquals(0, listaVertices.size());//traigo una lista vacia
	}
	
	@Test
	public void SerializaJsonAListaTest() throws IOException
	{
		DAOVertices dao=new DAOVertices("src/modelo/instancia1.json");
		
		ArrayList<Vertice> listaVertices=dao.obtenerVertices();
				
		assertEquals( -34.52133782929332, listaVertices.get(0).getLatitud(), 1e-4 );
		assertEquals( -58.70068073272705, listaVertices.get(0).getLongitud(), 1e-4 );
		assertEquals( -34.520772089706036, listaVertices.get(1).getLatitud(), 1e-4 );
		assertEquals( -58.702311515808105, listaVertices.get(1).getLongitud(), 1e-4 );
		assertEquals( -34.52126711205503, listaVertices.get(2).getLatitud(), 1e-4 );
		assertEquals( -58.70325565338135, listaVertices.get(2).getLongitud(), 1e-4 );
		assertEquals( -34.52186820666683, listaVertices.get(3).getLatitud(), 1e-4 );
		assertEquals( -58.70265483856201, listaVertices.get(3).getLongitud(), 1e-4 );
	}
	
	@Test(expected=IOException.class)
	public void ArchivoInexistenteTest() throws IOException
	{
		DAOVertices dao=new DAOVertices("src/modelo/instancia_inexistente.json");
		
		ArrayList<Vertice> listaVertices=dao.obtenerVertices();
		
		assertEquals(0, listaVertices.size());
	}
}
