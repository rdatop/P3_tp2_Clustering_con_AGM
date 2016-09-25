package modelo;

public class Coordenada {

	private Double _latitud;
	private Double _longitud;
	
	public Coordenada(Double lat,Double longit)
	{
		set_latitud(lat);
		set_longitud(longit);
	}

	public Double get_latitud() {
		return _latitud;
	}

	public void set_latitud(Double _latitud) {
		this._latitud = _latitud;
	}

	public Double get_longitud() {
		return _longitud;
	}

	public void set_longitud(Double _longitud) {
		this._longitud = _longitud;
	}
}