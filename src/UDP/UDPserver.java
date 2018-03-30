package UDP;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPserver {
	public static void main(String[] args) throws Exception {
		
		DatagramSocket socket = new DatagramSocket(6789);
		byte[] buffer = new byte[1000];
		
		while(true) {
			DatagramPacket recebe = new DatagramPacket(buffer, buffer.length);
			socket.receive(recebe);
			byte[] bufferRecebido = recebe.getData();
			InetAddress ip = recebe.getAddress();
			int porta =  recebe.getPort();
			//System.out.println("Buffer: "+ bufferRecebido);
			
			String dadoRecebido = new String(bufferRecebido,"UTF-8");
			//System.out.println("Server Recebeu: "+ dadoRecebido);
			String resultado = calculaRaiz(Double.parseDouble(dadoRecebido));
			//System.out.println("Server enviou: "+ resultado);
			DatagramPacket envia = new DatagramPacket(resultado.getBytes(),resultado.getBytes().length,ip,porta );
			socket.send(envia);
		}
	}
	
	public static String calculaRaiz(double num){				
		return Double.toString(Math.sqrt(num));
	}
}
