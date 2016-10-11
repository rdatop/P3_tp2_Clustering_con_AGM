package logica_negocios;

import java.io.IOException;
import java.util.ArrayList;

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
	
//	//Devuelve una lista de vertices emulando ser clusters
//	public ArrayList<ArrayList<Vertice>> listaClusters(int cantClusters)
//	{
//		ArrayList<ArrayList<Vertice>> listaClusters=new ArrayList<ArrayList<Vertice>>();
//		
//		ArrayList<Arista> listaAristasMayores=new ArrayList<Arista>();
//		
//		ArrayList<Arista> listaAux=clonaAristas(tuplaGrafoAristas.getAristasAGM());
//		
//		////Recorro la lista auxiliar y busco las cantClusters-1 aristas mayores
//		llenaListaAristasMayores(cantClusters, listaAristasMayores, listaAux);
//		
//		////Ordeno la lista de aristas mayores teniendo en cuenta su orden de aparicion
//		listaOrdenadaPorAparicion(listaAux,listaAristasMayores);
//		
//		////Recorro la lista AGM hasta que encuentro una de las aristas Mayores.Voy agregando
//		////los puntos amarillos de las aristas en un cluster(el cluster cambia sí encuentro
//		////una de las aristas mayores)
//		for(int i=0;i<tuplaGrafoAristas.getAristasAGM().size();i++)// y por q no rrecorres lista aux
//		{
//			Arista AristaAGMActual=tuplaGrafoAristas.getAristasAGM().get(i);
//			
//			for(int j=0;j<listaAristasMayores.size();j++)
//			{
//				if(AristaAGMActual.equals(listaAristasMayores.get(j)))//si la arista actual
//				//coincide con una de las aristas mayores a excluir	
//				{
//					listaClusters.addAll(new Vertice(Vertice.getLatitud(),Vertice.this.getLongitud()));
//				}
//			}
//		}
////		for(int j=0;j<cantClusters;j++)
////		{
////			listaClusters.add(new ArrayList<Vertice>());
////			
////			for(int i=0;i<tuplaGrafoAristas.getGrafoPesado().getCantVertices();i++)
////			{
////				listaClusters.get(j).add(tuplaGrafoAristas.getGrafoPesado().obtenerVertice(i));
////			}
////		}
//		
//		return listaClusters;
//	}
	//Devuelve una lista de vertices emulando ser clusters
	public ArrayList<ArrayList<Vertice>> listaClusters(int cantClusters)
	{
		int indice=0;
		ArrayList<ArrayList<Vertice>> listaClusters=new ArrayList<ArrayList<Vertice>>();
		ArrayList<Arista> listaAristasMayores=new ArrayList<Arista>();
		ArrayList<Arista> listaAux=clonaAristas(tuplaGrafoAristas.getAristasAGM());
		llenaListaAristasMayores(cantClusters, listaAristasMayores, listaAux);
		listaOrdenadaPorAparicion(listaAux,listaAristasMayores);
			
		for(int i=0;i<tuplaGrafoAristas.getAristasAGM().size();i++){// y por q no rrecorres lista aux
			indice++;
			Arista AristaAGMActual=tuplaGrafoAristas.getAristasAGM().get(i);
			if(!AristaAGMActual.equals(listaAristasMayores.get(i))){
				listaClusters.get(i).add(tuplaGrafoAristas.getGrafoPesado().obtenerVertice(i));
			}else{
				listaClusters.get(i).remove(tuplaGrafoAristas.getGrafoPesado().obtenerVertice(i));
			}
		}
		for(int j=indice+1;j<tuplaGrafoAristas.getAristasAGM().size();j++){
			Arista AristaAGMActual=tuplaGrafoAristas.getAristasAGM().get(j);
			if(!AristaAGMActual.equals(listaAristasMayores.get(j))){//si la arista actual
				listaClusters.get(j).add(tuplaGrafoAristas.getGrafoPesado().obtenerVertice(j));			
			}else{
				listaClusters.get(j).remove(tuplaGrafoAristas.getGrafoPesado().obtenerVertice(j));
			}
		}
		return listaClusters;
	}
	

	////Recorro la lista auxiliar y busco las cantClusters-1 aristas mayores
	public void llenaListaAristasMayores(int cantClusters, ArrayList<Arista> listaAristasMayores,
			ArrayList<Arista> listaAux) {
		
		for(int i=0;i<cantClusters-1;i++)
		{
			Arista aristaMayor=Algoritmos.aristaMayorPeso(listaAux);
			
			listaAristasMayores.add(new Arista(aristaMayor.vertAGM,aristaMayor.getVertice(),aristaMayor.getPeso()));
			
			listaAux.remove(aristaMayor);
		}
	}
	
	//Devuelve una lista de aristas ordenadas por orden de aparición en la lista de aristas del AGM
	private void listaOrdenadaPorAparicion(ArrayList<Arista> aristasAGM,ArrayList<Arista> listaAristasMayores)
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
			
			listaAristasMayores=listaOrdenadaPorAparicion;
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
	public static void main(String[]args) throws IOException{
		
		DAOVertices daoVertices=new DAOVertices("src/modelo/instancia_borrar.json");//    este archivo no existe
		                                                                            // y si le pones uno q existe
		GrafoPesado grafo=new GrafoPesado(daoVertices.obtenerVertices());           //dice que esta asignando loops
		
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