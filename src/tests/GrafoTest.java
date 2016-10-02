package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;

import org.junit.Test;

import logica_negocios.Grafo;
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
	public void agregarAristaBordeTest()
	{
		Grafo grafo = casita();
		grafo.agregarAristas(0, 4);
		grafo.agregarAristas(2, 4);
		
		assertTrue( grafo.contieneArista(2, 0) );
		assertTrue( grafo.contieneArista(0, 2) );
		assertTrue( grafo.contieneArista(0, 4) );
		assertTrue( grafo.contieneArista(4, 0) );
		assertTrue( grafo.contieneArista(2, 4) );
		assertTrue( grafo.contieneArista(4, 2) );
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void agregarAristaIlegalTest()
	{
		Grafo grafo = caminito();
		grafo.agregarAristas(-1, 2);
	}

	@Test
	public void removerAristaTest()
	{
		Grafo grafo = caminito();
		grafo.removerArista(2, 0);
		
		assertFalse( grafo.contieneArista(2, 0) );
		assertTrue( grafo.contieneArista(2, 1) );
		assertFalse( grafo.contieneArista(0, 2) );
	}

	@Test
	public void removerAristaRepetidaTest()
	{
		Grafo grafo = caminito();
		grafo.removerArista(2, 0);
		grafo.removerArista(2, 0);
		grafo.removerArista(2, 0);
		
		assertFalse( grafo.contieneArista(2, 0) );
		assertTrue( grafo.contieneArista(2, 1) );
		assertFalse( grafo.contieneArista(0, 2) );
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
		Grafo grafo = caminito();
		
		assertEquals(2, grafo.CantAristas());
		
		grafo.agregarAristas(0,1);
		
		assertEquals(3, grafo.CantVertices());
		assertEquals(3, grafo.CantAristas());		
		
		grafo.removerArista(2, 0);
		
		assertEquals(2, grafo.CantAristas());	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void loopsTest()
	{
		Grafo grafo = caminito();
		grafo.agregarAristas(3, 3);
	}	

	private Grafo casita()
	{
		ArrayList<Vertice> listaVertices=new ArrayList<Vertice>();
		listaVertices.add(new Vertice(-34.52133782929332,-58.70068073272705));
		listaVertices.add(new Vertice(-34.520772089706036,-58.702311515808105));
		listaVertices.add(new Vertice(-34.52126711205503,-58.70325565338135));
		listaVertices.add(new Vertice(-34.52186820666683,-58.70265483856201));
		listaVertices.add(new Vertice(-34.522433938809684,-58.70325565338135));
		
		Grafo grafo = new Grafo(listaVertices);
		grafo.agregarAristas(0, 1);
		grafo.agregarAristas(0, 2);
		grafo.agregarAristas(1, 3);
		grafo.agregarAristas(2, 3);
		grafo.agregarAristas(3, 4);
		grafo.agregarAristas(2, 4);
		
		return grafo;
	}
	
	private Grafo caminito()
	{
		ArrayList<Vertice> listaVertices=new ArrayList<Vertice>();
		listaVertices.add(new Vertice(-34.52133782929332,-58.70068073272705));
		listaVertices.add(new Vertice(-34.520772089706036,-58.702311515808105));
		listaVertices.add(new Vertice(-34.52126711205503,-58.70325565338135));
		
		Grafo grafo = new Grafo(listaVertices);
		grafo.agregarAristas(0, 2);
		grafo.agregarAristas(1, 2);
	
		return grafo;
	}
}
