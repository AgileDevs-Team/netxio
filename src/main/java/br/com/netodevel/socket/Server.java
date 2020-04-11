package br.com.netodevel.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Date;

@Slf4j
public class Server {

    private final ServerOptions serverOptions;

    public Server(ServerOptions serverOptions) {
        this.serverOptions = serverOptions;
    }

    public void start() {
        try {
            ServerSocket serverSocket = createServerSocket();
            log.info("Starting HttpServer at http://127.0.0.1:" + serverOptions.getPort());

            openConnections(serverSocket);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void openConnections(ServerSocket serverSocket) throws IOException {
        while (true) {
            Client client = new Client(serverSocket.accept());
            log.info("open to clients, date = {}", new Date());

            Thread thread = new Thread(client);
            thread.start();
        }
    }

    public ServerSocket createServerSocket() throws IOException {
        return new ServerSocket(serverOptions.getPort());
    }

}
