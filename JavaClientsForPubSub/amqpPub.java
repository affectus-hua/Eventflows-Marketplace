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
public class amqpPub {

	public amqpPub() {
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
		    factory.setVirtualHost("PRODUCER_VHOST(SAME AS USER NAME)");
		   
			Connection conn = factory.newConnection();
			
			Channel channel = conn.createChannel();
			
			String exchangeName="EXCHANGE_NAME(EVENT NAME)";
				
			String routingKey="";
			byte[] messageBodyBytes = "Hello, COSMOS world in Traffic 333!".getBytes();
			channel.basicPublish(exchangeName, "", null, messageBodyBytes);
			System.out.println("Publishing...");
						
			channel.close();
			conn.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}
}


