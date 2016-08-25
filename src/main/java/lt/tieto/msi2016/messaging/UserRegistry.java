package lt.tieto.msi2016.messaging;

import lt.tieto.msi2016.messaging.model.WebSocketSessionHolder;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

/**
 * Created by localadmin on 16.8.22.
 */
public interface UserRegistry {

    Map<String, WebSocketSessionHolder> getOperatorRegistry();

    Map<String, WebSocketSessionHolder> getCustomerRegistry();

}
