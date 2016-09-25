package modelo;

public class Puntaje {

	private String nombreUsuario;
	private int puntaje;
	
	public Puntaje(String nombreUsuario,int puntaje)
	{
		this.nombreUsuario=nombreUsuario;
		this.puntaje=puntaje;
	}
	public String getNombreUsuario()
	{
		return this.nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario)
	{
		this.nombreUsuario = nombreUsuario;
	}
	public int getPuntaje() 
	{
		return this.puntaje;
	}
	public void setPuntaje(int puntaje)
	{
		this.puntaje = puntaje;
	}
}