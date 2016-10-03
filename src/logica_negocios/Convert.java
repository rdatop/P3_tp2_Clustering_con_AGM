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
	
	//covercion a Radianes
	public static Double aRadianes(Double punto){//degree
	   // Value degree * Pi/180
	   Double peso = punto * 3.1415926 / 180;
	   return peso;	
	}
	
	//main a efectos de verificar funcionamiento, luego borrar
	public static void main(String[]args){
		System.out.println(calcularDistanciaEntre(-34.52133782929332, -58.70068073272705, -34.520772089706036, -58.702311515808105));
		System.out.println(calcularDistanciaEntre(-34.520772089706036, -58.702311515808105, -34.52126711205503, -58.70325565338135));
		System.out.println(calcularDistanciaEntre(-34.52133782929332, -58.70068073272705, -34.52126711205503, -58.70325565338135));
	}
}
