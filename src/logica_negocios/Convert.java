package logica_negocios;



public class Convert {
	
	private static Double EARTH_RADIUS = 6371.00; // Radius in Kilometers default

	//calcula la distancia entre dos puntos
	public static Double calcularDistanciaEntre(Double lat1, Double lon1, Double lat2,   Double lon2){
	  Double Radio = Convert.EARTH_RADIUS; //6371.00;
	  Double dLat = Convert.aRadianes(lat2-lat1);
	  Double dLon = Convert.aRadianes(lon2-lon1);            
	  Double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	  Math.cos(Convert.aRadianes(lat1)) *   Math.cos(Convert.aRadianes(lat2)) *
	  Math.sin(dLon/2) * Math.sin(dLon/2);
	  Double c = 2 * Math.asin(Math.sqrt(a));
	  return Radio * c;		
	}
	
	//calcular distancia entre dos puntos
	public static Double aRadianes(Double punto){//degree
	   // Value degree * Pi/180
	   Double peso = punto * 3.1415926 / 180;
	   return peso;	
	}
	
	
}
