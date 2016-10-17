package modelo;

import java.util.ArrayList;
import logica_negocios.Algoritmos.Arista;
import logica_negocios.GrafoPesado;

public class Tupla_GrafoPesado_Aristas{
	//variables de instancia
	private GrafoPesado _grafoPesado;
	private ArrayList<Arista> _aristasAGM;
	
	// Cosntrutor
	public Tupla_GrafoPesado_Aristas(ArrayList<Vertice> vertices){
		_grafoPesado=new GrafoPesado(vertices);
		_aristasAGM=new ArrayList<Arista>();
	}

	// Agrega aristas del grafo pesado
	public void agregaAristaGrafoPesado(Vertice aristaAgm,Vertice arista){//int
		_grafoPesado.agregarArista(aristaAgm,arista);
	}
	
	// Construye una lista con los vertices y aristas del AGM
	public void agregaAristaAListaAgm(Arista arista){
		_aristasAGM.add(arista);
	}
	
	// Lectura protegida de grafo Pesado
	public GrafoPesado getGrafoPesado(){
		return _grafoPesado;
	}

	// Lectura protegida de la lista de vertices y aristas del AGM
	public ArrayList<Arista> getAristasAGM(){
		return _aristasAGM;
	}
	
	//Expuesto: contine aristas sin herencia de la class GrafoPesado/Grafo
	public boolean getContieneArista(int vert_i, int vert_j) {
		return _grafoPesado.contieneArista(vert_i, vert_j);
	}
	
    //Expuesto: cant de aristas sin herencia de la class GrafoPesado/Grafo
	public int getCantAristas() {
		return _grafoPesado.cantAristas();
	}
	
}
