package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ManejadorArchivos{
	public static boolean escribirGuardarArchivo(String filename,String content)throws IOException
	{		
		PrintWriter writer = new PrintWriter(filename, "UTF-8");
		writer.print(content);
		writer.close();
		return true;
	}
	
	public static String leerArchivo(String fileName) throws IOException
	{
		FileReader reader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(reader);
		String contenido = bufferedReader.lines().toString();
		bufferedReader.close();
		return contenido;
	}
	
	public static boolean borrarArchivo(String nombreArchivo)
	{
		File archivo=new File(nombreArchivo);
		return archivo.delete();
	}
}