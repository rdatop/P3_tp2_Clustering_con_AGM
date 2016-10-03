package modelo;

public class Vertice {
	//variables de instancia
	private Double _latitud;
	private Double _longitud;
	
	// Constructor
	public Vertice(Double lat,Double longit){
		setLatitud(lat);
		setLongitud(longit);
	}

	public Double getLatitud() {
		return _latitud;
	}

	public void setLatitud(Double _latitud) {
		this._latitud = _latitud;
	}

	public Double getLongitud() {
		return _longitud;
	}

	public void setLongitud(Double _longitud) {
		this._longitud = _longitud;
	}
}