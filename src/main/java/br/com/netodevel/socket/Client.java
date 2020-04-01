package br.com.netodevel.socket;

import br.com.netodevel.http.Request;
import br.com.netodevel.http.Response;

import java.io.*;
import java.net.Socket;

public class Client implements Runnable {

    private final Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        System.out.println("execute connection --->");
        try {
            final Request request = Request.builder()
                    .bufferedReader(new BufferedReader(new InputStreamReader(socket.getInputStream())))
                    .build();

            System.out.println("INPUT =======");
            System.out.println(request.getInputRequest());

            final Response response = Response.builder()
                    .bufferedOutputStream(new BufferedOutputStream(socket.getOutputStream()))
                    .printOut(new PrintWriter(socket.getOutputStream()))
                    .build();

            response.defaultResponse("application/json", "{json_fake}");

        } catch (IOException e) {
            System.out.println("error = " + e.getMessage());
        }
    }
}
