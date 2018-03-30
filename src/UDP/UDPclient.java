package UDP;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Utils.Utilidades;

public class UDPclient {

	public static void main(String[] args) throws Exception {
		if (args.length != 3) {
			System.out.println("Uso correto UDPClient <servidor> <porta> <dados>");
			System.exit(0);
		}
		FileWriter writer = new FileWriter("C:\\Users\\Aluno\\Documents\\test.csv");// cria o arquivo
		
		for (int i = 0; i < 10000; i++) {

			long tempInicial = System.currentTimeMillis();// inicia contador			

			DatagramSocket udpclient = new DatagramSocket();
			InetAddress server = InetAddress.getByName(args[0]);
			int porta = Integer.parseInt(args[1]);
			byte[] numero = args[2].getBytes();//numero codificado em bytes

			DatagramPacket pedido = new DatagramPacket(numero, numero.length, server, porta);
			udpclient.send(pedido);

			byte[] buffer = new byte[1000];
			DatagramPacket resposta = new DatagramPacket(buffer, buffer.length);
			udpclient.receive(resposta);
			String dadosRecebidos = new String(resposta.getData(), "UTF-8");
			//System.out.println("Resultado: " + dadosRecebidos);
			udpclient.close();

			long tempFinal = System.currentTimeMillis();			
			long dif = (tempFinal - tempInicial);
			Utilidades.addItemCsv(writer, dif);
			//System.out.println(String.format("%d milisegundos", dif));

		}
		writer.close();// fecha o arquivo
		Utilidades.calculaMedia();
	}

	

}
