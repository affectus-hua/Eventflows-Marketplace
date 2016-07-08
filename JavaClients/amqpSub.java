package org.cosmos.marketplace.publicClients;


import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;
import com.rabbitmq.client.impl.Method;

import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
public class amqpSub {

	public amqpSub() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			ConnectionFactory factory = new ConnectionFactory();
			factory.setConnectionTimeout(60000);
		    factory.setHost("IP_OR_DOMAIN_NAME");
		    factory.setPassword("PASSWORD");
		    factory.setPort(5672);
		    factory.setUsername("USERNAME");
		    factory.setAutomaticRecoveryEnabled(true);
		    factory.setVirtualHost("PRODUCER_VHOST");
			
			Connection conn = factory.newConnection();
			
			Channel channel = conn.createChannel();
			
			String queueName = "QUEUE_NAME(SAME AS USERNAME)";//channel.queueDeclare().getQueue();
			String routingKey="";
						
			//consuming

			boolean autoAck = true;
			GetResponse response = channel.basicGet(queueName, autoAck);
			int i=0;
			while ( 1<2){
				if (response == null) {
					// No message retrieved.
					System.out.println("No message retrieved.");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {

					AMQP.BasicProperties props = response.getProps();
					byte[] body = response.getBody();
					long deliveryTag = response.getEnvelope().getDeliveryTag();
					System.out.println("Message retrieved.");//+  Arrays(body.toString()));
					System.out.write(body);

				}
				response = channel.basicGet(queueName, autoAck);
				i=i+1;
			}

			//channel.close();
			//conn.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


