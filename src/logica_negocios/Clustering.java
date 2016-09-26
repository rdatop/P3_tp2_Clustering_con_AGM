package logica_negocios;

import java.util.ArrayList;

import logica_negocios.Algoritmos.Arista;


public class Clustering {
	
	//variable de instancia
	private static ArrayList<Arista>_pesosAristas;
	
	//constructor
	public Clustering(ArrayList<Arista> _pesosAristas){
		this._pesosAristas=new ArrayList<Arista>();
		//this._pesosAristas=(ArrayList<Arista>) Algoritmos._aristasAGM.clone();
	}
	
	//////// pablito en este punto deberiamos traer el ArrayList de aristas ordenado de mayor a menor
	//////// no me da bola el .clone que intente mepa

	
	public static void main(String[]args){
	System.out.println(_pesosAristas.toString());
	
	}
}
