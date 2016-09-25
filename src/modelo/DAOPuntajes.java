package modelo;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class DAOPuntajes {

	private Gson gson;
	private String nombreArchivo;
	private ArrayList<Coordenada> listaPuntajes;
	
	public DAOPuntajes(String nombreArchivo)throws IOException
	{
		this.gson=new Gson();
		this.nombreArchivo=nombreArchivo;
		this.listaPuntajes=this.desserializaJson(this.nombreArchivo);
	}
	
	public boolean agregarPuntaje(Coordenada puntaje)//agrega un puntaje a la lista
	//de puntajes y lo registra en un archivo json
	{
		try {
		
			this.listaPuntajes.add(puntaje);
			
			String puntajesJson=this.gson.toJson(this.listaPuntajes);
			
			ManejadorArchivos.escribirGuardarArchivo(this.nombreArchivo,puntajesJson);
			
			return true;
		}
		catch (Exception excepcion) {
			return false;
		}	
	}
	
	public ArrayList<Coordenada> obtenerPuntajes()//devuelve lista de puntajes y los 
	//usuarios que los obtuvieron
	{
		return this.listaPuntajes;
	}
	
	public ArrayList<Coordenada> desserializaJson(String nombreArchivo)throws IOException
	{
		String json=ManejadorArchivos.leerArchivo(nombreArchivo);
		
		Type collectionType=new TypeToken<ArrayList<Coordenada>>(){}.getType();
		
		ArrayList<Coordenada> listaPuntajes=gson.fromJson(json,collectionType);
		
		if(listaPuntajes==null)//sí el archivo está vacio
		{
			listaPuntajes=new ArrayList<Coordenada>();//hago una lista vacía
		}
		
		return listaPuntajes;
	}
}