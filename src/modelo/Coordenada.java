package modelo;

public class Coordenada {

	private int id_coordenada;
	private Double _latitud;
	private Double _longitud;
	
	public Coordenada(Double lat,Double longit)
	{
		setIdCoordenada(GeneradorID.devuelveIDMasUno());
		set_latitud(lat);
		set_longitud(longit);
	}
	
	public int getIdCoordenada() {
		return id_coordenada;
	}

	public void setIdCoordenada(int id_coordenada) {
		this.id_coordenada = id_coordenada;
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
	
	////////////Clase inner GeneradorID
	public static class GeneradorID{
		private static int id;
		
		public static int devuelveIDMasUno(){
			id++;
			int aux=id;
			return aux;
		}
	}
}