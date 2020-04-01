package br.com.netodevel;

import br.com.netodevel.socket.Server;
import br.com.netodevel.socket.ServerOptions;

public class Netxio {

    public static void main(String[] args) {
        ServerOptions serverOptions = ServerOptions.builder()
                .port(8082)
                .build();

        Server server = new Server(serverOptions);
        server.start();
    }

}
