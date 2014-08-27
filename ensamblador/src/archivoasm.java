import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;



public class archivoasm  extends Texto{
	
	JFileChooser file=new JFileChooser();
	ArrayList<String> lista = new ArrayList<String>();
	ArrayList<String> listataboop = new ArrayList<String>();
	
	
	public archivoasm() {	
		super();
		// TODO Auto-generated constructor stub
	}

	
	public void abrirtaboop() {
		try{
			listataboop.clear();
  			File f = new File("C:/Users/Hernandez/Desktop/taboop.txt");
  			int a=0;
  			String linea, cadena="";
  			Scanner s = new Scanner(f);
  			while(s.hasNext()){
  				linea = s.nextLine();
  				a=linea.length();
  				listataboop.add(linea);
  			}
  			s.close();		
  		}catch(FileNotFoundException fnfe){
  			fnfe.printStackTrace();	
  		}
		
		}

	
	public String abrirArchivo() {
		lista.clear();
		  String aux=""  ;
				  String texto = "";
		  try
		  {
		   /**llamamos el metodo que permite cargar la ventana*/
		file.showOpenDialog(null);
		   /**abrimos el archivo seleccionado*/
		   File abre=file.getSelectedFile();
		   /**recorremos el archivo, lo leemos para plasmarlo
		   *en el area de texto*/
		   if(abre!=null)
		   {    
			   int a=0;
		      FileReader archivos=new FileReader(abre);
		      BufferedReader lee=new BufferedReader(archivos);
		      while((aux=lee.readLine())!=null)
		      {
		    	  if(aux.indexOf("END")!=-1)
		    	  {
		    		   a=1;
		    		   lista.add("END");
		    	  }
		    	  if(a==0)
		            lista.add(aux);
		      }
		         lee.close();
		    }   
		   }
		   catch(IOException ex)
		   {
		     JOptionPane.showMessageDialog(null,ex+"" +
		           "\nNo se ha encontrado el archivo",
		                 "ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
		    }
		  return texto;//El texto se almacena en el JTextArea
		}

	public void guardarArchivo(int filas,JTable table) {
		 try
		 {
		  JFileChooser file=new JFileChooser();
		  file.showSaveDialog(null);
		  File guarda =file.getSelectedFile();
		  if(guarda !=null)
		  {
		   /*guardamos el archivo y le damos el formato directamente*/
		    FileWriter  save=new FileWriter(guarda+".asm");
		    guardarcambios( table, filas);
		    String cadena=guardarcambios(table, filas);
			save.write(cadena);
		    save.close();
		    JOptionPane.showMessageDialog(null,
		         "El archivo se a guardado Exitosamente",
		             "Información",JOptionPane.INFORMATION_MESSAGE);
		    }
		 }
		  catch(IOException ex)
		  {
		   JOptionPane.showMessageDialog(null,
		        "Su archivo no se ha guardado",
		           "Advertencia",JOptionPane.WARNING_MESSAGE);
		  }
		 }
	public void guardar(int filas,JTable table) {
		 try
		 {
		  File guarda =file.getSelectedFile();
		  if(guarda !=null)
		  {
		   /*guardamos el archivo y le damos el formato directamente*/
		    FileWriter  save=new FileWriter(guarda);
		    guardarcambios( table, filas);
		    String cadena=guardarcambios(table, filas);
			save.write(cadena);
		    save.close();
		    JOptionPane.showMessageDialog(null,
		         "El archivo se a guardado Exitosamente",
		             "Información",JOptionPane.INFORMATION_MESSAGE);
		    }
		 }
		  catch(IOException ex)
		  {
		   JOptionPane.showMessageDialog(null,
		        "Su archivo no se ha guardado",
		           "Advertencia",JOptionPane.WARNING_MESSAGE);
		  }
		 }
	public int comparar(String cadena)
	{
		String algo;
		abrirtaboop();
		int band=1;
		for(int i=0;listataboop.size()>i;i++)
		{
			algo=listataboop.get(i);
			algo=algo.substring(0, algo.indexOf('|'));
			 if(algo.equals(cadena)==true||cadena.equals("DCB")==true||cadena.equals("DCB.B")||cadena.equals("DCB.W")||cadena.equals("DCB.L")||cadena.equals("FCC")||cadena.equals("DC")||cadena.equals("DC.B")||cadena.equals("DC.W")||cadena.equals("DC.L")||cadena.equals("ORG")||cadena.equals("EQU"))
			 {
				 band=0;
				 break;
			 }
		}
		return band;
	}
		}





