package tests;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;
import modelo.Coordenada;
import modelo.DAOCoordenadas;

public class DAOCoordenadasTest
{

	private String nombreArchivo;
	
	public DAOCoordenadasTest()
	{
		this.nombreArchivo="src/modelo/instancia1.json";
	}
	
	
	
	public void obtenerCoordenadasTest() throws IOException{
		ArrayList<Coordenada> listaCoordenadas=new ArrayList<Coordenada>();
		listaCoordenadas.add(obtenerInstancia());
		listaCoordenadas.add(obtenerOtraInstancia());
		
		DAOCoordenadas dao=new DAOCoordenadas(nombreArchivo);
		ArrayList<Coordenada> listaCoordenadasDesdeJson=dao.obtenerCoordenadas();
		
		assert(listaCoordenadas.get(0).equals(listaCoordenadasDesdeJson.get(0)));
		assert(listaCoordenadas.get(1).equals(listaCoordenadasDesdeJson.get(1)));
	}
	
	private Coordenada obtenerInstancia(){
		Coordenada coordenada=new Coordenada(-34.52133782929332,-58.70068073272705);
		return coordenada;
	}
	
	private Coordenada obtenerOtraInstancia(){
		Coordenada coordenada=new Coordenada(-34.520772089706036,-58.702311515808105);
		return coordenada;
	}

}