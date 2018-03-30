package Utils;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Utilidades {
	public static String calculaRaiz(double num) {
		return Double.toString(Math.sqrt(num));
	}
	public static void calculaMedia() throws Exception {
		List numeros = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream("C:\\Users\\Notyleus\\Desktop\\test.csv")));
		String linha = null;
		while ((linha = reader.readLine()) != null) {
			String[] dados = linha.split("/n");
			// System.out.println(Arrays.toString(dados));
			numeros.add(dados[0]);
		}
		reader.close();
		double soma = 0;
		for (int i = 0; i < numeros.size(); i++) {
			soma = soma + Double.parseDouble((String) numeros.get(i));
		}
		double media = soma / numeros.size();
		System.out.println("A media é: " + media);

	}

	public static void addItemCsv(FileWriter writer, long num) {
		try {
			writer.append(Long.toString(num));
			writer.append('\n');
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
