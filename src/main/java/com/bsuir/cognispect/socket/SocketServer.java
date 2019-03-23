package com.bsuir.cognispect.socket;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.stereotype.Component;


@Component
public class SocketServer {

    private SocketIOServer socketIOServer;

    public SocketServer() {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(10000);

        socketIOServer = new SocketIOServer(config);

        setupListeners();
        socketIOServer.start();
    }

    private void setupListeners() {
        socketIOServer.addEventListener("new_message", Object.class, (socketIOClient, someObject, ackRequest) -> {
            if (ackRequest.isAckRequested()) {
                ackRequest.sendAckData(someObject.toString());
            }
        });
    }
}
