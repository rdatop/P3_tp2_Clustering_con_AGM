package logica_negocios;

import java.util.ArrayList;
import java.util.HashSet;

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
	public ArrayList<HashSet<Vertice>> listaClusters(int cantClusters){
		ArrayList<HashSet<Vertice>> listaClusters=new ArrayList<HashSet<Vertice>>();
		ArrayList<Arista> listaAristasMayores=new ArrayList<Arista>();
		ArrayList<Arista> listaAuxAristas=clonaAristas(tuplaGrafoAristas.getAristasAGM());
		
		populaListaClusters(listaClusters,cantClusters);
		 
		////////Guardo en una lista las cantClusters-1 aristas mayores 
		for(int i=0;i<cantClusters-1;i++)
		{
			Arista aristaMayor=Algoritmos.aristaMayorPeso(listaAuxAristas);
			listaAristasMayores.add(aristaMayor);
			listaAuxAristas.remove(aristaMayor);
		}
		
		////////Ordenar por aparición las cantClusters-1 aristas mayores
		
		
		return listaClusters;
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
	
	private void populaListaClusters(ArrayList<HashSet<Vertice>> listaClusters,int cantClusters) {
			for(int i=0;i<cantClusters;i++)
			{
				listaClusters.add(new HashSet<Vertice>());
			}	
	}

	public static void main(String[] args)
	{
		//System.out.println(Clustering.listaClusters(null, 2));
	}
	
}