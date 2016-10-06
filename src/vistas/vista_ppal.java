package vistas;

import java.awt.Color;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.UIManager;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;


import logica_negocios.GrafoPesado;


import java.awt.Component;


import javax.swing.border.LineBorder;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.util.ArrayList;


import javax.swing.JComboBox;
import javax.swing.border.MatteBorder;



import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;


import javax.swing.AbstractAction;
import javax.swing.Action;

public class vista_ppal {

	private JFrame frame;
	private JMapViewer _mapa;
	private JLabel Cant_de_Clusterlabel;
	private JTextField Clusters_textField;
	private JButton Iniciar_button;
	private JPanel panel;
	private final Action action = new SwingAction();
	private GrafoPesado _grafo;
	

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
		
		//String[] arregloInstancias={"(Seleccione La Instancia a Cargar)", "instancia1", "instancia2", "instancia3", "instancia4", "instancia5"};
		// Options in the combobox
        String[] options = { "(Seleccione La Instancia a Cargar)", "instancia1", "instancia2", "instancia3", "instancia4", "instancia5" };
        JComboBox<String> comboBox = new JComboBox<String>(options);
		panel.add(comboBox);
		comboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Do something when you select a value
            	System.out.println("Index seleccionado: "+options[comboBox.getSelectedIndex()]); 
            }
        });
		
		//indicacion de Clusters
		Cant_de_Clusterlabel = new JLabel("Cantidad de Clusters");
		panel.add(Cant_de_Clusterlabel);
		
		//campo a llenar con la cant de clusters
		Clusters_textField = new JTextField();
		Clusters_textField.setColumns(2);
		panel.add(Clusters_textField);
		
		//boton para iniciar el clustering
		Iniciar_button = new JButton("Iniciar Division");
		Iniciar_button.setAction(action);
		panel.add(Iniciar_button);
		Iniciar_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int cluster=Integer.parseInt(Clusters_textField.getText());
				if(cluster>_grafo.getCantVertices()||cluster<1){
					
					//JOptionPane.showMessageDialog(null,"Coloque un Nro valido de Clusters");
				}else{
					////////preguntar a pablo
				}
			}
		});
		
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
	private class SwingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
