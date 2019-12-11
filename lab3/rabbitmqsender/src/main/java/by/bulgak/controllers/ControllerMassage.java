package by.bulgak.controllers;

import by.bulgak.exception.ExceptionMessage;
import by.bulgak.services.ServiceMessage;
import by.bulgak.utils.ConverterJson;
import by.bulgak.utils.ResponseUtils;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Map;

public class ControllerMassage {
    private final static String MESSAGE_KEY = "message";
    private ServiceMessage serviceMessage = new ServiceMessage();

    public final Route sendMessage = (Request request, Response response) -> {
        Map<String, String> bodyParams = ConverterJson.convertJsonBodyToMap(request);
        if (!bodyParams.containsKey(MESSAGE_KEY)) {
            return ResponseUtils.errorMessage(response, "Invalid request body");
        }
        String message = bodyParams.get(MESSAGE_KEY);
        System.out.println("Sending message: " + message);
        try {
            serviceMessage.sendMessage(message);
            System.out.println("Message sent successfully");
            return ResponseUtils.successMessage(response, "Message sent successfully");
        } catch (ExceptionMessage error) {
            System.out.println("Failed to send message" + error);
            return ResponseUtils.errorMessage(response, error.getMessage());
        }
    };
}
