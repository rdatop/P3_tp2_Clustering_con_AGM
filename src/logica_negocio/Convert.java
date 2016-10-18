package logica_negocio;

public class Convert
{
	//variable de instancia sin constructor
	private static Double _radioTerrestre= 6371.00; //radio medio del planeta en Kilometros

	// Calcula la distancia entre dos vertices
	public static Double calcularDistanciaEntre(Double lat1, Double lon1, Double lat2, Double lon2)
	{
	  Double Radio = Convert._radioTerrestre; //6371.00;
	  Double dLat = Convert.aRadianes(lat2-lat1);
	  Double dLon = Convert.aRadianes(lon2-lon1);            
	  Double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	  Math.cos(Convert.aRadianes(lat1)) * Math.cos(Convert.aRadianes(lat2)) 
	  * Math.sin(dLon/2) * Math.sin(dLon/2);
	  Double c = 2 * Math.asin(Math.sqrt(a));
	  return Radio * c;		
	}
	
	// Covercion a Radianes
	public static Double aRadianes(Double punto)//valor de punto * Pi/180
	{
	   Double peso = punto * 3.1415926 / 180;
	   return peso;	
	}
}
