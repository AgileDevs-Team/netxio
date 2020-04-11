package br.com.netodevel.http;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class Request {

    private Map<String, String> headers;
    private String url;
    private String method;
    private String httpVersion;

}
