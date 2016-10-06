package vistas;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import com.google.gson.JsonNull;

public class vista_mapa
{
	private JFrame frame;
	private JMapViewer miMapa;
	

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					vista_mapa window = new vista_mapa();
					window.frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public vista_mapa()
	{
		initialize();
	}

	private void initialize()
	{
		
		
		frame = new JFrame();
		frame.setBounds(-5,33, 1400, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		miMapa = new JMapViewer();
		miMapa.setZoomContolsVisible(true);//zoom incorporado
		miMapa.setDisplayPositionByLatLon(-34.521, -58.7008, 11);//lat log zoom

		// Ponemos un marcador!
		MapMarker marker = new MapMarkerDot(-34.521, -58.7008);//marcador la facu
		marker.getStyle().setBackColor(Color.RED);
		miMapa.addMapMarker(marker);
		
		frame.setContentPane(miMapa);
		miMapa.setLayout(null);
		
		/*boton de seleccio en este caso equipos*/
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(51, 630, 220, 20);
		
		//comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"(Seleccione La Instancia a Cargar)", "instancia1", "instancia2", "instancia3", "instancia4", "instancia5"}));
		String[] arregloInstancias={"(Seleccione La Instancia a Cargar)", "instancia1", "instancia2", "instancia3", "instancia4", "instancia5"};
		comboBox_1.setModel(new DefaultComboBoxModel<String>(arregloInstancias));
		
		miMapa.add(comboBox_1);
		
		
		// Ahora un polígono!
		ArrayList<Coordinate> coordenadas = new ArrayList<Coordinate>();
		coordenadas.add(new Coordinate(-34.532, -58.7128));
		coordenadas.add(new Coordinate(-34.546, -58.719));
		coordenadas.add(new Coordinate(-34.559, -58.721));
		coordenadas.add(new Coordinate(-34.569, -58.725));
		coordenadas.add(new Coordinate(-34.532, -58.730));
		
		MapPolygon polygon = new MapPolygonImpl(coordenadas);
		miMapa.addMapPolygon(polygon);
		
		// Y un marcador en cada vértice del polígono!
		for(Coordinate c: coordenadas)
			miMapa.addMapMarker(new MapMarkerDot(c));
		
		
	}
}
