import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;


public class ventanaensamblar {

	ArrayList<String> listaoperando=new ArrayList<String>();
	ArrayList<String> listainstrucion = new ArrayList<String>();
	ArrayList<String> listaetiqueta=new ArrayList<String>();
	ArrayList<String> listacomentario=new ArrayList<String>();
	ArrayList<String> contadorlocalidades = new ArrayList<String>();
	 JFrame frame;
	 JTable table = new JTable();
	 private JScrollPane scrollPane;
	 private JPanel panel;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	 public void ensan(){
		 
	 }
	 
	public ventanaensamblar() {
		ventanaensambla();
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	//etiqueta,instruccion,operando,comentario
	public void ventanaensambla() {
		frame = new JFrame();
		frame.setBounds(100, 100, 491, 300);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 469, 244);
	frame.getContentPane().add(scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setToolTipText("");
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		
		
		table.setBounds(5, 16, 441, 222);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"pedro", null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		panel.add(table);
		ventanaensambla();
		for(int i=0;i<listaetiqueta.size();i++){
			System.out.println("no mmes");
		DefaultTableModel mitablemo = (DefaultTableModel) table.getModel();
		Object nuevaFila[]= {i,listaetiqueta.get(i),listainstrucion.get(i),listaoperando.get(i),listacomentario.get(i)};
	    mitablemo.addRow(nuevaFila);
		}
		frame.setVisible(true);
		
		
	     
	
	}
	public void llena(Texto clasetexto )
	{

	   
		this.listaoperando=clasetexto.listaoperando;
		this.listainstrucion =clasetexto.listainstruccion;
		this.listaetiqueta=clasetexto.listaetiqueta;
	 this.listacomentario=clasetexto.listacomentario;
  this.contadorlocalidades =clasetexto.contadorlocalidades;
  System.out.println(this.listaoperando);
  System.out.println(this.listainstrucion);
  System.out.println(this.listaetiqueta);
  System.out.println(this.listacomentario);
  System.out.println(this.contadorlocalidades);
	}

}
