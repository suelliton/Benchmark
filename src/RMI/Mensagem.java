package RMI;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Mensagem extends Remote {

	public void enviarMensagem(String mensagem) throws RemoteException;
	
	public String lerMensagem() throws RemoteException;
	public String calculaRaiz(double num) throws RemoteException  ;
}