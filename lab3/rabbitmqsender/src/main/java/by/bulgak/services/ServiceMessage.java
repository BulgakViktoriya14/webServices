package by.bulgak.services;

import by.bulgak.exception.ExceptionMessage;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ServiceMessage {
    public void sendMessage(String message) throws ExceptionMessage {
        System.out.println("send");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            channel.basicPublish("", "test", null, message.getBytes());
        } catch (TimeoutException | IOException error) {
            System.out.println("Failed to establish connection to RabbitMQ" + error);
            throw new ExceptionMessage("Failed to establish connection to RabbitMQ", error);
        }
    }
}
