package modelo;

public class Vertice {

	private int id_coordenada;
	private Double _latitud;
	private Double _longitud;
	
	public Vertice(Double lat,Double longit)
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

	public Double getLatitud() {
		return _latitud;
	}

	public void set_latitud(Double _latitud) {
		this._latitud = _latitud;
	}

	public Double getLongitud() {
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