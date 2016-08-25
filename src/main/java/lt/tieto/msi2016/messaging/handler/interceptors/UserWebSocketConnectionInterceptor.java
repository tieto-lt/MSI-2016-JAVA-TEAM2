package lt.tieto.msi2016.messaging.handler.interceptors;

import lt.tieto.msi2016.messaging.services.RegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * Created by localadmin on 16.8.25.
 */
public class UserWebSocketConnectionInterceptor implements HandshakeInterceptor {

    @Autowired
    private RegistryService registryService;

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        String userId = serverHttpRequest.getURI().getPath().substring(serverHttpRequest.getURI().getPath().lastIndexOf("/")).substring(1);
        if(registryService.findOperatorForReservation(userId) == null) {
            serverHttpResponse.setStatusCode(HttpStatus.SERVICE_UNAVAILABLE);
            return false;
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
