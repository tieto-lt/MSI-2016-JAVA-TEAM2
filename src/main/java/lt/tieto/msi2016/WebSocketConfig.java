package lt.tieto.msi2016;

import lt.tieto.msi2016.utils.services.SecurityHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.annotation.Resource;

/**
 * Created by localadmin on 16.8.22.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Resource
    private BinaryWebSocketHandler operatorVideoMessageHandler;
    @Resource
    private TextWebSocketHandler operatorCommandMessageHandler;
    @Resource
    private TextWebSocketHandler customerCommandMessageHandler;
    @Autowired
    private SecurityHolder securityHolder;

    @Bean
    public BinaryWebSocketHandler customerVideoMessageHandler() {
        return new BinaryWebSocketHandler() {
            @Override
            public void afterConnectionEstablished(WebSocketSession session) throws Exception {
                System.out.println(session.getUri().getPath() + " sss");
                super.afterConnectionEstablished(session);
            }
        };
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(operatorVideoMessageHandler,"/ws/video/**");
        webSocketHandlerRegistry.addHandler(operatorCommandMessageHandler,"/ws/api/**");
        webSocketHandlerRegistry.addHandler(customerVideoMessageHandler(),"/dronestream/{userId}");
        webSocketHandlerRegistry.addHandler(customerCommandMessageHandler,"/ws/control/**");
    }
}
