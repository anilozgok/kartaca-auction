package com.anilcan.kartaca.socket;

import com.anilcan.kartaca.model.entity.Bid;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SocketModule {
    private SocketIOServer socketIOServer;

    public SocketModule(SocketIOServer socketIOServer) {
        this.socketIOServer = socketIOServer;
        socketIOServer.addConnectListener(onConnect());
        socketIOServer.addDisconnectListener(onDisconnect());
        socketIOServer.addEventListener("makeBid", Bid.class, onBidRecieved());
    }

    public DataListener<Bid> onBidRecieved() {
        return (senderClient, data, ackSender) -> {
            log.info(String.format("%s -> %s", senderClient.getSessionId(), data.toString()));
            senderClient.getNamespace().getBroadcastOperations().sendEvent("getBidDetails", data);
        };
    }

    private ConnectListener onConnect() {
        return socketIOClient -> {
            log.info(String.format("socketId: %s connected", socketIOClient.getSessionId().toString()));
        };
    }

    private DisconnectListener onDisconnect() {
        return socketIOClient -> {
            log.info(String.format("socketId: %s disconnected", socketIOClient.getSessionId().toString()));
        };
    }

}
