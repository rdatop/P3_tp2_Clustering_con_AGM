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
		this.setId(id);
		this.setLatitud(latitud);
		this.setLongitud(longitud);
	}
	
	//escritura de la latitud
	public void setLatitud(Double latitud)
	{
		this.latitud = latitud;
	}
	//lectura protegida
	public Double getLatitud() 
	{
		return this.latitud;
	}
		
	//escritura de la longitud
	public void setLongitud(Double longitud)
	{
		this.longitud = longitud;
	}
	//lectura protegida
	public Double getLongitud()
	{
		return this.longitud;
	}
	
	//escrituta del id
	public void setId(int iD)
	{
		this.id=iD;
	}
	//lectura protegida
	public int getId()
	{
		return this.id;
	}
	
	//representacion de prueba
	@Override
	public String toString()
	{
		return "ID: "+this.getId()+" , latitud: "+this.getLatitud()+", longitud: "+this.getLongitud();
	}
}