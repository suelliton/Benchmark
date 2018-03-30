package TCP;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import Utils.Utilidades;

public class TCPclient {
	public static void main(String[] args) throws Exception {
		if (args.length < 3) {
			System.out.println("Uso correto: TCPClient <host> <post> <message>");
			System.exit(0);
		}

		FileWriter writer = new FileWriter("C:\\Users\\Aluno\\Documents\\test.csv");// cria o arquivo

		String ip = args[0];
		String porta = args[1];
		String numero = args[2];
		String modifiedMessage;
		
		
		for (int i = 0; i < 100000; i++) {
			long tempInicial = System.currentTimeMillis();// inicia contador
			Socket socket = new Socket(ip, Integer.parseInt(porta));			
			DataInputStream in = new DataInputStream(socket.getInputStream());
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(numero);
			modifiedMessage = in.readUTF();
			System.out.println("Resultado: " + modifiedMessage);
			socket.close();

			long tempFinal = System.currentTimeMillis();
			long dif = (tempFinal - tempInicial);
			Utilidades.addItemCsv(writer, dif);			
			
		}
		Utilidades.calculaMedia();

	}

	
}