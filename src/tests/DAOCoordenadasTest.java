package tests;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;
import modelo.Coordenada;
import modelo.DAOCoordenadas;

public class DAOCoordenadasTest
{

	private String nombreArchivo;
	
	public DAOCoordenadasTest()
	{
		this.nombreArchivo="src/modelo/instancia1.json";
	}
	
	@Test
	public void obtenerCoordenadasTest() throws IOException{
		
		ArrayList<Coordenada> listaCoordenadas=new ArrayList<Coordenada>();
		listaCoordenadas.add(obtenerInstancia());
		listaCoordenadas.add(obtenerOtraInstancia());
		
		DAOCoordenadas dao=new DAOCoordenadas(nombreArchivo);
		ArrayList<Coordenada> listaCoordenadasDesdeJson=dao.obtenerCoordenadas();
		
		assert(Double.compare(listaCoordenadas.get(0).get_latitud(),listaCoordenadasDesdeJson.get(0).get_latitud())==0);
		assert(Double.compare(listaCoordenadas.get(0).get_longitud(),listaCoordenadasDesdeJson.get(0).get_longitud())==0);
		assert(Double.compare(listaCoordenadas.get(1).get_latitud(),listaCoordenadasDesdeJson.get(1).get_latitud())==0);
		assert(Double.compare(listaCoordenadas.get(0).get_longitud(),listaCoordenadasDesdeJson.get(0).get_longitud())==0);
		
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