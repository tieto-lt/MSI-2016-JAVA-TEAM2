package lt.tieto.msi2016.messaging;

import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

/**
 * Created by localadmin on 16.8.22.
 */
public interface UserRegistry {

    Map<String, WebSocketSession> getOperatorRegistry();

    Map<String, WebSocketSession> getCustomerRegistry();

    Map<String, WebSocketSession> getReservationRegistry();

}
