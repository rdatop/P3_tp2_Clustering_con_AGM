package logica_negocios;

import java.util.ArrayList;
import java.util.HashSet;

import java.util.Set;


public class Algoritmos{//
	
	//CREAR UNA VARIABLE DE INSTANCIA QUE GUARDE LAS ARISTAS DEL AGM PARA DESPUES ORDENARLAS
	//E INVERTIRLAS PARA ARMAR LA COLA DE PRIORIDADES
	public static ArrayList<Arista> _aristasAGM=new ArrayList<Arista>();////////nuevo
	
	public static ArrayList<Double> _pesosAristas=new ArrayList<Double>();//////nuevo
	
	
	// Algoritmo de Prim
	public static GrafoPesado AGM(GrafoPesado grafo){
		GrafoPesado resultadoPrim=new GrafoPesado(grafo.CantVertices());//grafo con el peso de sus aristas
		Set<Integer>vertAMG=new HashSet<Integer>();//vertices del arbol generador minimo
		vertAMG.add(0);//le agrego algo
		
		for(int i=0;i<grafo.CantVertices()-1;i++){//recorro los vertices del grafo hasta el ultimo
			Arista arista=menorArista(grafo, vertAMG);//crea una arista que sera la de menor valor
			resultadoPrim.agregarArista(arista.vertAGM, arista.vertice, arista.peso);//se le agrega al agm 
			//los vertices con la arista de menor peso
			
			vertAMG.add(arista.vertice);//se le agrega todos los vertices negros al agm por prioridad de peso
			_aristasAGM.add(arista);//////////nuevo
			_pesosAristas.add(arista.getPeso());
			//System.out.println(arista.vertice+" "+arista.peso);
			//mostrar(_aristasAGM);//////////////
		}
		
		//ArrayList<Arista> prueba=_aristasAGM;
		return resultadoPrim;
	}
		
	//ineer class (clase interna, solo se usa en grafo)
	public static class Arista{
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
		//equals de object sobre escrito
		@Override
		public boolean equals(Object obj){
			if(this==obj){
				return true;
			}
			if(obj==null||getClass()!=obj.getClass()){
				return false;
			}
			Arista otra=(Arista)obj;
			return vertAGM==otra.vertAGM&&vertice==otra.vertice;
		}
		
		public int getVertAGM() {
			return vertAGM;
		}
		
		public int getVertice() {
			return vertice;
		}
		
		public double getPeso() {
			return peso;
		}
		
		@Override
		public String toString(){
			return "["+getPeso()+"]";//+getVertAGM()+"/"+getVertice()+","+
		}
				
	}//fin de clase inner
	
	//paquete privado(static sin public/private/protected)
	// Retorna la arista de menor peso entre un vertice amarillo y uno no amarillo
	static Arista menorArista(GrafoPesado grafo, Set<Integer>vertAGM){
		
		Arista ret=new Arista(0,0,Double.MAX_VALUE);//para ir vajando el valor
		
		for(Integer i: vertAGM){//for recorre un conjunto de Integer llamados vertAGM
			for(Integer j: grafo.vecinos(i)){//recorre un conjunto de Integer llamados vecinos de grafo
				if(vertAGM.contains(j)==false){//si el vertice de grafo esta contenido ya en el agm
					if(grafo.getPesoArista(i, j)<ret.peso){//y si el peso entre un vertAGM y uno NoIncluido es menor que el anterior
						ret=new Arista(i,j,grafo.getPesoArista(i,j));//convierte la arista con sus estremos y el peso
					}
				}
			}
		}
		return ret;
	}
	
	/** para ver la arista mayor este los vecinos*/
	// Retorna la arista de mayor peso entre un vertice amarillo y uno no amarillo
	static Arista mayorArista(GrafoPesado grafo, Set<Integer>vertAGM){
		Arista ret=new Arista(0,0,Double.MIN_VALUE);//para ir vajando el valor
		
		for(Integer i: vertAGM){//for recorre un conjunto de Integer llamados vertAGM
			for(Integer j: grafo.vecinos(i)){//for recorre un conjunto de Integer llamados vecinos de grafo
				if(vertAGM.contains(j)==false){//si el vertice de grafo esta contenido ya en el agm
					if(grafo.getPesoArista(i, j)>ret.peso){//y si el peso entre un vertAGM y uno NoIncluido es menor que el anterior
						ret=new Arista(i,j,grafo.getPesoArista(i,j));//retorna la arista con sus estremos y el peso
					}
				}
			}
		}
		return ret;
	}
	
	public static ArrayList<Double> ordenar(ArrayList<Double> pesosAristas){
		ArrayList<Double>pesosOrdenados=new ArrayList<Double>();
		Double max=Double.MAX_VALUE;
		for (int i = 0; i < pesosAristas.size(); i++) {
			if(pesosAristas.get(i)<max){
				pesosOrdenados.add(pesosAristas.get(i));
			}
		}
		return pesosOrdenados;
		
	}


	//////////////////prueba de empleo luego borar
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
		
		
		
		
		Algoritmos.AGM(grafo);
		System.out.println(_aristasAGM.toString());
		System.out.println(_pesosAristas.toString());
		ArrayList<Double>nuevo=new ArrayList<Double>();
		nuevo=ordenar(_pesosAristas);
		System.out.println(nuevo.toString());
		
		
		

	}
	
	

}


