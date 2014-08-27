import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;


public class Texto  
{ 
	int bandera=0;
	String opera="0000";
	ArrayList<String> listaoperando=new ArrayList<String>();
	ArrayList<String> listainstruccion = new ArrayList<String>();
	String aux = "",etiqueta ="",instruccion = "",operando = "",comentario="",n="",errores="",maquina="";
	ArrayList<String> listaetiqueta=new ArrayList<String>();
	ArrayList<String> codigomaquina=new ArrayList<String>();
	ArrayList<String> listacomentario=new ArrayList<String>();
	ArrayList<String> contadorlocalidades = new ArrayList<String>();
	
	
	public void tokenizar(String lista,JTable table,int numlinea,JTextArea textArea)
	{
	
		archivoasm a =new archivoasm();
		for(int i=0;i<lista.length();i++)
		{
			
			if(lista.charAt(i)==';')
			{
				    
				    comentario=lista.substring(i,lista.length());
				    lista=lista.substring(0,i);
			   
				    
			}
		}
		StringTokenizer token;
		token = new StringTokenizer(lista);
		if(token.countTokens()==1)
		{
			if(lista.startsWith(" ")||lista.startsWith("\t"))
			{
				instruccion=token.nextToken();
				
				if(a.comparar(instruccion)==1)
				{
					errores+="linea"+numlinea+"esta instrucion no esta en el taboop \n";
				}
			}
			else if(lista.lastIndexOf("END")!=-1)
			{
				instruccion=token.nextToken();
				
			}
			else {
				etiqueta=token.nextToken();
				errores+="linea"+numlinea+"esta linea solo tiene etiqueta"+"\n";
				
			}
		}
		else if(token.countTokens()==2)
		{
			if(lista.startsWith(" ")||lista.startsWith("\t"))
			{
				instruccion=token.nextToken();
				
	
				if(a.comparar(instruccion)==1)
				{
					errores+="linea"+numlinea+"esta instrucion no esta en el taboop \n";
				}
				operando=token.nextToken();
				reconocer2(operando,numlinea,1);
				
	
			}
			else
			{
				etiqueta=token.nextToken();
				instruccion=token.nextToken();
		
				
				if(a.comparar(instruccion)==1)
				{
					errores+="linea"+numlinea+"esta instrucion no esta en el taboop \n";
				}
			}
			
		}
		else if(token.countTokens()==1)
		{
			etiqueta=" ";
			instruccion=" ";
			operando=" ";
			comentario=" ";	
		}
		else if(token.countTokens()==3)
		{
			if(lista.startsWith(" "))
			{
				instruccion=token.nextToken();
			
				
				if(a.comparar(instruccion)==1)
				{
					errores+="linea"+numlinea+"esta instrucion no esta en el taboop \n";
				}
				operando=token.nextToken();
				reconocer2(operando,numlinea,1);
				comentario=token.nextToken();
				errores+="linea"+numlinea+"contiene mas elementos de los posibles "+"\n";
			}
			else{
				etiqueta=token.nextToken();
				instruccion=token.nextToken();
				
				if(a.comparar(instruccion)==1)
				{
					errores+="linea"+numlinea+"esta instrucion no esta en el taboop \n";
				}
				operando=token.nextToken();
				reconocer2(operando,numlinea,1);
				
			}
		}
		else if(token.countTokens()==4)
		{
			etiqueta=token.nextToken();
			instruccion=token.nextToken();
			

			if(a.comparar(instruccion)==1)
			{
				errores+="linea"+numlinea+"esta instrucion no esta en el taboop \n";
			}
			operando=token.nextToken();
			reconocer2(operando,numlinea,1);
			comentario=token.nextToken();
			errores+="linea"+numlinea+"contiene mas elementos de los posibles "+"\n";
		}
		DefaultTableModel miTableModel = (DefaultTableModel) table.getModel();
		Object nuevaFila[]= {numlinea,etiqueta,instruccion,operando,comentario,n};
	     miTableModel.addRow(nuevaFila);
	     listaetiqueta.add(etiqueta);
	     etiqueta=" ";
	     listainstruccion.add(instruccion);
	     instruccion=" ";
	     listaoperando.add(operando);
	     operando=" ";
	     listacomentario.add(comentario);
	     comentario=" ";
	     textArea.setText(errores);
	    
	     
	}
	
	
	public String guardarcambios(JTable table,int filas)
	{
		String cadena = "";
		for(int i=0;i<=filas;i++)
			for(int a=1;a<5;a++)
			{
						cadena+=table.getValueAt(i, a).toString();
						cadena+="\t";
						if(a==4)
							cadena+="\n";

		
		} 
		return cadena;
		
	}
	public int reconocer2(String cadena, int i,int b){
		
		Pattern hexa = Pattern.compile("[$][A-F|0-9]+");
		 Pattern dec = Pattern.compile("[0-9]+");
		 Pattern  oct= Pattern.compile("[@][0-9]+");
		 Pattern bin = Pattern.compile("[%][0-1]+");
		 Pattern lo = Pattern.compile("[0-9]+[,](([0-9]+)|([$][A-F|0-9]+)|([%][0-1]+))");
		 Pattern la = Pattern.compile("[(([0-9]+)|([$][A-F|0-9]+)|([%][0-1]+))[,](([0-9]+)|([$][A-F|0-9]+)|([%][0-1]+))]+");
		 Matcher hex=hexa.matcher(cadena);
		 Matcher de=dec.matcher(cadena);
		 Matcher oc=oct.matcher(cadena);
		 Matcher bi=bin.matcher(cadena);
		 Matcher loc=lo.matcher(cadena);
		 Matcher lac=la.matcher(cadena);
		 if(hex.matches())
		 {
			 return 16;
		 }
		 else if(de.matches())
			 return 10;
		 else if(oc.matches())
			 return 8;
		 else if(bi.matches())
			 return 2;
		 else if(loc.matches())
			 return 3;
		 else if(lac.matches())
			 return 3;
		 else if(b==1)
			 return -1;//errores+=i+"operando invalido"+cadena+"\n";
		 return 0;
	}
	
	public void reconocer(ArrayList<String> lista,ArrayList<String> listao){
		int i=0;
		String aux="0000";
		contadorlocalidades.clear();
		while(i<lista.size())
         {  
		     switch(lista.get(i))
		     {
		     case "EQU":
		     {
		    	 if(reconocer2(listao.get(i),i,1)!=0)
		    	 {
		    	 maquina="";
		    	   aux=("$"+convertirhexadecimal(listao.get(i),0,reconocer2(listao.get(i),i,1))); 
		    	 break;
		    	 }else 
		    		 break;
		    	 
		     }
		     case "DCB":
		     {
		    	 if(reconocer2(listao.get(i),i,1)!=-1)
		    	 {
                 String aux2="";
		    	 int bits=0;
		    	 StringTokenizer tokens=new StringTokenizer(listao.get(i),",");
		    	 bits=(Integer.parseInt(tokens.nextToken(),10));
		    	 aux2=tokens.nextToken();
		    	     for(int a=0;a<bits;a++)
		    		 maquina+=(completarbits(convertirhexadecimal(aux2,0,reconocer2(aux2,i,1)),2));
		    	 opera=("$"+completarbits(convertirhexadecimal(aux,bits,reconocer2(aux,i,1)),4));
		    	 break;
		    	 }else
		    		 errores+=i+"operando invalido"+listao.get(i)+"\n";
		    		 break;
		     }
		     case "DCB.W":
		     {
		    	 if(reconocer2(listao.get(i),i,1)!=-1)
		    	 {
                 String aux2="";
		    	 int bits=0;
		    	 StringTokenizer tokens=new StringTokenizer(listao.get(i),",");
		    	 bits=(Integer.parseInt(tokens.nextToken(),10));
		    	 aux2=tokens.nextToken();
		    	     for(int a=0;a<bits;a++)
		    		 maquina+=(completarbits(convertirhexadecimal(aux2,0,reconocer2(aux2,i,1)),4));
		    	     bits=bits*2;
		    	 opera=("$"+completarbits(convertirhexadecimal(aux,bits,reconocer2(aux,i,1)),4));
		    	 break;
		    	 }
		    	 else
		    		 errores+=i+"operando invalido"+listao.get(i)+"\n";
		    		 break;
		     }
		     case "DCB.L":
		     {
		    	 if(reconocer2(listao.get(i),i,1)!=-1)
		    	 {
                 String aux2="";
		    	 int bits=0;
		    	 StringTokenizer tokens=new StringTokenizer(listao.get(i),",");
		    	 bits=(Integer.parseInt(tokens.nextToken(),10));
		    	 aux2=tokens.nextToken();
		    	     for(int a=0;a<bits;a++)
		    		 maquina+=(completarbits(convertirhexadecimal(aux2,0,reconocer2(aux2,i,1)),8));
		    	     bits=bits*4;
		    	 opera=("$"+completarbits(convertirhexadecimal(aux,bits,reconocer2(aux,i,1)),4));
		    	 break;
		     }else
		    	 errores+=i+"operando invalido"+listao.get(i)+"\n";
		    	 break;
		     }
		     case"DCB.B":
		     {
		    	 if(reconocer2(listao.get(i),i,1)!=-1)
		    	 {
                 String aux2="";
		    	 int bits=0;
		    	 StringTokenizer tokens=new StringTokenizer(listao.get(i),",");
		    	 bits=(Integer.parseInt(tokens.nextToken(),10));
		    	 aux2=tokens.nextToken();
		    	     for(int a=0;a<bits;a++)
		    		 maquina+=(completarbits(convertirhexadecimal(aux2,0,reconocer2(aux2,i,1)),2));
		    	 opera=("$"+completarbits(convertirhexadecimal(aux,bits,reconocer2(aux,i,1)),4));
		    	 break;
		    	 }else
		    		 errores+=i+"operando invalido"+listao.get(i)+"\n";
		    		 break;
		     }
		     
		     case "DC.B":
		     {  
		    	 if(reconocer2(listao.get(i),i,1)!=-1)
		    	 {
		    	 aux=opera;
		     int h=0;
		     String aux2="";
	    	 StringTokenizer st = new StringTokenizer(listao.get(i),",");
	    	 while(st.hasMoreTokens())
	    	 {
	    		 
	    		 aux2=st.nextToken();
	    		 
	    		 maquina+=(completarbits(convertirhexadecimal(aux2,0,reconocer2(aux2,i,1)),2));
	    		 h=h+1;
	    	 }
		    		 opera=("$"+completarbits(convertirhexadecimal(aux,h,reconocer2(aux,i,1)),4));
		    	 break;
		    	 }else
		    		 errores+=i+"operando invalido"+listao.get(i)+"\n";
		    		 break;
		     }
		     case "DC.W":
		     {
		    	 
		       	 String aux2="";
		    	 int h=0;
		    	 StringTokenizer st = new StringTokenizer(listao.get(i),",");
		    	 while(st.hasMoreTokens())
		    	 {
		    		 
		    		 aux2=st.nextToken();
		    		 
		    		 maquina+=(completarbits(convertirhexadecimal(aux2,0,reconocer2(aux2,i,1)),4));
		    		 h=h+2;
		    	 }
		    	 opera=("$"+completarbits(convertirhexadecimal(aux,h,reconocer2(aux,i,1)),4));
		    	 //contadorlocalidades.add(completarbits(convertirhexadecimal(listao.get(i)),4)); 
		    	 break;
		    	 
		    		 
		     }
		    	 
		     case "DC.L":{
		    	 if(reconocer2(listao.get(i),i,1)!=-1)
		    	 {
		    	 String aux2="";
		    	
		    	 int h=0;
		    	 StringTokenizer st = new StringTokenizer(listao.get(i),",");
		    	 while(st.hasMoreTokens())
		    	 {
		    		
		    		 aux2=st.nextToken();
		    		 
		    		 maquina+=(completarbits(convertirhexadecimal(aux2,0,reconocer2(aux2,i,1)),8));
		    		 h=h+4;
		    	 }
		    	 
		    	 opera=("$"+completarbits(convertirhexadecimal(aux,h,reconocer2(aux,i,1)),4));
		    	 //contadorlocalidades.add(completarbits(convertirhexadecimal(listao.get(i)),4)); 
		    	 break;
		    	 }else
		    		 errores+=i+"operando invalido"+listao.get(i)+"\n";
		    		 break;
		    	 }
		     case "ORG":
		     {
		    	 
		    	 if(reconocer2(listao.get(i),i,1)!=-1)
		    	 {

		    		 if(rangohexa(listao.get(i),i)==0)
		    		 {
		    			 errores+=i+"operador fuera de rango\n";
		    		 }
		    		 else{
		    	 aux=opera;
		    	 maquina="";
		    	 opera="";
		    	 opera=("$"+completarbits(convertirhexadecimal(listao.get(i),0,reconocer2(listao.get(i),i,1)),4));
		    	 break;
		    		 }
		    	 }else
		    		 errores+=i+"operando invalido"+listao.get(i)+"\n";
		    		 break;
		     }
		     case"FCC":
		     { 
	    	 
		    	
		    	 maquina=convertirascii(listao.get(i),i);
		    	 opera=("$"+completarbits(convertirhexadecimal(aux,listao.get(i).length(),reconocer2(aux,i,0)),4));
		    	 break;
	    	 
	    		 
		     }
		     default :
		     {
		    	 maquina="";
		    	 break; 
		     }
		     } 
		     contadorlocalidades.add(aux);
		      aux=opera;
		      codigomaquina.add(maquina);
		      maquina="";
		     i++;
         }
	}
	
	
	
	public int rangohexa(String cadena,int i)
	{
		int b;
		b=10;
		while(cadena.startsWith( "$")||cadena.startsWith( "%")||cadena.startsWith( "@"))
		{
			if(cadena.startsWith( "$"))
				b=16;
			else if(cadena.startsWith( "%"))
				b=2;
			else if(cadena.startsWith( "@"))
				b=8;
		cadena=cadena.substring(1, cadena.length());
		}
		int bandera=0;
	 int numero = Integer.parseInt( cadena, b);  //El primer parametro es la cadena a convertir y el segundo la base
   
     if(numero<65535)
     {
    	
    	 bandera=1;
    	 return bandera;
     }else{
    	
    	 ;
     }
	return bandera;
	}
	
	
	
	
	public String convertirhexadecimal(String cadena,int bits,int base)
	{
		while(cadena.startsWith( "$")||cadena.startsWith( "%")||cadena.startsWith( "@"))
		{
			
		cadena=cadena.substring(1, cadena.length());
		}
		int numero;
		numero=bits+Integer.parseInt(cadena,base);
		
		return (""+Integer.toHexString(numero)).toUpperCase();
	}
	
	
	public String completarbits(String cadena,int i)
	{ 
		String aux="";
		while((aux.length()+cadena.length())<i)
		{
			aux+="0";
		}
		return aux+cadena;
	}
	
	
	
    public String convertirascii(String cadena,int i)
    {   
    	String cadenaenascii="";
    	int tipo=0;
    	Pattern hexa = Pattern.compile("[$][A-F|0-9]+");
		 Pattern dec = Pattern.compile("[0-9]+");
		 Pattern  oct= Pattern.compile("[@][0-9]+");
		 Pattern bin = Pattern.compile("[%][0-1]+");
		 Matcher hex=hexa.matcher(cadena);
		 Matcher de=dec.matcher(cadena);
		 Matcher oc=oct.matcher(cadena);
		 Matcher bi=bin.matcher(cadena);
		 if(hex.matches())
		 {
			  tipo=16;
		 }
		 else if(de.matches())
			 tipo=10;
		 else if(oc.matches())
			 tipo=8;
		 else if(bi.matches())
			 tipo=2;
    	if(tipo==2)
    	  return Integer.toHexString(Integer.parseInt(cadena,2));
    	else if(tipo==8)
    	  return Integer.toHexString(Integer.parseInt(cadena,8));
    	else if(tipo==10)
          return Integer.toHexString(Integer.parseInt(cadena,10));
    	else if(tipo==16)
        	return Integer.toHexString(Integer.parseInt(cadena,16));
    	else 
    	    for (int x=0;x<cadena.length();x++)
    	    {
    	    	cadenaenascii+=Integer.toHexString(cadena.codePointAt(x));
    	    	
    	    }
    	    	return cadenaenascii;
    }
}
