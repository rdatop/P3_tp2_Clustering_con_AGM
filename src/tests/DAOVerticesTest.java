package tests;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;
import modelo.Vertice;
import modelo.DAOVertices;

public class DAOVerticesTest
{

	private String nombreArchivo;
	
	public DAOVerticesTest()
	{
		this.nombreArchivo="src/modelo/instancia1.json";
	}
	
	@Test
	public void obtenerCoordenadasTest() throws IOException{
		
		ArrayList<Vertice> listaVertices=new ArrayList<Vertice>();
		listaVertices.add(obtenerInstancia());
		listaVertices.add(obtenerOtraInstancia());
		
		DAOVertices dao=new DAOVertices(nombreArchivo);
		ArrayList<Vertice> listaVerticesDesdeJson=dao.obtenerVertices();
		
		assert(Double.compare(listaVertices.get(0).getLatitud(),listaVerticesDesdeJson.get(0).getLatitud())==0);
		assert(Double.compare(listaVertices.get(0).getLongitud(),listaVerticesDesdeJson.get(0).getLongitud())==0);
		assert(Double.compare(listaVertices.get(1).getLatitud(),listaVerticesDesdeJson.get(1).getLatitud())==0);
		assert(Double.compare(listaVertices.get(0).getLongitud(),listaVerticesDesdeJson.get(0).getLongitud())==0);
		
	}
	
	private Vertice obtenerInstancia(){
		Vertice vertice=new Vertice(-34.52133782929332,-58.70068073272705);
		return vertice;
	}
	
	private Vertice obtenerOtraInstancia(){
		Vertice vertice=new Vertice(-34.520772089706036,-58.702311515808105);
		return vertice;
	}

}