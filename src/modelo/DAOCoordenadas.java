package modelo;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class DAOCoordenadas {

	private Gson gson;
	private String nombreArchivo;
	private ArrayList<Coordenada> listaCoordenadas;
	
	public DAOCoordenadas(String nombreArchivo)throws IOException
	{
		this.gson=new Gson();
		this.nombreArchivo=nombreArchivo;
		this.listaCoordenadas=this.desserializaJson(this.nombreArchivo);
	}

	public ArrayList<Coordenada> obtenerCoordenadas()//devuelve lista de coordenadas
	{
		return this.listaCoordenadas;
	}
	
	public ArrayList<Coordenada> desserializaJson(String nombreArchivo)throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
		
		Type collectionType=new TypeToken<ArrayList<Coordenada>>(){}.getType();
		
		ArrayList<Coordenada> listaCoordenadas=gson.fromJson(br,collectionType);
		
		if(listaCoordenadas==null)//sí el archivo está vacio
		{
			listaCoordenadas=new ArrayList<Coordenada>();//hago una lista vacía
		}
		
		return listaCoordenadas;
	}
}