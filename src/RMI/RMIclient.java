package RMI;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Utils.Utilidades;

public class RMIclient {

	public static void main(String args[]) throws IOException {
		FileWriter writer = new FileWriter("C:\\Users\\Aluno\\Desktop\\test.csv");// cria o arquivo
		try {
			String numero = args[0];

			for (int i = 0; i < 10000; i++) {
				long tempInicial = System.currentTimeMillis();// inicia contador
				
				Registry registry = LocateRegistry.getRegistry();

				final Mensagem chat = (Mensagem) Naming.lookup("rmi://localhost:1000/SERVIDOR");
				
				chat.enviarMensagem(numero);
				System.out.println(chat.lerMensagem());
				
				long tempFinal = System.currentTimeMillis();			
				long dif = (tempFinal - tempInicial);
				Utilidades.addItemCsv(writer, dif);
			}
			writer.close();// fecha o arquivo
			Utilidades.calculaMedia();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


}
