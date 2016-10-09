package modelo;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class DAOVertices 
{
	//variables de instancia
	private Gson _gson;
	private String nombreArchivo;
	private ArrayList<Vertice> listaCoordenadas;
	
	// cosntructor
	public DAOVertices(String nombreArchivo)throws IOException
	{
		this._gson=new Gson();
		this.nombreArchivo=nombreArchivo;
		this.listaCoordenadas=this.desserializaJson(this.nombreArchivo);
	}

	// Retorna lista de coordenadas
	public ArrayList<Vertice> obtenerVertices()
	{
		return this.listaCoordenadas;
	}
	
	// Desserializa y retorna una lista de coordenadas
	private ArrayList<Vertice> desserializaJson(String nombreArchivo)throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
		Type collectionType=new TypeToken<ArrayList<Vertice>>(){}.getType();
		ArrayList<Vertice> listaCoordenadas=_gson.fromJson(br,collectionType);
		if(listaCoordenadas==null)//s� el archivo est� vacio
		{
			listaCoordenadas=new ArrayList<Vertice>();//hara una lista vac�a
		}
		return listaCoordenadas;
	}
}