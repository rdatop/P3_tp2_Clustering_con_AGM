package logica_negocios;

import java.util.ArrayList;
import java.util.LinkedList;

import logica_negocios.Algoritmos.Arista;
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
	public ArrayList<LinkedList<Vertice>> listaClusters(int cantClusters){
		
		ArrayList<LinkedList<Vertice>> listaClusters=new ArrayList<LinkedList<Vertice>>();
		ArrayList<Arista> listaAristasMayores=new ArrayList<Arista>();
		ArrayList<Arista> listaAuxAristas=clonaAristas(tuplaGrafoAristas.getAristasAGM());
		
		populaListaClusters(listaClusters,cantClusters);
		 
		listaAristasMayores(cantClusters, listaAristasMayores, listaAuxAristas);
		
		//Ordeno la lista de aristas mayores segun nivel de aparicion
		@SuppressWarnings("unused")
		ArrayList<Arista> listaAristasMayoresOrdenadas=listaOrdenadaPorAparicion(tuplaGrafoAristas.getAristasAGM(),listaAristasMayores);
		
		//formo las listas de clusters
		
		return listaClusters;
	}

	public void listaAristasMayores(int cantClusters, ArrayList<Arista> listaAristasMayores,
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
//	public static void main(String[]args) throws IOException{
//		
//		DAOVertices daoVertices=new DAOVertices("src/modelo/instancia1.json");
//		GrafoPesado grafo = new GrafoPesado(daoVertices.obtenerVertices());
//		grafo.agregarArista(0, 1);
//		grafo.agregarArista(0, 2);
//		grafo.agregarArista(0, 3);
//		grafo.agregarArista(1, 2);
//		grafo.agregarArista(2, 3);
//		
//		Clustering clustering=new Clustering(Algoritmos.AGM(grafo).getAristasAGM());
//		clustering.ordenaAristasDescendente(clustering._pesosAristas);
//		System.out.println(clustering.getPesosAristas().toString());
//		
//		//prueba de obviar aristas mayores
//		System.out.println(clustering.obviarAristasMayores(clustering.getPesosAristas(), 3).toString());
//		
//	}
	
	

	public static void main(String[] args)
	{
		//System.out.println(Clustering.listaClusters(null, 2));
	}
	
}