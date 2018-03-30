package PUBSUB;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

public class TopicProducer implements Runnable {

	// Connection Factory which will help in connecting to ActiveMQ serer
	ActiveMQConnectionFactory connectionFactory = null;

	public TopicProducer(ActiveMQConnectionFactory connectionFactory) {
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
				Destination destination = session.createTopic("envia");
				// Create a MessageProducer from
				// the Session to the Topic or Queue
				MessageProducer producer = session.createProducer(destination);
				producer.setDeliveryMode(DeliveryMode.PERSISTENT);
				// Create a messages for the current climate
				String text = "144";
				TextMessage message = session.createTextMessage(text);
				// Send the message to topic
				producer.send(message);
			
				// ------------------recebe-----------------------
			
				Destination topicDestination = session.createTopic("recebe");

				// Create a MessageProducer from the Session
				// to the Topic or Queue
				MessageConsumer messageConsumer = session.createConsumer(topicDestination);

				// Get the message
				Message message2 = messageConsumer.receive();

				TextMessage textMessage = (TextMessage) message2;

				System.out.println("producer recebeu: " + textMessage.getText());			
				
				// Do the cleanup
				session.close();
				connection.close();
		

		} catch (JMSException jmse) {
			System.out.println("Exception: " + jmse.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}