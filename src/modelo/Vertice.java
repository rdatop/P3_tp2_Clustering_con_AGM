package modelo;

public class Vertice {
	//variables de instancia
	private Double latitud;
	private Double longitud;
	
	// Constructor
	public Vertice(Double lat,Double longit){
		setLatitud(lat);
		setLongitud(longit);
	}

	// Ingreso de latitud
	public void setLatitud(Double _latitud) {
		this.latitud = _latitud;
	}
	
	// Lectura protegida de latitud
	public Double getLatitud() {
		return latitud;
	}

	// Ingreso de longitud
	public void setLongitud(Double _longitud) {
		this.longitud = _longitud;
	}

	// Lectura protegida longitud
	public Double getLongitud() {
		return longitud;
	}
}