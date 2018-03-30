package TCP;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPserver {
	public static void main(String argv[]) throws Exception {
		String palavra;
		String dadoRecebido;
		ServerSocket socket = new ServerSocket(6789);
		while (true) {
			Socket conexao = socket.accept();
			DataInputStream recebe = new DataInputStream(conexao.getInputStream());
			DataOutputStream responde = new DataOutputStream(conexao.getOutputStream());
			dadoRecebido = recebe.readUTF();
			System.out.println("Recebeu: " + dadoRecebido);
			String resultado = calculaRaiz(Double.parseDouble(dadoRecebido));
			responde.writeUTF(resultado);
		}
	}
	
	public static String calculaRaiz(double num){				
		return Double.toString(Math.sqrt(num));
	}
	
}