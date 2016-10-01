package tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import logica_negocios.Algoritmos;
import logica_negocios.GrafoPesado;
import modelo.Tupla_GrafoPesado_Aristas;



public class AlgoritmosTest {

	//testeo de un vertice del agm
	@Test
	public void unAmarilloTest() {
		GrafoPesado grafo=instancia();
		
		Set<Integer>amarillos=new HashSet<Integer>();
		amarillos.add(0);
		
		Algoritmos.Arista arista=Algoritmos.menorArista(grafo, amarillos);
		assertEquals(new Algoritmos.Arista(0, 1, 5),arista);
		
	}
	
	//testea tres vertices de agm (amarillo por la clase teorica
	@Test
	public void tresAmarillosTest()
	{
		GrafoPesado grafo = instancia();

		Set<Integer> amarillos = new HashSet<Integer>();
		amarillos.add(0);
		amarillos.add(1);
		amarillos.add(2);
		
		Algoritmos.Arista arista = Algoritmos.menorArista(grafo, amarillos);
		assertEquals(new Algoritmos.Arista(1, 4, 4), arista);
	}

	//test del algoritmo de PRIM
	@Test
	public void primTest()
	{
		GrafoPesado grafo = instancia();
		Tupla_GrafoPesado_Aristas agm = Algoritmos.AGM(grafo);
		
		assertTrue(agm.getContieneArista(1, 2));
		assertTrue(agm.getContieneArista(1, 4));
		assertTrue(agm.getContieneArista(4 ,3));
		assertTrue(agm.getContieneArista(0, 1));
		assertEquals(4, agm.getCantAristas());		
	}

	/**preguntar al profe*/
//	private void cheqArista(Algoritmos.Arista arista) {
//		assertTrue(arista.amarillo==0 || arista.amarillo==1);
//		assertTrue(arista.negro==0 || arista.negro==1);
//		assertTrue(arista.negro != arista.negro);
//	}
	
	//banco de prueba
	private GrafoPesado instancia()
	{
		GrafoPesado grafo = new GrafoPesado(5);
		grafo.agregarArista(0, 1, 5);
		grafo.agregarArista(0, 2, 6);
		grafo.agregarArista(0, 3, 10);
		grafo.agregarArista(1, 2, 1);
		grafo.agregarArista(2, 3, 5);
		grafo.agregarArista(1, 4, 4);
		grafo.agregarArista(2, 4, 10);
		grafo.agregarArista(3, 4, -10);
		
		return grafo;
	}
}
