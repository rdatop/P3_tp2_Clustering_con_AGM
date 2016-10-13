package logica_negocios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import logica_negocios.Algoritmos.Arista;
import modelo.DAOVertices;

public class Clustering {
	
	//variable de instancia
	private ArrayList<Arista>_pesosAristas;
	
	// Constructor
	public Clustering(ArrayList<Arista> aristas){
		_pesosAristas=clonaAristas(aristas);
	}
	
	// Obvía las aristas mayores de acuerdo a la cant de clusters -1
	public ArrayList<Arista> obviarAristasMayores(ArrayList<Arista> aristas, int cantClusters){
		ArrayList<Arista> aux=new ArrayList<Arista>();
		int aristasEliminadas=cantClusters-1;
		recorreAgrega(aristas, aux, aristasEliminadas);//de, a, indice de inicio
		return aux;
	}
	
	// Ordena el conj de aristas de > a < peso
	public void ordenaAristasDescendente(ArrayList<Arista> aristas){
		Collections.sort(aristas);//para poder utilizar Collections.sort se modificio la clase inner Aristas con compareTo
		Collections.reverse(aristas);
	}
		
	/*-- Metodos auxiliares --*/
	// Clona un ArrayList de aristas
	private ArrayList<Arista> clonaAristas(ArrayList<Arista> aristas){
		ArrayList<Arista> aux=new ArrayList<Arista>();
		recorreAgrega(aristas, aux, 0);//de, a, indice de inicio
		return aux;
	}

	// Unifica rrecorrido y agregado de un ArrayList a otro
	private void recorreAgrega(ArrayList<Arista> aristas, ArrayList<Arista> aux, int indice) {
		for(int i=indice;i<aristas.size();i++){
			Arista actual=aristas.get(i);
			aux.add(new Arista(actual.getVertAGM(),actual.getVertice(),actual.getPeso()));
			ordenaAristasDescendente(aux);
		}
	}
	
	// Lectura protegida de la lista de aristas
	public ArrayList<Arista> getPesosAristas(){
		return _pesosAristas;
	}
	
	
	
	
	////////////main a efectos de verificar funcionamiento, luego borrar
	public static void main(String[]args) throws IOException{
		DAOVertices daoVertices=new DAOVertices("src/modelo/instancia1.json");
		GrafoPesado grafo = new GrafoPesado(daoVertices.obtenerVertices());
		for (int i = 0; i <grafo.cantVertices()-1; i++) {
			for (int j = i+1; j < grafo.cantVertices(); j++) {
				grafo.agregarArista(i, j);
			}
		}

		Clustering clustering=new Clustering(Algoritmos.AGM(grafo).getAristasAGM());
		System.out.println(" Aristas ya ordenadas de > a <");
		System.out.println(clustering.getPesosAristas().toString());
		
		System.out.println(" Prueba clustering");
		System.out.println(clustering.obviarAristasMayores(clustering.getPesosAristas(), 3).toString());
	}	
}