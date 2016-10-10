package modelo;

public class Vertice {
	
	private Double latitud;
	private Double longitud;
	public Vertice(Double latitud, Double longitud)
	{
		
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
	
}