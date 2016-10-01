package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import logica_negocios.GrafoPesado;

public class GrafoPesadoTest {

	//testeo de existencia de aristas
	@Test
	public void contieneAristaTest()
	{
		GrafoPesado grafo = instancia();
		
		assertTrue( grafo.contieneArista(0, 1) );
		assertTrue( grafo.contieneArista(0, 3) );
		assertFalse( grafo.contieneArista(1, 3) );
		assertFalse( grafo.contieneArista(2, 0) );
	}

	//testeo de pesos de aristas
	@Test
	public void pesosTest()
	{
		GrafoPesado grafo = instancia();
		
		assertEquals( 13, grafo.getPesoArista(0, 1), 1e-4 );//tolerancia por dif de doubles 10 a la menos 4
		assertEquals( 10, grafo.getPesoArista(3, 0), 1e-4 );//tolerancia por dif de doubles 10 a la menos 4
		assertEquals( 11, grafo.getPesoArista(3, 2), 1e-4 );//tolerancia por dif de doubles 10 a la menos 4
		assertEquals( 12, grafo.getPesoArista(1, 2), 1e-4 );//tolerancia por dif de doubles 10 a la menos 4
	}
	
	//testeo de excepcion
	@Test(expected = IllegalArgumentException.class)
	public void pesoFallidoTest()
	{
		GrafoPesado grafo = instancia();
		grafo.getPesoArista(0, 2);
	}

	//banco de prueba
	private GrafoPesado instancia()
	{
		GrafoPesado grafo = new GrafoPesado(4);
	
		grafo.agregarArista(0, 1, 13);
		grafo.agregarArista(1, 2, 12);
		grafo.agregarArista(3, 0, 10);
		grafo.agregarArista(3, 2, 11);
		
		return grafo;
	}
}


