package br.com.netodevel.http;

import lombok.Builder;
import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

@Data
@Builder
public class Request {

    private BufferedReader bufferedReader;
    private Map<String, String> headers;

    public String getInputRequest() throws IOException {
        return bufferedReader.readLine();
    }


}
