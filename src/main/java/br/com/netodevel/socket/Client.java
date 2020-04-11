package br.com.netodevel.socket;

import br.com.netodevel.http.HttpExchange;
import br.com.netodevel.http.Request;
import br.com.netodevel.http.Response;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Client implements Runnable {

    private final Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        log.info("execute connection");
        try {
            HttpExchange exchange = new HttpExchange();

            BufferedReader input = exchange.getInput(socket);

            String firstLine = input.readLine();

            while (firstLine.isEmpty()) {
                firstLine = input.readLine();
            }

            String[] firstSplit = firstLine.split(" ");

            final String method = firstSplit[0];
            final String url = firstSplit[1];
            final String httpVersion = firstSplit[2];

            Map<String, String> headers = getHeaders(input);

            final Request request = Request.builder()
                    .url(url)
                    .method(method)
                    .httpVersion(httpVersion)
                    .headers(headers)
                    .build();

            System.out.println(request);

            final Response response = Response.builder()
                    .bufferedOutputStream(new BufferedOutputStream(socket.getOutputStream()))
                    .printOut(new PrintWriter(socket.getOutputStream()))
                    .build();

            response.defaultResponse("application/json", "{json_fake}");

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private Map<String, String> getHeaders(BufferedReader input) throws IOException {
        Map<String, String> headers = new HashMap<>();
        for (String line = input.readLine(); line != null && !line.isEmpty(); line = input.readLine()) {
            String[] splitLine = line.split(":");
            headers.put(splitLine[0], splitLine[1]);
        }
        return headers;
    }

}
