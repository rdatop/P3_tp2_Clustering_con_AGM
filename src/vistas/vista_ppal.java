package vistas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.border.LineBorder;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import modelo.DAOVertices;
import modelo.Vertice;

public class vista_ppal {

	private JFrame frame;
	private JMapViewer _mapa;
	private JLabel lblCantClusters;
	private JTextField txtCantClusters;
	private JButton btnIniciarDivision;
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
		////////////////////colocar marcador ungs
		MapMarker ungs=new MapMarkerDot(-34.521, -58.7008);
		ungs.getStyle().setBackColor(Color.DARK_GRAY);
		_mapa.addMapMarker(ungs);
		////////////////////
		//panel que contiene las acciones contenido en mapa
		panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(128, 128, 128)));
		panel.setBounds(0, 651, 1362, 35);
		
		//contencion de elementos
		frame.setContentPane(_mapa);
		_mapa.add(panel);
		
        final String[] options = { "(Seleccione La Instancia a Cargar)", "instancia1", "instancia2", "instancia3", "instancia4", "instancia5" };
        final JComboBox<String> cmbxInstancias = new JComboBox<String>(options);
		panel.add(cmbxInstancias);
		
		//indicacion de Clusters
		lblCantClusters = new JLabel("Cantidad de Clusters");
		panel.add(lblCantClusters);
		
		//campo a llenar con la cant de clusters
		txtCantClusters = new JTextField();
		txtCantClusters.setColumns(2);
		seteaCantClusters(0);
		panel.add(txtCantClusters);
		
		//boton para iniciar el clustering
		btnIniciarDivision = new JButton("Iniciar Division");
		panel.add(btnIniciarDivision);
		btnIniciarDivision.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				int cantClusters=Integer.parseInt(txtCantClusters.getText());
				boolean erroresValidacion=false;
				String	mensajeErroresValidacion="";
				
				if(cmbxInstancias.getSelectedIndex()==0){//no se eligió una instancia valida
					erroresValidacion=true;
					mensajeErroresValidacion+="-Por favor seleccione una instancia\n";
				}
				if(cantClusters < 1){
					erroresValidacion=true;
					mensajeErroresValidacion+="-La cantidad de clusters debe de ser igual o mayor a 1";
					seteaCantClusters(0);//reseteo el valor del campo de cantidad de clusters
				}
				
				if(erroresValidacion){//si hubo errores en la validación
					JOptionPane.showMessageDialog(null,mensajeErroresValidacion);
				}else{//si no hubo errores
					JOptionPane.showMessageDialog(null,"Ahi va la division!");
					String instanciaSelecionada=options[cmbxInstancias.getSelectedIndex()];
					try{
						muestraNuevoMapa(instanciaSelecionada);
					}catch(IOException exception){
						System.out.println("Error al cargar la instancia");
					}
				}
			}
		});	
	}

	/*-- Métodos auxiliares --*/
	private void muestraNuevoMapa(String instancia) throws IOException {
		DAOVertices dao=new DAOVertices("src/modelo/"+instancia+".json");
		Vertice primerVertice=dao.obtenerVertices().get(0);
		
		_mapa.setDisplayPositionByLatLon(primerVertice.getLatitud(),primerVertice.getLongitud(),12);
		ArrayList<Coordinate> coordenadas = llenaListaCoordenadas(dao.obtenerVertices());
		
		_mapa.removeAllMapPolygons();//borra todas las aristas
		_mapa.removeAllMapMarkers();//borra todos los marcadores
		
		/*-- Armado del/los polígono/s --*/
		MapPolygon polygon = new MapPolygonImpl(coordenadas);
		_mapa.addMapPolygon(polygon);
				
		// Y un marcador en cada vértice del polígono!
		for(Coordinate c: coordenadas)
			_mapa.addMapMarker(new MapMarkerDot(c));
	}

	/*-- Genera una lista de coordendas --*/
	private ArrayList<Coordinate> llenaListaCoordenadas(ArrayList<Vertice> listaVertices) {
		ArrayList<Coordinate> coordenadas = new ArrayList<Coordinate>();
		for(Vertice vertice:listaVertices){
			coordenadas.add(new Coordinate(vertice.getLatitud(),vertice.getLongitud()));
		}
		return coordenadas;
	}
	
	private void seteaCantClusters(int cant){
		txtCantClusters.setText("0");
	}	
}
