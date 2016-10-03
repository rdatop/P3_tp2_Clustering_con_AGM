package logica_negocios;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import modelo.DAOVertices;
import modelo.Tupla_GrafoPesado_Aristas;

public class Algoritmos{
	//sin constructor
	
	// Algoritmo de Prim
	public static Tupla_GrafoPesado_Aristas AGM(GrafoPesado grafo){
		//Tupla: grafoPesado + lista de aristas del AGM (dirigido a Clustering)
		Tupla_GrafoPesado_Aristas tupla=new Tupla_GrafoPesado_Aristas(grafo.obtenerVertices());
		Set<Integer>vertAMG=new HashSet<Integer>();//vertices del AGM
		vertAMG.add(0);//se inicializa
		
		for(int i=0;i<grafo.cantVertices()-1;i++){//recorro los vertices del grafo hasta el ultimo
			Arista arista=menorArista(grafo, vertAMG);//crea una arista que sera la de menor valor
			tupla.agregaAristaGrafoPesado(arista.vertAGM, arista.vertice);//se agrega al AGM 
			//los vertices con la arista de menor peso
			
			vertAMG.add(arista.vertice);//se agrega AGM los vertices por prioridad de peso
			tupla.agregaAristaAListaAgm(arista);
		}
		return tupla;
	}
		
	/** Ineer class (clase interna, solo se usa en grafo)*/
	public static class Arista implements Comparable<Arista>{//adhiere a Comparable
		//variables de instancia
		public int vertAGM;
		public int vertice;
		public double peso;
		
		//constructor
		public Arista(int verticeIncluido, int verticeNoIncluido, double pesoArista){
			vertAGM=verticeIncluido;
			vertice=verticeNoIncluido;
			peso=pesoArista;
		}
		
		// Equals de object sobreescrito (comparacion por igualdad)
		@Override
		public boolean equals(Object obj){
			if(this==obj){
				return true;
			}
			if(obj==null||getClass()!=obj.getClass()){
				return false;
			}
			Arista otra=(Arista)obj;
			return vertAGM==otra.vertAGM && vertice==otra.vertice;
		}
		
		// CompareTo sobreescrito (comparacion por peso de aristas - Clustering)
		@Override
		public int compareTo(Arista otraArista) {
			if (getPeso()< otraArista.getPeso()) {
				return 1;
			}
			if (getPeso()> otraArista.getPeso()){
				return -1;
			}
			return 0;
		}
		
		// Lectura protegida
		public int getVertAGM() {
			return vertAGM;
		}
		
		public int getVertice() {
			return vertice;
		}
		
		public double getPeso() {
			return peso;
		}
		
		///////////////representacion luego borrar
		@Override
		public String toString(){
			return "["+getVertAGM()+"/"+getVertice()+", "+getPeso()+"]";
		}
	}/**fin de clase inner*/
	
	// Retorna la arista de menor peso entre un verticeAGM y uno no AMG
	public static Arista menorArista(GrafoPesado grafo, Set<Integer>vertAGM){
		
		Arista ret=new Arista(0,0,Double.MAX_VALUE);//para ir bajando el valor
		
		for(Integer i: vertAGM){//for recorre un conjunto de Integer llamados vertAGM
			for(Integer j: grafo.vecinos(i)){//recorre un conjunto de Integer llamados vecinos de grafo
				if(vertAGM.contains(j)==false){//si el vertice de grafo esta contenido ya en el AGM
					if(grafo.getPesoArista(i, j)<ret.peso){//y si el peso entre un vertAGM y uno NoIncluido es menor que el anterior
						ret=new Arista(i,j,grafo.getPesoArista(i,j));//convierte la arista con sus estremos y el peso
					}
				}
			}
		}
		return ret;
	}
	

	//////////////////prueba de empleo luego borrar
	public static void main(String[]args) throws IOException{
		DAOVertices daoVertices=new DAOVertices("src/modelo/instancia1.json");
		GrafoPesado grafo = new GrafoPesado(daoVertices.obtenerVertices());
		grafo.agregarArista(0, 1);
		grafo.agregarArista(0, 2);
		grafo.agregarArista(0, 3);
		grafo.agregarArista(1, 2);
		grafo.agregarArista(2, 3);
		System.out.println("Peso: "+grafo.getPesoArista(1,2));
		
		System.out.println("Cantidad de vertices del grafo pesado: "+grafo.cantVertices());
		
		Tupla_GrafoPesado_Aristas tupla=Algoritmos.AGM(grafo);
		System.out.println("asi se crea el AGM");
		System.out.println(tupla.getAristasAGM().toString());
		System.out.println("asi se ordena de > a <");
		Collections.sort(tupla.getAristasAGM());//ordena de mayot a menor gracias a la implementacion de compareTo en Arista
		System.out.println(tupla.getAristasAGM().toString());
		System.out.println("asi queda definitivamente");
		System.out.println(tupla.getAristasAGM().toString());
		
	}
}