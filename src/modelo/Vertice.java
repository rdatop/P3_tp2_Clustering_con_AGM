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

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double _latitud) {
		this.latitud = _latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double _longitud) {
		this.longitud = _longitud;
	}
}