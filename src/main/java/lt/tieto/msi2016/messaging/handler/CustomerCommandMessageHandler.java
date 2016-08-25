package lt.tieto.msi2016.messaging.handler;

import lt.tieto.msi2016.messaging.services.RegistryService;
import lt.tieto.msi2016.messaging.services.RegistryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by localadmin on 16.8.22.
 */
@Component
public class CustomerCommandMessageHandler extends TextWebSocketHandler {

    @Autowired
    private RegistryService registryService;

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String userId = registryService.getPathVariable(session);
        registryService.removeCustomer(userId);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = registryService.getPathVariable(session);
        registryService.addCustomerControlSession(userId,session);

    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String userId = registryService.getPathVariable(session);
        WebSocketSession operatorsSession = registryService.getOperatorControlSession(userId);
        if(operatorsSession != null) {
            operatorsSession.sendMessage(message);
        }
    }


}
