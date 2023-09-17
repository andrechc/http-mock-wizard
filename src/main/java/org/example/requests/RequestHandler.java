package org.example.requests;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.mocks.files.HttpResponse;
import org.example.mocks.repository.impl.MocksFilesRepositoryImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class RequestHandler implements HttpHandler {

    private static final MocksFilesRepositoryImpl fileHandler = new MocksFilesRepositoryImpl();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            final String httpMethod = exchange.getRequestMethod();
            final String uri = exchange.getRequestURI().toString();
            final String body = extractBody(exchange.getRequestBody());
            System.out.println(httpMethod + " " + uri);
            System.out.println(body);
            exchange.getRequestHeaders().values().forEach(System.out::println);
/*
                final String responseBody = activeMocksHandler(uri);
*/
            final Optional<HttpResponse> byUri = fileHandler.findByUri(uri);
/*                exchange.sendResponseHeaders(200, responseBody.getBytes().length);
                exchange.getResponseBody().write(responseBody.getBytes());*/
            exchange.close();
            System.out.println("------RESPONSE SENT------");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("------ERROR------");
        }
    }

    private static String extractBody(InputStream body) throws IOException {
        StringBuilder requestBodyBuilder = new StringBuilder();
        int bytesRead;
        byte[] buffer = new byte[1024];
        while ((bytesRead = body.read(buffer)) != -1) {
            requestBodyBuilder.append(new String(buffer, 0, bytesRead));
        }
        return requestBodyBuilder.toString();
    }
}