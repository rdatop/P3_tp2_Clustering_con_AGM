package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;

import org.junit.Test;

import logica_negocio.Grafo;
import modelo.Vertice;

public class GrafoTest
{
	@Test
	public void agregarAristaTest()
	{
		Grafo grafo = caminito();
		
		assertTrue( grafo.contieneArista(2, 0) );
		assertTrue( grafo.contieneArista(0, 2) );
		assertTrue( grafo.contieneArista(1, 2) );
		assertFalse( grafo.contieneArista(1,0) );	
	}
	
	@Test
	public void ObtenerVerticeTest()
	{
		Grafo grafo=new Grafo(caminito().obtenerVertices());
		Vertice vertice=grafo.obtenerVertice(0);
		assertEquals( -34.52133782929365,vertice.getLatitud(), 1e-4 );
		assertEquals( -58.70068073272778,vertice.getLongitud(), 1e-4 );
	}
	
	@Test
	public void agregarAristaBordeTest()
	{
		Grafo grafo = this.casita();
		grafo.agregarAristas(this.casita().obtenerVertice(1),this.casita().obtenerVertice(3));//0,4
		grafo.agregarAristas(this.casita().obtenerVertice(2),this.casita().obtenerVertice(3));
		
		assertTrue( grafo.contieneArista(2, 0));
		assertTrue( grafo.contieneArista(0, 2));
		assertTrue( grafo.contieneArista(2,	4));
		assertTrue( grafo.contieneArista(4,	2));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void agregarAristaIlegalTest()
	{
		Grafo grafo = caminito();
		grafo.agregarAristas(new Vertice(-1, -34.52133782929332,58.70068073272705),this.caminito().obtenerVertice(2));
	}
	
	@Test
	public void vecinosTest()
	{
		Grafo grafo = casita();
		Set<Integer> vecinos = grafo.vecinosDelVertice(3);
			
		assertEquals(3, vecinos.size());
		assertTrue(vecinos.contains(1));
		assertTrue(vecinos.contains(2));
		assertTrue(vecinos.contains(4));
	}
	
	@Test
	public void parametrosTest()
	{
		Grafo grafo = this.caminito();
		
		assertEquals(2, grafo.cantAristas());
		
		grafo.agregarAristas(this.caminito().obtenerVertice(0),this.caminito().obtenerVertice(1));
		
		assertEquals(3, grafo.cantVertices());
		assertEquals(3, grafo.cantAristas());		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void loopsTest()
	{
		Grafo grafo = this.caminito();
		grafo.agregarAristas(this.caminito().obtenerVertice(2),this.caminito().obtenerVertice(2));
	}	

	private Grafo casita()
	{
		ArrayList<Vertice> listaVertices=InstanciaVertice.getInstanciaCincoVertices();
		
		Grafo grafo = new Grafo(listaVertices);
		grafo.agregarAristas(listaVertices.get(0),listaVertices.get(1));
		grafo.agregarAristas(listaVertices.get(0),listaVertices.get(2));
		grafo.agregarAristas(listaVertices.get(1),listaVertices.get(3));
		grafo.agregarAristas(listaVertices.get(2),listaVertices.get(3));
		grafo.agregarAristas(listaVertices.get(3),listaVertices.get(4));
		grafo.agregarAristas(listaVertices.get(2),listaVertices.get(4));
		
		return grafo;
	}
	
	private Grafo caminito()
	{
		ArrayList<Vertice> listaVertices=InstanciaVertice.getInstanciaTresVertices();
		
		Grafo grafo = new Grafo(listaVertices);
		grafo.agregarAristas(listaVertices.get(0),listaVertices.get(2));
		grafo.agregarAristas(listaVertices.get(1),listaVertices.get(2));
	
		return grafo;
	}
}
