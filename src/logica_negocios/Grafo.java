package logica_negocios;

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
		_vertices=listaVertices;
		_vecinos=new ArrayList<HashSet<Integer>>();
		for (int i = 0; i < cantVertices(); i++) {
			_vecinos.add(new HashSet<Integer>());
		}
	}
	
	// Entrega la lista de vertices
	public ArrayList<Vertice> obtenerVertices()
	{
		return _vertices;
	}
	
	// Entrega un vertice particular
	public Vertice obtenerVertice(int idVertice)
	{
		return _vertices.get(idVertice);
	}
	
	// Cant de vertices de grafo
	public int cantVertices()
	{
		return _vertices.size();
	}
	
	// Cant de Vecinos
	public int cantVecinos()
	{
		return _vecinos.size();
	}
	
	// Cant de Aristas
	public int cantAristas()
	{
		return _aristas;
	}
	
	// Relaciona dos vertices mediante una aristas
	public void agregarAristas(int idVert_i, int idVert_j)
	{
		chequearExtremos(idVert_i, idVert_j);
		if(!contieneArista(idVert_i,idVert_j))
		{
			_aristas++;
		}
		_vecinos.get(idVert_i).add(idVert_j);//
		_vecinos.get(idVert_j).add(idVert_i);//asignacion simetrica
	}
	
	// Desvincula dos vertices eliminando la arista q los unio
	public void removerArista(int idVert_i, int idVert_j)
	{
		chequearExtremos(idVert_i, idVert_j);
		if(contieneArista(idVert_i, idVert_j))
		{
			_aristas --; 
		}
		_vecinos.get(idVert_i).remove(idVert_j);//
		_vecinos.get(idVert_j).remove(idVert_i);//desasignacion asimetrica
	}
		
	// Verifica asignacion entre vertices
	public boolean contieneArista(int idVert_i, int idVert_j)
	{
		chequearExtremos(idVert_i,idVert_j);
		return _vecinos.get(idVert_i).contains(idVert_j);
	}

	// Verifica limites de los parametros
	private void chequearExtremos(int idVert_i, int idVert_j) 
	{
		if (idVert_i <= -1 || idVert_j <= -1 || idVert_i >= cantVecinos() || idVert_j >= cantVecinos())
		{
			throw new IllegalArgumentException("Vertices fuera de rango: " + idVert_i + ", " + idVert_j + " (vertices = " + cantVecinos() + ")");
		}
		if(idVert_i==idVert_j)
		{//verifica que lo tenga bucles
			throw new IllegalArgumentException("No se pueden agregar loops: " + idVert_i);
		}
	}
	
	// Entrega el grado del vertice
	public int gradoDelVertice(int idVertice)
	{
		return _vecinos.get(idVertice).size();
	}
	
	// Entrega los vecinos del vertice
	public Set<Integer> vecinosDelVertice(int idVertice)
	{
		Set<Integer> ret=_vecinos.get(idVertice);
		return ret;
	}

	// Verifica la existencia de un vertice tomando como parámetro su id
	public boolean contieneVertice(int idVertice)
	{
		return idVertice >= 0 && idVertice <= cantVertices();
	}
}
