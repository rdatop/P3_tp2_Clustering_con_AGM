package modelo;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class DAOVertices {

	private Gson gson;
	@SuppressWarnings("unused")
	private String nombreArchivo;
	private ArrayList<Vertice> listaCoordenadas;
	
	public DAOVertices(String nombreArchivo)throws IOException
	{
		this.gson=new Gson();
		this.nombreArchivo=nombreArchivo;
		//this.listaCoordenadas=this.desserializaJson(this.nombreArchivo);//este tiene que ir
		this.listaCoordenadas=this.creaListaVerticesHardcodeada();
	}

	public ArrayList<Vertice> obtenerVertices()//devuelve lista de coordenadas
	{
		return this.listaCoordenadas;
	}
	
	public ArrayList<Vertice> desserializaJson(String nombreArchivo)throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
		
		Type collectionType=new TypeToken<ArrayList<Vertice>>(){}.getType();
		
		ArrayList<Vertice> listaCoordenadas=gson.fromJson(br,collectionType);
		
		if(listaCoordenadas==null)//s� el archivo est� vacio
		{
			listaCoordenadas=new ArrayList<Vertice>();//hago una lista vac�a
		}
		
		return listaCoordenadas;
	}
	
	private ArrayList<Vertice> creaListaVerticesHardcodeada() {
		ArrayList<Vertice> aux=new ArrayList<Vertice>();
		aux.add(new Vertice(-34.52133782929332,-58.70068073272705));
		aux.add(new Vertice(-34.520772089706036,-58.702311515808105));
		aux.add(new Vertice(-34.52126711205503,-58.70325565338135));
		aux.add(new Vertice(-34.52186820666683,-58.70265483856201));
		return aux;
	}
}