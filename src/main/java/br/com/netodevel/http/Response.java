package br.com.netodevel.http;

import lombok.Builder;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Builder
public class Response {

    private PrintWriter printOut;
    private BufferedOutputStream bufferedOutputStream;

    public void defaultResponse(String contentMimeType, String json) throws IOException {
        this.printOut.println("HTTP/1.1 501 Not Implemented");
        this.printOut.println("Server: Java HTTP Server from Netxio: 0.1");
        this.printOut.println("Date:" + new Date());
        this.printOut.println("Content-type: " + contentMimeType);
        this.printOut.println("Content-length: " + json.length());
        this.printOut.println("");
        this.printOut.flush();

        bufferedOutputStream.write(json.getBytes());
        bufferedOutputStream.flush();
    }

}
