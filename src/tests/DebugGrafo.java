package tests;

import java.io.IOException;

import logica_negocio.Algoritmos;
import logica_negocio.GrafoPesado;
import modelo.DAOVertices;
import modelo.Tupla_GrafoPesado_Aristas;

public class DebugGrafo {

	public static void main(String[] args) throws IOException
	{
		DAOVertices dao=new DAOVertices("src/modelo/instancia1.json");
		GrafoPesado grafoPesado=new GrafoPesado(dao.obtenerVertices());
		Tupla_GrafoPesado_Aristas tupla=Algoritmos.AGM(grafoPesado);
		grafoPesado=tupla.getGrafoPesado();
		
		//Muestro que ahora cada Vertice posee un ID
		System.out.println(grafoPesado.obtenerVertices());
	}
}
