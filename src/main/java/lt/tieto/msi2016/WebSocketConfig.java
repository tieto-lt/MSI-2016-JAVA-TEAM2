package lt.tieto.msi2016;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;

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
    private BinaryWebSocketHandler customerVideoMessageHandler;
    @Resource
    private TextWebSocketHandler customerCommandMessageHandler;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(operatorVideoMessageHandler,"/ws/video/**");
        webSocketHandlerRegistry.addHandler(operatorCommandMessageHandler,"/ws/api/**");
        webSocketHandlerRegistry.addHandler(customerVideoMessageHandler,"/ws/stream/**");
        webSocketHandlerRegistry.addHandler(customerCommandMessageHandler,"/ws/control/**");
    }
}
