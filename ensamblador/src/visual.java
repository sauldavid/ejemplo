import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.TextArea;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JProgressBar;

import java.awt.Scrollbar;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;

import javax.swing.UIManager;


public class visual{
	 Texto clasetexto =new Texto();
	JTextArea textArea = new JTextArea();
	int filas;
	
	archivoasm clasearchivo=new archivoasm();
	private JFrame frame;
    private JPanel panel;
	private ImageIcon icono2= new ImageIcon ("nuevo.png");
	private ImageIcon icono= new ImageIcon ("guardar.jpg");
	private ImageIcon icono3= new ImageIcon ("abrir.png");
	private ImageIcon icono4= new ImageIcon ("guardarcomo.jpg");
 JTable table;
 
	String cadena="";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					visual window = new visual();
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
	public visual() {
		
		initialize();
	}
	private JScrollPane scrollPane_1;
	private JButton button_2;
	private JButton button;
	private JButton button_1;
	private JButton btnEnsamblar;
	private JButton btnNuevo;
	private JPanel panel_1;
	private JScrollPane scrollPane_2;
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 515, 510);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		button= new JButton("abrir",icono3);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				cadena=clasearchivo.abrirArchivo();
				clasetexto.errores="";
				 clasetexto.listacomentario.clear();
				clasetexto.listaetiqueta.clear();
				clasetexto.listainstruccion.clear();
				clasetexto.listaoperando.clear();
				clasetexto.contadorlocalidades.clear();
				 for(int i=0;i<clasearchivo.lista.size();i++){
				 clasetexto.tokenizar(clasearchivo.lista.get(i),  table,i, textArea);
				 filas=i;
				 }
				
				 
				//a.separardatos(cadena, table);
			     //texto.setText(""+abrirArchivo());
			}
		});
		button.setBounds(36, 20, 30, 30);
		frame.getContentPane().add(button);
		
		button_1 = new JButton("guardarcomo",icono4);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clasearchivo.guardarArchivo(filas, table);
				
			}
		});
		button_1.setBounds(76, 20, 30, 30);
		frame.getContentPane().add(button_1);
		
		button_2 = new JButton("guardar",icono );
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clasearchivo.guardar( filas, table);
			}
		});
		button_2.setBounds(161, 20, 30, 30);
		frame.getContentPane().add(button_2);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 50, 469, 244);
		frame.getContentPane().add(scrollPane_1);
		
		panel = new JPanel();
		scrollPane_1.setViewportView(panel);
		panel.setToolTipText("");
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"linea", "etiqueta", "instruccion", "operando", "New column"
			}
		));
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{table}));
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 441, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		btnNuevo = new JButton("nuevo",icono2);
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 DefaultTableModel modelo=(DefaultTableModel) table.getModel();
					            int filas=table.getRowCount();
				            for (int i = 0;filas>i; i++) {
					                modelo.removeRow(0);
				            }
				            textArea.setText(" ");
			}
		});
		btnNuevo.setBounds(116, 20, 30, 30);
		frame.getContentPane().add(btnNuevo);
		
		btnEnsamblar = new JButton("ensamblar", null);
		btnEnsamblar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				
				clasetexto.reconocer(clasetexto.listainstruccion,clasetexto.listaoperando);
				
				
				extvisual aa=new extvisual();
			    aa.es(clasetexto.errores,clasetexto.listaoperando,clasetexto.codigomaquina,clasetexto.listaetiqueta,clasetexto.listainstruccion,clasetexto.listacomentario,clasetexto.contadorlocalidades);
			    aa.setVisible(true);
			    clasetexto.errores="";
			    
				//ventanaensamblar a=new ventanaensamblar();
		     
				
				
			}
		});
		btnEnsamblar.setBounds(208, 20, 30, 30);
		frame.getContentPane().add(btnEnsamblar);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 311, 463, 150);
		frame.getContentPane().add(scrollPane_2);
		
		panel_1 = new JPanel();
		scrollPane_2.setViewportView(panel_1);
		panel_1.setLayout(null);
		textArea.setBounds(0, 0, 463, 150);
		panel_1.add(textArea);
		
		
		
	}
}
