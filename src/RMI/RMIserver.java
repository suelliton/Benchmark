package RMI;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class RMIserver extends MensagemImp {

	public RMIserver() throws RemoteException {

		try {
			MensagemImp obj = new MensagemImp();// instancia a implementacao de
												// servidor

			Mensagem stub = (Mensagem) UnicastRemoteObject.exportObject(obj, 0);
			Naming.rebind("rmi://localhost:1000/SERVIDOR", stub);
			System.out.println("Server ready");

		} catch (Exception e) {

			System.out.println("Erro:" + e);

		}
	}

	public static void main(String args[]) {
		try {
			java.rmi.registry.LocateRegistry.createRegistry(1000);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			new RMIserver();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
