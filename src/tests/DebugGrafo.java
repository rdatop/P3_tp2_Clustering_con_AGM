package tests;

import java.io.IOException;

import logica_negocios.Algoritmos;
import logica_negocios.GrafoPesado;
import modelo.DAOVertices;
import modelo.Tupla_GrafoPesado_Aristas;
import modelo.Vertice.generadorID;

public class DebugGrafo {

	public static void main(String[] args) throws IOException
	{
		DAOVertices dao=new DAOVertices("src/modelo/instancia1.json");
		GrafoPesado grafoPesado=new GrafoPesado(dao.obtenerVertices());
		Tupla_GrafoPesado_Aristas tupla=Algoritmos.AGM(grafoPesado);
		grafoPesado=tupla.getGrafoPesado();
		
		System.out.println(generadorID.aumentarContador());
		
		System.out.println(generadorID.aumentarContador());
		
		System.out.println(generadorID.aumentarContador());
	}
}
