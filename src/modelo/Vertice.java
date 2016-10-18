package modelo;

public class Vertice
{
	//variables de instancia
	private int id;
	private Double latitud;
	private Double longitud;
	
	// Constructor
	public Vertice( int id,Double latitud, Double longitud)
	{
		this.setId(id);//
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
	
	public void setId(int iD)
	{
		this.id=iD;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public String toString()
	{
		return "ID: "+this.getId()+" , latitud: "+this.getLatitud()+", longitud: "+this.getLongitud();
	}
}