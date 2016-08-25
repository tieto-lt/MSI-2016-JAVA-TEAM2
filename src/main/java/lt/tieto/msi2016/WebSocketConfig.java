package lt.tieto.msi2016;

import lt.tieto.msi2016.utils.services.SecurityHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

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
    @Resource
    private BinaryWebSocketHandler customerVideoMessageHandler;
    @Autowired
    private SecurityHolder securityHolder;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(operatorVideoMessageHandler,"/ws/video/**");
        webSocketHandlerRegistry.addHandler(operatorCommandMessageHandler,"/ws/api/**");
        webSocketHandlerRegistry.addHandler(customerVideoMessageHandler,"/dronestream/**");
        webSocketHandlerRegistry.addHandler(customerCommandMessageHandler,"/ws/control/**");
    }

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(8192);
        container.setMaxBinaryMessageBufferSize(80000);
        return container;
    }
}
