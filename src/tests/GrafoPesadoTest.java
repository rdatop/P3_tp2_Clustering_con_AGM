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
		ArrayList<Vertice> verts=new ArrayList<Vertice>();
		verts.add(new Vertice(0,-34.52133782929332,-58.70068073272705));
		verts.add(new Vertice(1,-34.520772089706036,-58.702311515808105));
		verts.add(new Vertice(2,-34.52126711205503,-58.70325565338135));
		verts.add(new Vertice(3,-34.52186820666683,-58.70265483856201));
		verts.add(new Vertice(4,-34.522433938809684,-58.70325565338135));
		
		GrafoPesado grafo = new GrafoPesado(verts);
		
		grafo.agregarArista(verts.get(0),verts.get(1));
		grafo.agregarArista(verts.get(1),verts.get(2));
		grafo.agregarArista(verts.get(3),verts.get(0));
		grafo.agregarArista(verts.get(3),verts.get(2));
		
		return grafo;
	}
}