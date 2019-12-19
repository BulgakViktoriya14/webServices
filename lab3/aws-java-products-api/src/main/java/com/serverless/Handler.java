package com.serverless;

import java.util.Collections;
import java.util.Map;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Handler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {
	private final static String BODY_KEY = "body";


	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		System.out.println("received: {}" + input);
		String requestBody = (String) input.get(BODY_KEY);
		String responseBody = filter(requestBody);
		return ApiGatewayResponse.builder()
				.setStatusCode(200)
				.setObjectBody(responseBody)
				.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
				.build();
	}

	private String filter(String content) {
		return "Сообщение: <b>" + content+ "</b>";
	}
}
