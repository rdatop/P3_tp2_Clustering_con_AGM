package logica_negocios;

import java.util.ArrayList;
import java.util.Collections;
import logica_negocios.Algoritmos.Arista;

public class Clustering {
	
	//variable de instancia
	private ArrayList<Arista>_pesosAristas;
	
	//constructor
	public Clustering(ArrayList<Arista> aristas){
		_pesosAristas=clonaAristas(aristas);
	}
	
	public void ordenaAristasDescendente(){//ordena la lista de aristas de mayor a menor
		Collections.sort(this._pesosAristas);
	}
	
	public ArrayList<Arista> getPesosAristas(){//devuelve una lista de aristas
		return _pesosAristas;
	}
	
	/*-- Metodos auxiliares --*/
	private ArrayList<Arista> clonaAristas(ArrayList<Arista> aristas){//clona un ArrayList de aristas
		ArrayList<Arista> aux=new ArrayList<Arista>();
		for(int i=0;i<aristas.size();i++){
			Arista actual=aristas.get(i);
			aux.add(new Arista(actual.getVertAGM(),actual.getVertice(),actual.getPeso()));
		}
		return aux;
	}
	
	public static void main(String[]args){
		GrafoPesado grafo = new GrafoPesado(5);
		grafo.agregarArista(0, 1, 5);
		grafo.agregarArista(0, 2, 6);
		grafo.agregarArista(0, 3, 10);
		grafo.agregarArista(1, 2, 1);
		grafo.agregarArista(2, 3, 5);
		grafo.agregarArista(1, 4, 4);
		grafo.agregarArista(2, 4, 10);
		grafo.agregarArista(3, 4, 15);
		
		Clustering clustering=new Clustering(Algoritmos.AGM(grafo).getAristasAGM());
		clustering.ordenaAristasDescendente();
		System.out.println(clustering.getPesosAristas().toString());
	}
}