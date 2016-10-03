package modelo;

import java.util.ArrayList;
import logica_negocios.Algoritmos.Arista;
import logica_negocios.GrafoPesado;

public class Tupla_GrafoPesado_Aristas{

	private GrafoPesado _grafoPesado;
	private ArrayList<Arista> _aristasAGM;
	
	public Tupla_GrafoPesado_Aristas(ArrayList<Vertice> vertices){
		_grafoPesado=new GrafoPesado(vertices);
		_aristasAGM=new ArrayList<Arista>();
	}

	public void agregaAristaGrafoPesado(int aristaAgm,int arista){
		_grafoPesado.agregarArista(aristaAgm,arista);
	}
	
	public void agregaAristaAListaAgm(Arista arista){
		_aristasAGM.add(arista);
	}
	
	public GrafoPesado getGrafoPesado(){
		return _grafoPesado;
	}

	public ArrayList<Arista> getAristasAGM(){
		return _aristasAGM;
	}
}
