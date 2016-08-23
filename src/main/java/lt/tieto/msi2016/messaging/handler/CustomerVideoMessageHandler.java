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
public class CustomerVideoMessageHandler extends BinaryWebSocketHandler {

    @Autowired
    private RegistryService registryService;

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String userId = registryService.getPathVariable(session);
        registryService.cancelReservation(userId);
        registryService.removeCustomer(userId);
        System.out.println("connection closed");
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = registryService.getPathVariable(session);
        registryService.addCustomer(userId,session);
        WebSocketSession operatorsSession = registryService.getFreeOperatorsSession();
        registryService.removeOperator(registryService.getPathVariable(operatorsSession));
        registryService.reserveOperator(userId,operatorsSession);
        System.out.println("connection opened");

    }

}
