package vistas;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

public class vista_mapa{
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		miMapa = new JMapViewer();
		miMapa.setZoomContolsVisible(false);
		//miMapa.setDisplayPositionByLatLon(-34.521, -58.7008, 11);//lat log zoom

		// Ponemos un marcador!
		MapMarker marker = new MapMarkerDot(-34.521, -58.7008);//marcador la facu
		marker.getStyle().setBackColor(Color.RED);
		miMapa.addMapMarker(marker);
		
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
		
		frame.setContentPane(miMapa);
	}
}
