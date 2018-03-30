package PUBSUB;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import Utils.Utilidades;

public class TopicConsumer implements Runnable {

	ActiveMQConnectionFactory connectionFactory = null;

	public TopicConsumer(ActiveMQConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	

	@Override
	public void run() {
		try {
			
				// First create a connection
				Connection connection = connectionFactory.createConnection();
				connection.start();

				// Now create a Session
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

				// Let's create a topic. If the topic exist,
				// it will return that
				Destination topicDestination = session.createTopic("envia");

				// Create a MessageProducer from the Session
				// to the Topic or Queue
				MessageConsumer messageConsumer = session.createConsumer(topicDestination);

				// Get the message
				Message message = messageConsumer.receive();

				TextMessage textMessage = (TextMessage) message;
				double numero = Double.parseDouble(textMessage.getText());
				System.out.println(textMessage.getText());
				
				// --------------------envia---------
			

				Destination destination = session.createTopic("recebe");
				// Create a MessageProducer from
				// the Session to the Topic or Queue
				MessageProducer producer = session.createProducer(destination);
				producer.setDeliveryMode(DeliveryMode.PERSISTENT);
				// Create a messages for the current climate
				String text = Utilidades.calculaRaiz(numero);
				TextMessage message2 = session.createTextMessage(text);
				// Send the message to topic
				producer.send(message2);

				// Do the cleanup
				session.close();
				connection.close();
			
		} catch (JMSException jmse) {
			System.out.println("Exception: " + jmse.getMessage());
		}
	}
}