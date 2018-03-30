package RMI;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class MensagemImp implements Mensagem {
	
		String mensagem;		
	
		public MensagemImp() throws RemoteException {
			super();
			this.mensagem ="";
		}
		
		public void enviarMensagem(String msg) throws RemoteException{		
		    this.mensagem = msg;		    
		}
		public String calculaRaiz(double num){				
			return Double.toString(Math.sqrt(num));
		}
	
		public String lerMensagem() throws RemoteException{
			return calculaRaiz(Double.parseDouble(mensagem));	
	}

}