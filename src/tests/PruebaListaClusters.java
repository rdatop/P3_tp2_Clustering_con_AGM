package tests;

import java.io.IOException;
import java.util.ArrayList;

import logica_negocios.Algoritmos;
import logica_negocios.Clustering;
import logica_negocios.GrafoPesado;
import modelo.DAOVertices;
import modelo.Tupla_GrafoPesado_Aristas;
import modelo.Vertice;

public class PruebaListaClusters
{
	public static void main(String[] args) throws IOException
	{
		DAOVertices dao=new DAOVertices("src/modelo/instancia1.json");
		
		GrafoPesado grafo=new GrafoPesado(dao.obtenerVertices());
		
		grafo.agregarArista(0, 1);
		grafo.agregarArista(1, 2);
		grafo.agregarArista(2, 3);
		
		Tupla_GrafoPesado_Aristas tupla=Algoritmos.AGM(grafo);
		
		Clustering clustering=new Clustering(tupla);
		
		ArrayList<ArrayList<Vertice>> listaClusters=clustering.listaClusters(2);
		
		System.out.println(listaClusters);
	}
}