package conversion.ficheros;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class convertPropertiesToJson {

	public final static String FICHERO_ENTRADA = "C:\\Users\\mgonzcas\\Desktop\\errores.txt";
	public final static String FICHERO_SALIDA = "C:\\Users\\mgonzcas\\Desktop\\errores.json";

	public static void main(String[] args) throws IOException {
		String lineaRead;
		StringBuilder lineaWrite = new StringBuilder();
		String fichero = FICHERO_ENTRADA;
		FileReader f = new FileReader(fichero);
		BufferedReader b = new BufferedReader(f);
		while ((lineaRead = b.readLine()) != null) {
			lineaWrite.append("\n").append(transformaLinea(lineaRead));
		}
		b.close();
		escribeLineFichero(lineaWrite.toString());
	}

	private static void escribeLineFichero(String contenido) {
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter(FICHERO_SALIDA);
			pw = new PrintWriter(fichero);

			pw.print(contenido);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Nuevamente aprovechamos el finally para
				// asegurarnos que se cierra el fichero.
				if (null != fichero) {
					fichero.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	private static String transformaLinea(String lineaRead) {
		StringBuilder sb = new StringBuilder();
		if (null != lineaRead && !"".equals(lineaRead)) {
			String[] claveValor = lineaRead.split("=");
			if (null != claveValor && claveValor.length > 1 
					&& claveValor[0] != null && claveValor[1] != null) {
				sb.append("\"");
				sb.append(claveValor[0].trim());
				sb.append("\": ");
				sb.append("\"");
				sb.append(claveValor[1].trim());
				sb.append("\",");
			}
		}
		return sb.toString();
	}

}
