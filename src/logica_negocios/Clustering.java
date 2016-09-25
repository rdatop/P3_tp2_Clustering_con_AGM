package logica_negocios;

import java.util.ArrayList;


public class Clustering {

	private static ArrayList<Double>_pesosAristas;
	
	public Clustering(ArrayList<Double> _pesosAristas){
		this._pesosAristas=new ArrayList<Double>();
		this._pesosAristas=(ArrayList<Double>) Algoritmos._pesosAristas.clone();
				
		
	}
	
	
	public static void mostrar(ArrayList<Double> _pesosAristas2){
		for (int i = 0; i <_pesosAristas.size()-1; i++) {
			System.out.println(  "["+i+"]");//+getVertAGM()+"/"+getVertice()+","+
		}
	}
	
	
	
	
	
	
	
	public static void main(String[]args){
	mostrar(_pesosAristas);
	
	}
}
