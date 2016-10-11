package logica_negocios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import logica_negocios.Algoritmos.Arista;
import modelo.DAOVertices;
import modelo.Tupla_GrafoPesado_Aristas;
import modelo.Vertice;

public class Clustering 
{
	Tupla_GrafoPesado_Aristas tuplaGrafoAristas;
	
	public Clustering(Tupla_GrafoPesado_Aristas tupla)
	{
		tuplaGrafoAristas=tupla;
	}
	
	//Devuelve una lista de vertices emulando ser clusters
	public ArrayList<ArrayList<Vertice>> listaClusters(int cantClusters){
		
		ArrayList<ArrayList<Vertice>> listaClusters=new ArrayList<ArrayList<Vertice>>();
		
		listaClusters.add(new ArrayList<Vertice>());
		
		for(int i=0;i<tuplaGrafoAristas.getGrafoPesado().getCantVertices();i++)
		{
			listaClusters.get(0).add(tuplaGrafoAristas.getGrafoPesado().obtenerVertice(i));
		}
		
		return listaClusters;
	}

	private void listaAristasMayores(int cantClusters, ArrayList<Arista> listaAristasMayores,
			ArrayList<Arista> listaAuxAristas) {
		////////Guardo en una lista la cantClusters-1 aristas mayores 
		for(int i=0;i<cantClusters-1;i++)
		{
			Arista aristaMayor=Algoritmos.aristaMayorPeso(listaAuxAristas);
			listaAristasMayores.add(aristaMayor);
			listaAuxAristas.remove(aristaMayor);
		}
	}

	/*-- Metodos auxiliares --*/
	// Clona un ArrayList de aristas
	private ArrayList<Arista> clonaAristas(ArrayList<Arista> aristas)
	{
		ArrayList<Arista> aux=new ArrayList<Arista>();
		recorreAgrega(aristas, aux, 0);//de, a, indice de inicio
		return aux;
	}
	
	// Unifica recorrido y agregado de un ArrayList a otro
	private void recorreAgrega(ArrayList<Arista> aristas, ArrayList<Arista> aux, int indice) 
	{
		for(int i=indice;i<aristas.size();i++)
		{
			Arista actual=aristas.get(i);
			aux.add(new Arista(actual.getVertAGM(),actual.getVertice(),actual.getPeso()));
		}
	}
	
	//Devuelve una lista de aristas ordenadas por orden de aparición en la lista
	//de aristas del AGM
	private ArrayList<Arista> listaOrdenadaPorAparicion(ArrayList<Arista> aristasAGM,
			ArrayList<Arista> listaAristasMayores)
	{
		ArrayList<Arista> listaOrdenadaPorAparicion=new ArrayList<Arista>();
		
		for(Arista arista:aristasAGM)//recorro las aristas del AGM
		{
			for(Arista aristaMayor:listaAristasMayores)//por cada recorrida del AGM recorro
				//la lista de aristas mayores
			{
				if(arista.equals(aristaMayor))//si las dos aristas son iguales , la agrego
					//a la nueva lista(teniendo en cuenta su aparición)
				{
					listaOrdenadaPorAparicion.add(new Arista(arista.getVertAGM(),
															 arista.getVertice(),
															 arista.getPeso()));
				}
			}
		}
		
		return listaOrdenadaPorAparicion;
	}
	
	private void populaListaClusters(ArrayList<LinkedList<Vertice>> listaClusters,int cantClusters) {
		for(int i=0;i<cantClusters;i++)
		{
			listaClusters.add(new LinkedList<Vertice>());
		}	
	}
	
//	// Ordena el conj de aristas de > a < peso
//	private void ordenaAristasDescendente(ArrayList<Arista> aristas)
//	{
//		Collections.sort(aristas);//para poder utilizar Collections.sort se modificó la clase
//		//inner Aristas con compareTo
//	}
	
	
	
	////////////main a efectos de verificar funcionamiento, luego borrar
	public static void main(String[]args) throws IOException{
		
		DAOVertices daoVertices=new DAOVertices("src/modelo/instancia_borrar.json");
		
		GrafoPesado grafo=new GrafoPesado(daoVertices.obtenerVertices());
		
		Tupla_GrafoPesado_Aristas tupla=Algoritmos.AGM(grafo);//tupla grafo pesado/agm lista de aristas
		
		///////////////////////////quiero ver pesos de aristas//////////////////////////
		
		for(int i=0;i<tupla.getAristasAGM().size();i++)
		{
			Arista aristaActual=tupla.getAristasAGM().get(i);
			
			System.out.println("Peso de la arista "+i+":"+aristaActual.getPeso());
		}
		
		/////////////////////////////////////////////////////////
		
		//System.out.println(daoVertices.obtenerVertices().get(0).getLatitud());
				
	}
	
}