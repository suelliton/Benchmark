package JGROUPS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;

import Utils.Utilidades;

public class JGclient extends ReceiverAdapter {
	JChannel recebe;
	public void await() {
		try {
			recebe = new JChannel();
			recebe.setReceiver(this);
			recebe.connect("recebe");
		} catch (Exception e) {
			return;
		}
	}

	public void raise(String numero) {
		try {
			JChannel envia = new JChannel();
			envia.connect("envia");
			Message msg = new Message(null, numero);
			envia.send(msg);
			envia.close();
		} catch (Exception e) {
		}
	}

	public void receive(Message msg) {
		String line = msg.getObject();
		//System.out.println(line + "é O RESULTADO");
	}

	public static void main(String[] args) throws Exception {
		String numero = args[0];
		JGclient client = new JGclient();
		FileWriter writer = new FileWriter("C:\\Users\\Aluno\\Desktop\\test.csv");// cria o arquivo
		client.await();//cliente ouvindo
		for (int i = 0; i < 100; i++) {
			long tempInicial = System.currentTimeMillis();// inicia contador			
			client.raise(numero);//cliente envia			
			long tempFinal = System.currentTimeMillis();			
			long dif = (tempFinal - tempInicial);
			Utilidades.addItemCsv(writer, dif);						
		}
		
		
		writer.close();// fecha o arquivo
		Utilidades.calculaMedia();

	}
	
	
	
}
