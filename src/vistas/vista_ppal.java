package vistas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.RootPaneContainer;

import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JComboBox;
import javax.swing.border.MatteBorder;
import java.awt.ComponentOrientation;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class vista_ppal {

	private JFrame frame;
	private JMapViewer _mapa;
	private JLabel Cant_de_Clusterlabel;
	private JTextField Clusters_textField;
	private JButton Iniciar_button;
	private JPanel panel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vista_ppal window = new vista_ppal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public vista_ppal() {
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch(Exception e){
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//creacion del frame
		frame = new JFrame();
		frame.setBounds(-6,10, 1400, 725);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
			
		//creacion, centrado y punto de referencia del mapa contenido en frame
		_mapa=new JMapViewer();
		_mapa.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		_mapa.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		_mapa.setAlignmentX(Component.RIGHT_ALIGNMENT);
		_mapa.setTileGridVisible(false);
		_mapa.setScrollWrapEnabled(true);
		_mapa.setMapRectanglesVisible(true);
		_mapa.setMapPolygonsVisible(true);
		_mapa.setMapMarkerVisible(true);
		_mapa.setZoomContolsVisible(true);//zoom incorporado
		_mapa.setDisplayPositionByLatLon(-34.521, -58.7008, 11);//lat log zoom
		
		//colocar marcador ungs
		MapMarker ungs=new MapMarkerDot(-34.521, -58.7008);
		ungs.getStyle().setBackColor(Color.DARK_GRAY);
		_mapa.addMapMarker(ungs);
		
		//panel que contiene las acciones contenido en mapa
		panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(128, 128, 128)));
		panel.setBounds(0, 651, 1362, 35);
		
		//contencion de elementos
		frame.setContentPane(_mapa);
		_mapa.add(panel);
		
		//combobox		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		String[] arregloInstancias={"(Seleccione La Instancia a Cargar)", "instancia1", "instancia2", "instancia3", "instancia4", "instancia5"};
		comboBox_1.setModel(new DefaultComboBoxModel<String>(arregloInstancias));
		panel.add(comboBox_1);
		
		//indicacion de Clusters
		Cant_de_Clusterlabel = new JLabel("Cantidad de Clusters");
		panel.add(Cant_de_Clusterlabel);
		
		//campo a llenar con la cant de clusters
		Clusters_textField = new JTextField();
		Clusters_textField.setColumns(2);
		panel.add(Clusters_textField);
		
		//boton para iniciar el clustering
		Iniciar_button = new JButton("Iniciar Division");
		panel.add(Iniciar_button);
				
		
		
		// Ahora un polígono!
		ArrayList<Coordinate> coordenadas = new ArrayList<Coordinate>();
		coordenadas.add(new Coordinate(-34.532, -58.7128));
		coordenadas.add(new Coordinate(-34.546, -58.719));
		coordenadas.add(new Coordinate(-34.559, -58.721));
		coordenadas.add(new Coordinate(-34.569, -58.725));
		coordenadas.add(new Coordinate(-34.532, -58.730));
		
		MapPolygon polygon = new MapPolygonImpl(coordenadas);
		_mapa.addMapPolygon(polygon);
				
		// Y un marcador en cada vértice del polígono!
		for(Coordinate c: coordenadas)
			_mapa.addMapMarker(new MapMarkerDot(c));
	}
}
