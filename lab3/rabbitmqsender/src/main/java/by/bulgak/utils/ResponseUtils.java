package by.bulgak.utils;

import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtils {
    public static Map<String, String> errorMessage(Response response, String message) {
        return message(response, message, Status.BAD_REQUEST);
    }

    public static Map<String, String> message(Response response, String message, int status) {
        response.status(status);
        Map<String, String> result = new HashMap<>();
        result.put("message", message);
        return result;
    }

    public static Map<String, String> successMessage(Response response, String message) {
        return message(response, message, Status.OK);
    }
}
