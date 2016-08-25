package lt.tieto.msi2016.messaging.handler.interceptors;

import lt.tieto.msi2016.operator.services.OperatorService;
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
public class OperatorTokenValidityInterceptor implements HandshakeInterceptor {

    @Autowired
    private OperatorService operatorService;

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        String operatorToken = serverHttpRequest.getURI().getPath().substring(serverHttpRequest.getURI().getPath().lastIndexOf("/")).substring(1);
        if(!operatorService.isVerified(operatorToken)){
            serverHttpResponse.setStatusCode(HttpStatus.FORBIDDEN);
            return false;
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
