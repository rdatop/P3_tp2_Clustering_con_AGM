package logica_negocios;

import java.util.ArrayList;

import java.util.Set;

import modelo.Vertice;

/**
 * Grafo Pesado Utiliza la estructura de un 
 * Grafo simple(clase Grafo) y le agrega 
 * pesos a sus aristas
 * @author Pablo-Agus
 */

public class GrafoPesado 
{
	//variables de instancia
	private Grafo _grafo;//sin herencia usa un grafo simple de la clase Grafo
	private double[][] _pesos;//y le agrega peso a las aristas (GrafoPesado, peso)
	
	//constructor grafo simple + matriz de peso
	public GrafoPesado(ArrayList<Vertice> vertices)
	{
		_grafo=new Grafo(vertices);
		_pesos=new double[_grafo.cantVertices()][_grafo.cantVertices()];
	}
		
	// Agregar aristas con peso, actua la clase Convert (lat y log de los vertices)
	public void agregarArista(int idVert_i, int idVert_j)
	{
		_grafo.agregarAristas(idVert_i, idVert_j);
		Vertice vertice1=_grafo.obtenerVertice(idVert_i);
		Vertice vertice2=_grafo.obtenerVertice(idVert_j);
		Double calculaDistancia=Convert.calcularDistanciaEntre(vertice1.getLatitud(),vertice1.getLongitud(),vertice2.getLatitud(),vertice2.getLongitud());
		_pesos[idVert_i][idVert_j]=calculaDistancia;
		_pesos[idVert_j][idVert_i]=calculaDistancia;
	}
	
	// Expuesto: cant de vertices de la clase Grafo
	public ArrayList<Vertice> obtenerVertices()
	{
		return _grafo.obtenerVertices();
	}
	
	// Expuesto: obtiene un vertice segun id
	public Vertice obtenerVertice(int idVertice)
	{
		return _grafo.obtenerVertice(idVertice);
	}
		
	// Peso de una arista entre dos vertices
	public double getPesoArista(int idVert_a, int idVert_b)
	{
		if(_grafo.contieneArista(idVert_a, idVert_b)==false){
			throw new IllegalArgumentException("Se consulto el peso de una arista inexistente! " + idVert_a + ", " + idVert_b);
		}
		return _pesos[idVert_a][idVert_b];
	}
	
	// Expuesto: contine aristas sin herencia de la class Grafo (no es static en la clase Grafo)
	public boolean contieneArista(int vert_i, int vert_j)
	{
		return _grafo.contieneArista(vert_i, vert_j);
	}
	
	// Expuesto: cant de vertices sin herencia de la class Grafo (no es static en la clase Grafo)
	public int cantVertices()
	{
		return _grafo.cantVecinos();
	}
	
	// Lectura protegida cant de vertices
	public int getCantVertices()
	{
		return cantVertices();
	}
	
	// Expuesto: cant de aristas sin herencia de la class Grafo
	public int cantAristas()
	{
		return _grafo.cantAristas();
	}
	
	// Lectura protegida cant de aristas
	public int getCantAristas()
	{
		return cantAristas();
	}
	
	// Expuesto: conjunto de vecinos de un vértice
	public Set<Integer> vecinos(int id)
	{
		return _grafo.vecinosDelVertice(id);
	}
}
