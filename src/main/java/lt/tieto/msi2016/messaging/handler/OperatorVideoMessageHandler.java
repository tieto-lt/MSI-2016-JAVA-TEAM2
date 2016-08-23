package lt.tieto.msi2016.messaging.handler;

import lt.tieto.msi2016.messaging.services.RegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

/**
 * Created by localadmin on 16.8.22.
 */
@Component
public class OperatorVideoMessageHandler extends BinaryWebSocketHandler {

    @Autowired
    private RegistryService registryService;

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String operatorToken = registryService.getPathVariable(session);
        registryService.removeOperator(operatorToken);
        System.out.println("connection closed");
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String operatorToken = registryService.getPathVariable(session);
        registryService.addOperator(operatorToken,session);
        System.out.println("connection opened");

    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        WebSocketSession userSocketSession = registryService.getCustomerSessionByOperatorSession(session);
        if(userSocketSession != null) {
            userSocketSession.sendMessage(message);
        }
    }
}
