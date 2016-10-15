package modelo;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Vertice {
	//variables de instancia
	private int id;////////////
	private Double latitud;
	private Double longitud;
	
	// Constructor
	public Vertice(Integer id, Double latitud, Double longitud)/////////////
	{
		this.setId(id);
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
	
}