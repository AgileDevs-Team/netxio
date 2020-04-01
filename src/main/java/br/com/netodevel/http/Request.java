package br.com.netodevel.http;

import lombok.Builder;
import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;

@Data
@Builder
public class Request {

    private BufferedReader bufferedReader;

    public String getInputRequest() throws IOException {
        return bufferedReader.readLine();
    }
}
