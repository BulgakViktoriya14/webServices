package by.bulgak;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receiver {
    public void listen() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            System.out.println("Waiting for messages.");
            DeliverCallback deliverCallback = new SendToLambda();
            while (true) {
                channel.basicConsume("test", true, deliverCallback, consumerTag -> { });
            }
        } catch (TimeoutException | IOException e) {
            System.out.println("Failed to establish connection to RabbitMQ" + e);
            throw new IllegalStateException("Failed to establish connection to RabbitMQ", e);
        }
    }
}
