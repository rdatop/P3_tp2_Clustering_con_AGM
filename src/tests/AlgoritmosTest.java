package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import logica_negocio.Algoritmos;
import logica_negocio.GrafoPesado;
import modelo.Vertice;

public class AlgoritmosTest {

	//testeo de un vertice del agm
	@Test
	public void unAmarilloTest() {
		GrafoPesado grafo=instancia();
		
		Set<Integer>amarillos=new HashSet<Integer>();
		amarillos.add(0);
		
		Algoritmos.Arista arista=Algoritmos.menorArista(grafo, amarillos);
		assertEquals(new Algoritmos.Arista(grafo.obtenerVertice(0),grafo.obtenerVertice(1),0.5),arista);
	}
	
	//testea tres vertices de agm (amarillo por la clase teorica
	@Test
	public void tresAmarillosTest()
	{
		GrafoPesado grafo = this.instancia();

		Set<Integer> amarillos = new HashSet<Integer>();
		amarillos.add(0);
		amarillos.add(1);
		amarillos.add(2);
		
		Algoritmos.Arista arista = Algoritmos.menorArista(grafo, amarillos);
		assertEquals(new Algoritmos.Arista(grafo.obtenerVertice(2),grafo.obtenerVertice(3),0.08658641316587116), arista);
	}

////	//test del algoritmo de PRIM tira excepcion,fijate por favor agus
////	@Test
////	public void primTest()
////	{
////		GrafoPesado grafo = instancia();
////		Tupla_GrafoPesado_Aristas agm = Algoritmos.AGM(grafo);
////		
////		assertTrue(agm.getContieneArista(1, 2));
////		assertTrue(agm.getContieneArista(1, 4));
////		assertTrue(agm.getContieneArista(4 ,3));
////		assertTrue(agm.getContieneArista(0, 1));
////		assertEquals(4, agm.getCantAristas());		
////	}
//
//	/**preguntar al profe*/
////	private void cheqArista(Algoritmos.Arista arista) {
////		assertTrue(arista.amarillo==0 || arista.amarillo==1);
////		assertTrue(arista.negro==0 || arista.negro==1);
////		assertTrue(arista.negro != arista.negro);
////	}
	
	private GrafoPesado instancia()
	{
		ArrayList<Vertice> verts=InstanciaVertice.getInstanciaCincoVertices();
		
		GrafoPesado grafo = new GrafoPesado(verts);
		grafo.agregarArista(verts.get(0),verts.get(1));
		grafo.agregarArista(verts.get(0),verts.get(2));
		grafo.agregarArista(verts.get(0),verts.get(3));
		grafo.agregarArista(verts.get(1),verts.get(2));
		grafo.agregarArista(verts.get(2),verts.get(3));
		
		return grafo;
	}
}
