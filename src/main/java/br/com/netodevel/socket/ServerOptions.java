package br.com.netodevel.socket;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServerOptions {

    private Integer port;
}
