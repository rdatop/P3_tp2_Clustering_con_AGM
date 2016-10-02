package logica_negocios;

import java.util.Set;

/**
 * Grafo Pesado Utiliza la estructura de un 
 * GRAFO simple(clase Grafo) y le agrega 
 * pesos a sus aristas
 * @author Agus-MSI
 */

public class GrafoPesado {
	//variables de instancia
	private Grafo _grafo;//sin herencia usa un grafo simple de la clase Grafo
	private double[][] _pesos;//y le agrega peso a las aristas (GrafoPesado, peso)
	
	//constructor
	public GrafoPesado(int vertices){//una las dos componentes grafo simple + peso
		//TODO inicializar bien el grafo simple tomando en cuenta loas cambios agregados
		//a este
		_grafo=new Grafo(null);
		_pesos=new double[vertices][vertices];
	}
	
	//agregar aristas con peso
	public void agregarArista(int vert_i, int vert_j, double peso){
		_grafo.agregarAristas(vert_i, vert_j);//usa tambien el contiene aristas/cheq extremos de grafo simple
		_pesos[vert_i][vert_j]=peso;//
		_pesos[vert_j][vert_i]=peso;//agrega de forma simetrica
	}
	
	//Expuesto: repatria en contine aristas sin herencia de la class Grafo
	public boolean contieneArista(int vert_i, int vert_j){//porque no es static en la clase Grafo
		return _grafo.contieneArista(vert_i, vert_j);
	}
	
	//entrega el peso de una arista determinada entre dos vertices
	public double getPesoArista(int vert_a, int vert_b){
		if(_grafo.contieneArista(vert_a, vert_b)==false){//usa el contiene aristas repatriado de grafo simple
			throw new IllegalArgumentException("Se consulto el peso de una arista inexistente! " + vert_a + ", " + vert_b);
		}
		return _pesos[vert_a][vert_b];//si no es falso el cont arist entrega el peso de la arista
	}
			
	//Expuesto: repatria la cant de vertices sin herencia de la class Grafo
	public int CantVertices(){//porque no es static en la clase Grafo
		return _grafo.CantVertices();
	}
	
	//Expuesto: repatria la cant de aristas sin herencia de la class Grafo
	public int Cantaristas(){
		return _grafo.CantAristas();
	}
	
	// Expuesto desde Grafo: Conjunto de vecinos de un vértice
	public Set<Integer> vecinos(int i){
		return _grafo.vecinosDelVertice(i);
	}
	
	public int getCantVertices(){
		return CantVertices();
	}
	
}
