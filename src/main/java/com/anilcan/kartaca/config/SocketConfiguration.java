package com.anilcan.kartaca.config;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketConfiguration {

    @Value("${socket.host-name}")
    private String hostName;

    @Value("${socket.port}")
    private Integer port;


    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration configuration = new com.corundumstudio.socketio.Configuration();
        configuration.setHostname(hostName);
        configuration.setPort(port);
        return new SocketIOServer(configuration);
    }

}
