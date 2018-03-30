package PUBSUB;

import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import Utils.Utilidades;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMQMain {
	

	public static void main(String[] args) throws Exception {
		// Create the connection factory
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

		FileWriter writer = new FileWriter("C:\\Users\\Notyleus\\Desktop\\test.csv");// cria o arquivo

		for (int i = 0; i < 1000; i++) {
			long tempInicial = System.currentTimeMillis();// inicia contador
			// Create the consumer. It will wait to listen to the Topic
			Thread topicConsumerThread = new Thread(new TopicConsumer(connectionFactory));
			topicConsumerThread.start();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Create a message. As soon as the message is published on the Topic,
			// it will be consumed by the consumer

			Thread topicProducerThread = new Thread(new TopicProducer(connectionFactory));
			topicProducerThread.start();

			long tempFinal = System.currentTimeMillis();
			long dif = (tempFinal - tempInicial);
			Utilidades.addItemCsv(writer, dif);
		}

		writer.close();// fecha o arquivo
		Utilidades.calculaMedia();
	}

}