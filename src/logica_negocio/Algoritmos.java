package logica_negocio;

import java.util.HashSet;
import java.util.Set;

import modelo.Tupla_GrafoPesado_Aristas;
import modelo.Vertice;

public class Algoritmos
{	
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
			
				vertAMG.add(arista.vertice.getId());//se agrega AGM los vertices por prioridad de peso
				tupla.agregaAristaAListaAgm(arista);
			}
		return tupla;
	}
		
	/** Ineer class (clase interna, solo se usa en grafo)*/
	public static class Arista implements Comparable<Arista>{//adhiere a Comparable
		//variables de instancia
		public Vertice vertAGM;
		public Vertice vertice;
		public double peso;
		
		//constructor
		public Arista(Vertice verticeIncluido, Vertice verticeNoIncluido, double pesoArista)
		{
			this.vertAGM=verticeIncluido;
			this.vertice=verticeNoIncluido;
			this.peso=pesoArista;
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
			if (this.getPeso()< otraArista.getPeso()) {
				return -1;
			}
			if (this.getPeso()> otraArista.getPeso()){
				return 1;
			}
			return 0;
		}
		
		// Lectura protegida
		public Vertice getVertAGM()
		{
			return this.vertAGM;
		}
		
		public Vertice getVertice()
		{
			return this.vertice;
		}
		
		public double getPeso()
		{
			return this.peso;
		}
		
		@Override
		public String toString()
		{
			return "["+this.getVertAGM().getId()+"/"+this.getVertice().getId()+", "+this.getPeso()+"]";
		}
		
	}/**fin de clase inner*/
	
	// Retorna la arista de menor peso entre un verticeAGM y uno no AMG
	public static Arista menorArista(GrafoPesado grafo, Set<Integer>vertAGM){
		
		Arista ret=new Arista(grafo.obtenerVertice(0),grafo.obtenerVertice(0),Double.MAX_VALUE);//para ir bajando el valor
		
		for(Integer i: vertAGM){//for recorre un conjunto de Integer llamados vertAGM
			for(Integer j: grafo.vecinos(i)){//recorre un conjunto de Integer llamados vecinos de grafo
				if(vertAGM.contains(j)==false){//si el vertice de grafo esta contenido ya en el AGM
					if(grafo.getPesoArista(grafo.obtenerVertice(i),grafo.obtenerVertice(j))<ret.peso){//y si el peso entre un vertAGM y uno NoIncluido es menor que el anterior
						ret=new Arista(grafo.obtenerVertice(i),grafo.obtenerVertice(j),grafo.getPesoArista(grafo.obtenerVertice(i),grafo.obtenerVertice(j)));//convierte la arista con sus estremos y el peso
					}
				}
			}
		}
		return ret;
	}
}