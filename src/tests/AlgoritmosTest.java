package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import logica_negocios.Algoritmos;
import logica_negocios.GrafoPesado;
import modelo.Vertice;

public class AlgoritmosTest 
{

	//testeo de un vertice del agm
	@Test
	public void unAmarilloTest() 
	{
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
		assertEquals(new Algoritmos.Arista(2,3,0.08658641316587116), arista);
	}

//	//test del algoritmo de PRIM tira excepcion,fijate por favor agus
//	@Test
//	public void primTest()
//	{
//		GrafoPesado grafo = instancia();
//		Tupla_GrafoPesado_Aristas agm = Algoritmos.AGM(grafo);
//		
//		assertTrue(agm.getContieneArista(1, 2));
//		assertTrue(agm.getContieneArista(1, 4));    2, 3
//		assertTrue(agm.getContieneArista(4 ,3));
//		assertTrue(agm.getContieneArista(0, 1));
//		assertEquals(4, agm.getCantAristas());		
//	}

	/**preguntar al profe*/
//	private void cheqArista(Algoritmos.Arista arista) {
//		assertTrue(arista.amarillo==0 || arista.amarillo==1);
//		assertTrue(arista.negro==0 || arista.negro==1);
//		assertTrue(arista.negro != arista.negro);
//	}
	
	//banco de prueba
	private GrafoPesado instancia()
	{
		ArrayList<Vertice> listaVertices=new ArrayList<Vertice>();
		listaVertices.add(new Vertice(1,-34.52133782929332,-58.70068073272705));
		listaVertices.add(new Vertice(2,-34.520772089706036,-58.702311515808105));
		listaVertices.add(new Vertice(3,-34.52126711205503,-58.70325565338135));
		listaVertices.add(new Vertice(4,-34.52186820666683,-58.70265483856201));
		listaVertices.add(new Vertice(5,-34.522433938809684,-58.70325565338135));
		
		GrafoPesado grafo = new GrafoPesado(listaVertices);
		grafo.agregarArista(0, 1);
		grafo.agregarArista(0, 2);
		grafo.agregarArista(0, 3);
		grafo.agregarArista(1, 2);
		grafo.agregarArista(2, 3);
		
		return grafo;
	}
}
