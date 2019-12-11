package by.bulgak;

import by.bulgak.controllers.ControllerMassage;
import by.bulgak.utils.TransformerJson;

import static spark.Spark.port;
import static spark.Spark.post;

public class App {
    private final static String SEND_MESSAGE_URL = "/message/send/";

    private ControllerMassage controllerMassage = new ControllerMassage();

    private TransformerJson transformerJson = new TransformerJson();

    public void run() {
        port(4567);
        post(SEND_MESSAGE_URL, controllerMassage.sendMessage, transformerJson);
    }
}