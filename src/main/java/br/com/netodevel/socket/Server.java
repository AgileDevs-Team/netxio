package br.com.netodevel.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Date;

public class Server {

    private final ServerOptions serverOptions;

    public Server(ServerOptions serverOptions) {
        this.serverOptions = serverOptions;
    }

    public void start() {
        try {
            ServerSocket serverSocket = createServerSocket();
            System.out.println("Server started!");

            openConnections(serverSocket);
        } catch (IOException e) {
            System.out.println("error = " + e.getMessage());
        }
    }

    private void openConnections(ServerSocket serverSocket) throws IOException {
        while (true) {
            Client client = new Client(serverSocket.accept());
            System.out.println("connection opened: " + new Date());

            Thread thread = new Thread(client);
            thread.start();
        }
    }

    public ServerSocket createServerSocket() throws IOException {
        return new ServerSocket(serverOptions.getPort());
    }

}
