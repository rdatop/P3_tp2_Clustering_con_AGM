package modelo;

import java.io.IOException;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import logica_negocios.GrafoPesado;

public class Vertice {
	//variables de instancia
	private int id;////////////
	private Double latitud;
	private Double longitud;
	
	// Constructor
	public Vertice( Double latitud, Double longitud)/////////////int id,
	{
		this.setId(generadorID.aumentarContador());//
		this.setLatitud(latitud);
		this.setLongitud(longitud);
	}
	
	public Double getLatitud() 
	{
		return this.latitud;
	}
	
	public void setLatitud(Double latitud)
	{
		this.latitud = latitud;
	}
	
	public Double getLongitud()
	{
		return this.longitud;
	}
	
	public void setLongitud(Double longitud)
	{
		this.longitud = longitud;
	}
	
	public void setId(int iD)///////// agregado
	{
		this.id=iD;
	}
	
	public int getId()//////////  agregado
	{
		return this.id;
	}
	public Coordinate getCoordenadas()////////////  agregado
	{
		Coordinate coordenadasVertice=new Coordinate(latitud, longitud);
		return coordenadasVertice;
	}
	
	public String toString()
	{
		return "ID: "+this.getId()+" , latitud: "+this.getLatitud()+", longitud: "+this.getLongitud();
	}
	
	public static class generadorID
	{
		public static int ID=0;
		
		public static int aumentarContador()
		{
			ID++;
			return ID;
		}
	}
	
	public static void main(String[]args) throws IOException{
		DAOVertices daoVertices=new DAOVertices("src/modelo/instancia1.json");
		GrafoPesado grafo = new GrafoPesado(daoVertices.obtenerVertices());
		System.out.println("Cantidad de vertices del grafo pesado: "+grafo.cantVertices());
		for (int i = 0; i <grafo.cantVertices()-1; i++) {
			grafo.obtenerVertice(i).setId(i);
			System.out.println(grafo.obtenerVertice(i).toString());/////////////////// el toString es tuyo
			grafo.agregarArista(grafo.obtenerVertice(i), grafo.obtenerVertice(i+1));
		}
		
	}
}