package logica_negocios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Grafo {
	
	private ArrayList<Set<Integer>> _vecinos;
	private int _aristas;//se iran sumando a medida que se agregen
	
	//constructor con invariante de representacion
	public Grafo(int vertices){
		if(vertices<0){
			throw new IllegalArgumentException("cantidad de vertives negativo: "+vertices);
		}else{
			_vecinos=new ArrayList<Set<Integer>>();
		}
		for (int i = 0; i < vertices; i++) {
			_vecinos.add(new HashSet<Integer>());
		}
	}
	
	//cantidad de vertices
	public int cantVertices(){
		return _vecinos.size();
	}
	
	//cantidad de aristas
	public int cantAristas(){
		return _aristas;
	}
	
	//agrega, relaciona dos vertices mediante una aristas
	public void agregarAristas(int vert_i, int vert_j){
		chequearExtremos(vert_i, vert_j);//verifica rangos
		if(!contieneArista(vert_i,vert_j)){//verifica que no esten relacionados
			_aristas++;//suma cant aristas
		}
		_vecinos.get(vert_i).add(vert_j);//
		_vecinos.get(vert_j).add(vert_i);//asignacion simetrica
	}
	
	//elimida, desvincula dos vertices eliminando la arista q los unio
	public void removerArista(int vert_i, int vert_j){
		chequearExtremos(vert_i, vert_j);
		if(contieneArista(vert_i, vert_j)){//si esta asignado
			_aristas --;//resta una arista 
		}
		_vecinos.get(vert_i).remove(vert_j);//
		_vecinos.get(vert_j).remove(vert_i);//des asignacion asimetrica
	}
		
	//verifica relacion entre vertices
	public boolean contieneArista(int vert_i, int vert_j){
		chequearExtremos(vert_i,vert_j);
		return _vecinos.get(vert_i).contains(vert_j);

	}

	//chequea limites de los parametros
	private void chequearExtremos(int vert_i, int vert_j) {
		if (vert_i <= -1 || vert_j <= -1 || vert_i >= cantVertices() || vert_j >= cantVertices()){//verifica rango
			throw new IllegalArgumentException("Vertices fuera de rango: " + vert_i + ", " + vert_j + " (vertices = " + cantVertices() + ")");
		}
		if(vert_i==vert_j){//verifica que lo tenga rulos
			throw new IllegalArgumentException("No se pueden agregar loops: " + vert_i);
		}
	}
	
	//entrega el grado del vertice
	public int gradoDelVertice(int vertice){
		return _vecinos.get(vertice).size();
	}
	
	//entrega los vecinos del vertice
	public Set<Integer> vecinosDelVertice(int v){
		Set<Integer> ret=_vecinos.get(v);
		return ret;
	}
	
	//toString de representacion no se usa mas
//	@Override
//	public String toString(){
//		String s="";
//		for(Set<Integer> row:vecinos){
//			for(Integer item:row){
//				s+=item+" ";
//			}
//			s+="\n";
//		}
//		return s;
//	}	
	
}
