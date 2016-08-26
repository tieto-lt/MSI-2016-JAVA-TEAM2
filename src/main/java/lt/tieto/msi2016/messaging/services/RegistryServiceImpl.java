package lt.tieto.msi2016.messaging.services;

import lt.tieto.msi2016.messaging.UserRegistry;
import lt.tieto.msi2016.messaging.model.WebSocketSessionHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by localadmin on 16.8.22.
 */
@Service
public class RegistryServiceImpl implements RegistryService {

    private UserRegistry userRegistry;

    @PostConstruct
    private void initRegistryService(){
        userRegistry = new UserRegistry() {

            private final Map<String,WebSocketSessionHolder> operatorRegistry = new HashMap<>();

            private final Map<String,WebSocketSessionHolder> customerRegistry = new HashMap<>();

            @Override
            public Map<String, WebSocketSessionHolder> getOperatorRegistry() {
                return operatorRegistry;
            }

            @Override
            public Map<String, WebSocketSessionHolder> getCustomerRegistry() {
                return customerRegistry;
            }

        };
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public synchronized void addCustomerVideoSession(String userId, WebSocketSession session) {
        WebSocketSessionHolder userSessionHolder = findUserSessionHolder(userId);
        String operatorToken = findOperatorForReservation(userId);
        userSessionHolder.setVideoSession(session);
        userSessionHolder.setMapKey(operatorToken);
        userRegistry.getOperatorRegistry().get(operatorToken).setMapKey(userId);
    }

    private WebSocketSessionHolder findUserSessionHolder(String userId) {
        WebSocketSessionHolder userSessionHolder = userRegistry.getCustomerRegistry().get(userId);
        if(userSessionHolder == null) {
            userSessionHolder =  new WebSocketSessionHolder();
            userRegistry.getCustomerRegistry().put(userId,userSessionHolder);
        }
        return userSessionHolder;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public synchronized void addCustomerControlSession(String userId, WebSocketSession session) {
        WebSocketSessionHolder userSessionHolder = findUserSessionHolder(userId);
        String operatorToken = findOperatorForReservation(userId);
        userSessionHolder.setControlSession(session);
        userSessionHolder.setMapKey(operatorToken);
        userRegistry.getOperatorRegistry().get(operatorToken).setMapKey(userId);
    }

    /**
     * {@inheritDoc}
     */
    public synchronized String findOperatorForReservation(String userId){
        String freeOperatorToken = null;

        for(Map.Entry<String,WebSocketSessionHolder> entry :  userRegistry.getOperatorRegistry()
                .entrySet()){
            if(userId.equals(entry.getValue().getMapKey())){
                return entry.getKey();
            } else if(freeOperatorToken == null && entry.getValue().getMapKey() == null) {
                freeOperatorToken = entry.getKey();
            }

        }
        return freeOperatorToken;
    }

    private WebSocketSessionHolder findOperatorSessionHolder(String operatorToken) {
        WebSocketSessionHolder operatorSessionHolder = userRegistry.getOperatorRegistry().get(operatorToken);
        if(operatorSessionHolder == null) {
            operatorSessionHolder =  new WebSocketSessionHolder();
            userRegistry.getOperatorRegistry().put(operatorToken,operatorSessionHolder);
        }
        return operatorSessionHolder;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public synchronized void addOperatorVideoSession(String operatorToken, WebSocketSession session) {
        WebSocketSessionHolder operatorSessionHolder = findOperatorSessionHolder(operatorToken);
        operatorSessionHolder.setVideoSession(session);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public synchronized void addOperatorControlSession(String operatorToken, WebSocketSession session) {
        WebSocketSessionHolder operatorSessionHolder = findOperatorSessionHolder(operatorToken);
        operatorSessionHolder.setControlSession(session);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public synchronized void removeOperator(String operatorToken){
        WebSocketSessionHolder operatorsSessionHolder = userRegistry.getOperatorRegistry().get(operatorToken);
        if(operatorsSessionHolder != null) {
            WebSocketSessionHolder customersSessionHolder = userRegistry.getCustomerRegistry().get(operatorsSessionHolder.getMapKey());
            if (customersSessionHolder != null) {
                customersSessionHolder.setMapKey(null);
            }
            userRegistry.getOperatorRegistry().remove(operatorToken);
        }
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public synchronized void removeCustomer(String userId){
        WebSocketSessionHolder customerSessionHolder = userRegistry.getCustomerRegistry().get(userId);
        if(customerSessionHolder != null) {
            WebSocketSessionHolder operatorsSessionHolder = userRegistry.getOperatorRegistry().get(customerSessionHolder.getMapKey());
            if (operatorsSessionHolder != null) {
                operatorsSessionHolder.setMapKey(null);
            }
            userRegistry.getCustomerRegistry().remove(userId);
        }
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getPathVariable(WebSocketSession session) {
        return session.getUri().getPath().substring(session.getUri().getPath().lastIndexOf("/")).substring(1);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public WebSocketSession getCustomerVideoSession(String operatorToken) {
        WebSocketSessionHolder customerSessionHolder = userRegistry.getCustomerRegistry().get(userRegistry.getOperatorRegistry().get(operatorToken).getMapKey());
        return customerSessionHolder == null ? null : customerSessionHolder.getVideoSession();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public WebSocketSession getCustomerControlSession(String operatorToken) {
        WebSocketSessionHolder customerSessionHolder =  userRegistry.getCustomerRegistry().get(userRegistry.getOperatorRegistry().get(operatorToken).getMapKey());
        return customerSessionHolder == null ? null : customerSessionHolder.getControlSession();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public WebSocketSession getOperatorVideoSession(String userId) {
        WebSocketSessionHolder operatorSessionHolder = userRegistry.getOperatorRegistry().get(userRegistry.getCustomerRegistry().get(userId).getMapKey());
        return operatorSessionHolder == null ? null : operatorSessionHolder.getVideoSession();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public WebSocketSession getOperatorControlSession(String userId) {
        WebSocketSessionHolder operatorSessionHolder = userRegistry.getOperatorRegistry().get(userRegistry.getCustomerRegistry().get(userId).getMapKey());
        return operatorSessionHolder == null ? null : operatorSessionHolder.getControlSession();
    }


}
