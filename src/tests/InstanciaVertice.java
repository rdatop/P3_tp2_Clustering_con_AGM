package tests;

import java.util.ArrayList;

import modelo.Vertice;

public class InstanciaVertice
{
	//Devuelve un ArrayList con 5 vertices(para testear)
	public static ArrayList<Vertice> getInstanciaCincoVertices()
	{
		ArrayList<Vertice> listaVertices=new ArrayList<Vertice>();
		listaVertices.add(new Vertice(0,-34.52133782929332,-58.70068073272705));
		listaVertices.add(new Vertice(1,-34.520772089706036,-58.702311515808105));
		listaVertices.add(new Vertice(2,-34.52126711205503,-58.70325565338135));
		listaVertices.add(new Vertice(3,-34.52186820666683,-58.70265483856201));
		listaVertices.add(new Vertice(4,-34.522433938809684,-58.70325565338135));
		
		return listaVertices;
	}
	
	//Devuelve un ArrayList con 3 vertices(para testear)
	public static ArrayList<Vertice> getInstanciaTresVertices()
	{
		ArrayList<Vertice> listaVertices=new ArrayList<Vertice>();
		listaVertices.add(new Vertice(0,-34.52133782929365,-58.70068073272778));
		listaVertices.add(new Vertice(1,-34.520772089706010,-58.702311515808112));
		listaVertices.add(new Vertice(2,-34.52126711205500,-58.70325565338145));
		
		return listaVertices;
	}
}
