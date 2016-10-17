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
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import logica_negocios.Algoritmos;
import logica_negocios.Algoritmos.Arista;
import logica_negocios.Clustering;
import logica_negocios.GrafoPesado;
import modelo.DAOVertices;
import modelo.Tupla_GrafoPesado_Aristas;
import modelo.Vertice;

public class Main 
{
	//variables de instancia
	private JFrame _frame;
	private JMapViewer _mapa;
	private JLabel _lblCantClusters;
	private JTextField _txtCantClusters;
	private JButton _btnIniciarDivision;
	private JPanel _panel;
	private ArrayList<Arista> _listaAristasAMG;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Main window = new Main();
					window._frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//constructor
	public Main() 
	{
		try//manejo de look and feel
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		//creacion del frame
		this._frame = new JFrame();
		this._frame.setBounds(-6,10, 1400, 725);
		this._frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this._frame.getContentPane().setLayout(null);
			
		//creacion, centrado y punto de referencia del mapa contenido en frame
		this._mapa=new JMapViewer();
		this._mapa.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		this._mapa.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		this._mapa.setAlignmentX(Component.RIGHT_ALIGNMENT);
		this._mapa.setTileGridVisible(false);
		this._mapa.setScrollWrapEnabled(true);
		this._mapa.setMapRectanglesVisible(true);
		this._mapa.setMapPolygonsVisible(true);
		this._mapa.setMapMarkerVisible(true);
		this._mapa.setZoomContolsVisible(true);//zoom incorporado
		this._mapa.setDisplayPositionByLatLon(-34.521, -58.7008,12);//punto predeterminado:UNGS
		
		////////////////////colocar marcador ungs
		MapMarker ungs=new MapMarkerDot(-34.521, -58.7008);
		ungs.getStyle().setBackColor(Color.DARK_GRAY);
		this._mapa.addMapMarker(ungs);
		////////////////////
		
		//panel que contiene las acciones contenido en mapa
		this._panel = new JPanel();
		this._panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(128, 128, 128)));
		this._panel.setBounds(0, 651, 1362, 35);
		
		//contencion de elementos
		this._frame.setContentPane(_mapa);
		this._mapa.add(_panel);
		
        final String[] options = { "(Seleccione La Instancia a Cargar)", "instancia1", "instancia2", "instancia3", "instancia4", "instancia5" };
        final JComboBox<String> cmbxInstancias = new JComboBox<String>(options);
        this._panel.add(cmbxInstancias);
		
		//indicacion de Clusters
        this._lblCantClusters = new JLabel("Cantidad de Clusters");
        this._panel.add(_lblCantClusters);
		
		//campo a llenar con la cant de clusters
        this._txtCantClusters = new JTextField();
        this._txtCantClusters.setColumns(2);
        this.seteaCantClusters(0);
        this._panel.add(_txtCantClusters);
		
		//boton para iniciar el clustering
        this._btnIniciarDivision = new JButton("Iniciar Division");
        this._panel.add(_btnIniciarDivision);
        this._btnIniciarDivision.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
				int cantClusters=Integer.parseInt(_txtCantClusters.getText());
				boolean erroresValidacion=false;
				String	mensajeErroresValidacion="";
				
				if(cmbxInstancias.getSelectedIndex()==0)//no se eligio una instancia valida
				{
					erroresValidacion=true;
					mensajeErroresValidacion+="-Por favor seleccione una instancia\n";
				}
				if(cantClusters < 1)
				{
					erroresValidacion=true;
					mensajeErroresValidacion+="-La cantidad de clusters debe de ser igual o mayor a 1";
					seteaCantClusters(0);//reseteo el valor del campo de cantidad de clusters
				}
				
				if(erroresValidacion)//si hubo errores en la validaciï¿½n
				{
					JOptionPane.showMessageDialog(null,mensajeErroresValidacion);
				}else//si no hubo errores
				{
					JOptionPane.showMessageDialog(null,"Iniciando division!");
					String instanciaSelecionada=options[cmbxInstancias.getSelectedIndex()];
					try{
						muestraMapa(instanciaSelecionada,cantClusters);
					}catch(IOException exception){
						System.out.println("Error al cargar la instancia");
					}
				}
			}
		});	
	}
	
	private void muestraMapa(String instancia,int cantClusters) throws IOException 
	{
		DAOVertices dao=new DAOVertices("src/modelo/"+instancia+".json");
		GrafoPesado grafoPesado=new GrafoPesado(dao.obtenerVertices());
		//agregando pesos a las aristas del grafo pesado
		this.llenaGrafoConAristas(grafoPesado);
		Tupla_GrafoPesado_Aristas tupla=Algoritmos.AGM(grafoPesado);
		grafoPesado=tupla.getGrafoPesado();
		
		
		//centra el mapa y hace zoom según instancia elegida
		centrarMapa(grafoPesado);	
		
		//borro todos los puntos y lineas del mapa
		this.reseteaMapa();		
		
		
		
		//lista de aristas del AGM
		Clustering cluster=new Clustering(tupla.getAristasAGM());
		this._listaAristasAMG=cluster.getPesosAristas();
		cluster.obviarAristasMayores(this._listaAristasAMG, cantClusters);
		System.out.println("Puto el que lee: "+this._listaAristasAMG);//////////////
		for (int i = 0; i < this._listaAristasAMG.size(); i++) 
		{
			//TODO
			//aca deberia hacer la arista (a, b)(b,a) entre vértices pero desde las aristas	
		}
		
		ArrayList<Coordinate> coordenadas = llenaListaCoordenadas(grafoPesado.obtenerVertices());
		// Y un marcador en cada vertice del poligono!
		for(Coordinate c: coordenadas)
			this._mapa.addMapMarker(new MapMarkerDot(c));
	}

	/*-- Genera una lista de coordendas --*/
	private ArrayList<Coordinate> llenaListaCoordenadas(ArrayList<Vertice> listaVertices) 
	{
		ArrayList<Coordinate> coordenadas = new ArrayList<Coordinate>();
		for(Vertice vertice:listaVertices){
			coordenadas.add(new Coordinate(vertice.getLatitud(),vertice.getLongitud()));
		}
		return coordenadas;
	}
	
	/*-- Llena un grafoPesado de aristas(Ej:(1,2),(2,3),etc) --*/
	private void llenaGrafoConAristas(GrafoPesado grafo)
	{
		for (int i = 1; i <= grafo.cantVertices(); i++)
		{
			grafo.agregarArista(i,i+1);
		}
	}
	
	/*-- Elige el vértice del medio de la instancia y centra el zoom en él --*/
	public void centrarMapa(GrafoPesado grafo)
	{
		Vertice primerVertice=grafo.obtenerVertice((grafo.getCantVertices())/2);
		this._mapa.setDisplayPositionByLatLon(primerVertice.getLatitud(),primerVertice.getLongitud(),12);
	}
	
	/*-- Borra toso los puntos y líneas del mapa --*/
	private void reseteaMapa()
	{
		this._mapa.removeAllMapPolygons();//borra todas las aristas
		this._mapa.removeAllMapMarkers();//borra todos los marcadores
	}
	
	/*-- Setea en 0 el campo "txtCantClusters" para poder validarlo --*/
	private void seteaCantClusters(int cant)
	{
		this._txtCantClusters.setText("0");
	}	
}