package by.bulgak;

import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SendToLambda implements DeliverCallback {
    private final static String LAMBDA_URL = "https://7guaat1tqj.execute-api.us-east-1.amazonaws.com/dev/message/create/";
    private final static String FROM = "bulgak.viktoriya@gmail.com";
    private final static String TO = "bulgak.viktoriya@gmail.com";
    private final static String SUBJECT = "Message from RabbitMQ";
    private final static String SMTP_HOST = "email-smtp.us-west-2.amazonaws.com";
    private final static String SMTP_USERNAME = "AKIAZNYRQLQJJGLXP6GC";
    private final static String SMTP_PASSWORD = "BGz79zTTCjFjMfm97bbKMEV5yJOsHo8SqyoJD5SqQAIq";

    @Override
    public void handle(String s, Delivery delivery) {
        String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
        System.out.println("Received message: " + message);
        String processedMessage;
        try {
            processedMessage = requestProcessedMessage(message).replaceAll("^\"(.*)\"$", "$1");
        } catch (IOException e) {
            System.out.println("Failed to get processed message from lambda" + e);
            return;
        }
        sendEmail(processedMessage);
    }

    private String requestProcessedMessage(String message) throws IOException {
        HttpPost post = new HttpPost(LAMBDA_URL);
        StringEntity myEntity = new StringEntity(message, ContentType.create("text/plain", "UTF-8"));
        post.setEntity(myEntity);
        String result;
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)){
            result = EntityUtils.toString(response.getEntity());
        }

        return result;
    }

    private void sendEmail(String text) {
        System.out.println("Sending email");
        Email email = EmailBuilder.startingBlank()
                .from(FROM)
                .to(TO)
                .withSubject(SUBJECT)
                .withHTMLText(text)
                .buildEmail();

        Mailer mailer = MailerBuilder
                .withSMTPServer(SMTP_HOST, 587, SMTP_USERNAME, SMTP_PASSWORD)
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withSessionTimeout(10 * 1000)
                .clearEmailAddressCriteria() // turns off email validation
                .withDebugLogging(true)
                .buildMailer();

        mailer.sendMail(email);
        System.out.println("Message sent successfully");
    }
}
