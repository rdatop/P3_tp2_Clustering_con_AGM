package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import logica_negocios.GrafoPesado;
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
		GrafoPesado grafo = instancia();
		
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
		ArrayList<Vertice> listaVertices=new ArrayList<Vertice>();
		listaVertices.add(new Vertice(-34.52133782929332,-58.70068073272705));
		listaVertices.add(new Vertice(-34.520772089706036,-58.702311515808105));
		listaVertices.add(new Vertice(-34.52126711205503,-58.70325565338135));
		listaVertices.add(new Vertice(-34.52186820666683,-58.70265483856201));
		listaVertices.add(new Vertice(-34.522433938809684,-58.70325565338135));
		
		GrafoPesado grafo = new GrafoPesado(listaVertices);
		
		grafo.agregarArista(0, 1);
		grafo.agregarArista(1, 2);
		grafo.agregarArista(3,0);
		grafo.agregarArista(3,2);
		
		return grafo;
	}
}