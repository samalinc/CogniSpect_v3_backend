package com.bsuir.cognispect.socket;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SocketServer {

    private SocketIOServer socketIOServer;

    public SocketServer() {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(10000);

        socketIOServer = new SocketIOServer(config);

        setupListeners();
        log.info("Start SocketIO server");
        socketIOServer.start();
    }

    private void setupListeners() {
        socketIOServer.addConnectListener((socketIOClient) -> {
            log.info(socketIOClient.toString() + "is connected");
        });
        socketIOServer.addEventListener("new_message", Object.class, (socketIOClient, someObject, ackRequest) -> {
            log.info("Event: new_message triggered by " + socketIOClient.toString());
            if (ackRequest.isAckRequested()) {
                ackRequest.sendAckData(someObject.toString());
            }
        });
    }
}
