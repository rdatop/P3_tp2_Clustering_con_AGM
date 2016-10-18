package logica_negocio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import modelo.Vertice;

public class Grafo
{
	//variables de instancia
	private ArrayList<Vertice> _vertices;
	private ArrayList<HashSet<Integer>> _vecinos;
	private int _aristas;//se iran sumando a medida que se agregen
	
	//constructor
	public Grafo(ArrayList<Vertice> listaVertices)
	{
		this._vertices=listaVertices;
		this._vecinos=new ArrayList<HashSet<Integer>>();
		for (int i = 0; i < this.cantVertices(); i++)
		{
			this._vecinos.add(new HashSet<Integer>());
		}
	}
	
	// Entrega la lista de vertices
	public ArrayList<Vertice> obtenerVertices()
	{
		return this._vertices;
	}
	
	// Entrega un vertice particular
	public Vertice obtenerVertice(int idVertice)
	{
		return this._vertices.get(idVertice);
	}
	
	// Cant de vertices de grafo
	public int cantVertices()
	{
		return this._vertices.size();
	}
	
	// Cant de Vecinos
	public int cantVecinos()
	{
		return this._vecinos.size();
	}
	
	// Cant de Aristas
	public int cantAristas()
	{
		return this._aristas;
	}
	
	// Relaciona dos vertices mediante una aristas
	public void agregarAristas(Vertice idVert_i, Vertice idVert_j)
	{
		this.chequearExtremos(idVert_i.getId(), idVert_j.getId());
		if(!this.contieneArista(idVert_i.getId(),idVert_j.getId()))
		{
			this._aristas++;//incremente cant aristas
		}
		this._vecinos.get(idVert_i.getId()).add(idVert_j.getId());
		this._vecinos.get(idVert_j.getId()).add(idVert_i.getId());
	}
	
	// Verifica asignacion entre vertices
	public boolean contieneArista(int idVert_i, int idVert_j)
	{
		this.chequearExtremos(idVert_i,idVert_j);
		return this._vecinos.get(idVert_i).contains(idVert_j);
	}

	// Verifica limites de los parametros
	private void chequearExtremos(int idVert_i, int idVert_j)
	{
		if (idVert_i <= -1 || idVert_j <= -1 || idVert_i >= this.cantVecinos() || idVert_j >= this.cantVecinos())//verifica rango
		{
			throw new IllegalArgumentException("Vertices fuera de rango: " + idVert_i + ", " + idVert_j + " (vertices = " + cantVecinos() + ")");
		}
		if(idVert_i==idVert_j)//verifica que lo tenga bucles
		{
			throw new IllegalArgumentException("No se pueden agregar loops: " + idVert_i);
		}
	}
	
	// Entrega el grado del vertice
	public int gradoDelVertice(int idVertice)
	{
		return this._vecinos.get(idVertice).size();
	}
	
	// Entrega los vecinos del vertice
	public Set<Integer> vecinosDelVertice(int idVertice)
	{
		Set<Integer> ret=this._vecinos.get(idVertice);
		return ret;
	}

	// Verifica la existencia de un vertice tomando como parámetro su id
	public boolean contieneVertice(int idVertice)
	{
		return idVertice >= 0 && idVertice <= cantVertices();
	}
}