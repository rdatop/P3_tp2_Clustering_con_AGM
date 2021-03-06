package logica_negocio;

import java.util.ArrayList;
import java.util.Set;
import modelo.Vertice;

/**
 * Grafo Pesado Utiliza la estructura de un 
 * Grafo simple(clase Grafo) y le agrega 
 * pesos a sus aristas
 * @author Pabluntu-Agus
 */

public class GrafoPesado
{
	//variables de instancia
	private Grafo _grafo;//sin herencia usa un grafo simple de la clase Grafo
	private double[][] _pesos;//y le agrega peso a las aristas (GrafoPesado, peso)
	
	//constructor grafo simple + matriz de peso
	public GrafoPesado(ArrayList<Vertice> vertices)
	{
		this._grafo=new Grafo(vertices);
		this._pesos=new double[_grafo.cantVertices()][_grafo.cantVertices()];
	}
		
	// Agregar aristas con peso, actua la clase Convert (lat y log de los vertices)
	public void agregarArista(Vertice idVert_i, Vertice idVert_j)
	{
		this._grafo.agregarAristas(idVert_i, idVert_j);
		Vertice vertice1=_grafo.obtenerVertice(idVert_i.getId());
		Vertice vertice2=_grafo.obtenerVertice(idVert_j.getId());
		Double calculaDistancia=Convert.calcularDistanciaEntre(vertice1.getLatitud(),vertice1.getLongitud()
				                                              ,vertice2.getLatitud(),vertice2.getLongitud());
		this._pesos[idVert_i.getId()][idVert_j.getId()]=calculaDistancia;
		this._pesos[idVert_j.getId()][idVert_i.getId()]=calculaDistancia;//peso de forma simetrica
	}
	
	// Expuesto: cant de vertices de la clase Grafo
	public ArrayList<Vertice> obtenerVertices()
	{
		return this._grafo.obtenerVertices();
	}
	
	// Expuesto: obtiene un vertice segun id
	public Vertice obtenerVertice(int idVertice)
	{
		return this._grafo.obtenerVertice(idVertice);
	}
		
	// Peso de una arista entre dos vertices
	public double getPesoArista(int idVert_a, int idVert_b)
	{
		if(this._grafo.contieneArista(idVert_a,idVert_b)==false)
		{
			throw new IllegalArgumentException("Se consulto el peso de una arista inexistente! " + idVert_a + ", " + idVert_b);
		}
		return this._pesos[idVert_a][idVert_b];
	}
	
	// Expuesto: contine aristas sin herencia de la class Grafo
	public boolean contieneArista(int vert_i, int vert_j)
	{
		return this._grafo.contieneArista(vert_i, vert_j);
	}
	
	// Expuesto: cant de vertices sin herencia de la class Grafo
	public int cantVertices()
	{
		return this._grafo.cantVecinos();
	}
	
	// Lectura protegida cant de vertices
	public int getCantVertices()
	{
		return this.cantVertices();
	}
	
	// Expuesto: cant de aristas sin herencia de la class Grafo
	public int cantAristas()
	{
		return this._grafo.cantAristas();
	}
	
	// Lectura protegida cant de aristas
	public int getCantAristas()
	{
		return this.cantAristas();
	}
	
	// Expuesto: conjunto de vecinos de un v�rtice
	public Set<Integer> vecinos(int id)
	{
		return this._grafo.vecinosDelVertice(id);
	}
}