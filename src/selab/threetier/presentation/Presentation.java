package selab.threetier.presentation;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;

public abstract class Presentation implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response;
        byte[] responseBytes;
        try {
            response = exec(httpExchange.getRequestMethod(), httpExchange.getRequestBody());
            responseBytes = response.getBytes("UTF-8");
            Headers httpHeaders = httpExchange.getResponseHeaders();
            Hashtable<String, String> headers = this.getResponseHeaders();
            for (String key: headers.keySet()) {
                httpHeaders.add(key, headers.get(key));
            }
            httpExchange.sendResponseHeaders(200, responseBytes.length);
        } catch (Exception ex) {
            response = ex.toString();
            responseBytes = response.getBytes("UTF-8");
            httpExchange.sendResponseHeaders(500, responseBytes.length);
        }
        OutputStream os = httpExchange.getResponseBody();
        os.write(responseBytes);
        os.close();
    }

    public abstract String exec(String method, InputStream body) throws IOException;
    public abstract Hashtable<String, String> getResponseHeaders();
}
