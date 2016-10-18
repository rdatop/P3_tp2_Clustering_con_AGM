package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import logica_negocio.GrafoPesado;
import modelo.Vertice;

public class GrafoPesadoTest
{
	@Test
	public void contieneAristaTest()
	{
		GrafoPesado grafo = instancia();
		
		assertTrue( grafo.contieneArista(0, 1) );
		assertTrue( grafo.contieneArista(0, 3) );
		assertFalse( grafo.contieneArista(1, 3) );
		assertFalse( grafo.contieneArista(2, 0) );
	}

	@Test
	public void pesosTest()
	{
		GrafoPesado grafo = this.instancia();
		
		assertEquals( 0.1621085850851515, grafo.getPesoArista(0, 1), 1e-4 );
		assertEquals( 0.19023017355514252, grafo.getPesoArista(3, 0), 1e-4 );
		assertEquals( 0.08658641316587116, grafo.getPesoArista(3, 2), 1e-4 );
		assertEquals( 0.10252651484768478, grafo.getPesoArista(1, 2), 1e-4 );
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void pesoFallidoTest()
	{
		GrafoPesado grafo = instancia();
		grafo.getPesoArista(0,2);
	}

	private GrafoPesado instancia()
	{
		ArrayList<Vertice> verts=InstanciaVertice.getInstanciaCincoVertices();
		
		GrafoPesado grafo = new GrafoPesado(verts);
		
		grafo.agregarArista(verts.get(0),verts.get(1));
		grafo.agregarArista(verts.get(1),verts.get(2));
		grafo.agregarArista(verts.get(3),verts.get(0));
		grafo.agregarArista(verts.get(3),verts.get(2));
		
		return grafo;
	}
}