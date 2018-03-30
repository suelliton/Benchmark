package JGROUPS;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;

public class JGserver extends ReceiverAdapter {
	
	public void await() {
	    try {
	    	    JChannel channel = new JChannel(); 
	            channel.setReceiver(this);
	            channel.connect("envia"); 
	    } catch(Exception e) {
	            return;
	    }
    }
	
	public void raise(String resultado) {
		try {
			JChannel channel = new JChannel();
		 	channel.connect("recebe"); 
			Message msg = new Message(null, resultado);			
			channel.send(msg);
			channel.close();
		} 
		catch(Exception e) { 
		}
	}
    public void receive(Message msg) {
        String line =  msg.getObject();
        //System.out.println(line+"CHEGOU NO SERVER ");
        String resultado = calculaRaiz(Double.parseDouble(line));        
        raise(resultado);       
    }
	public String calculaRaiz(double num){				
		return Double.toString(Math.sqrt(num));
	}
	public static void main(String[] args) {
		JGserver server = new JGserver();		
		//alarm.raise();
		server.await();
		
	}
	
	
	
}