package lt.tieto.msi2016.messaging.services;

import lt.tieto.msi2016.messaging.UserRegistry;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by localadmin on 16.8.22.
 */
@Service
public class RegistryServiceImpl implements RegistryService {

    private UserRegistry userRegistry;

    @PostConstruct
    private void initRegistryService(){
        userRegistry = new UserRegistry() {

            final Map<String,WebSocketSession> operatorRegistry = new ConcurrentHashMap<>();

            final Map<String,WebSocketSession> customerRegistry = new ConcurrentHashMap<>();

            final Map<String,WebSocketSession> reservationRegistry = new ConcurrentHashMap<>();

            @Override
            public Map<String, WebSocketSession> getOperatorRegistry() {
                return operatorRegistry;
            }

            @Override
            public Map<String, WebSocketSession> getCustomerRegistry() {
                return customerRegistry;
            }

            @Override
            public Map<String, WebSocketSession> getReservationRegistry() {
                return reservationRegistry;
            }
        };
    }

    @Override
    public void addOperator(String operatorToken, WebSocketSession session){
        userRegistry.getOperatorRegistry().put(operatorToken,session);

    }

    @Override
    public void addCustomer(String customerId, WebSocketSession session) {
       userRegistry.getCustomerRegistry().put(customerId,session);
    }

    @Override
    public void reserveOperator(String userId, WebSocketSession session) {
        userRegistry.getReservationRegistry().put(userId,session);
    }

    @Override
    public void removeOperator(WebSocketSession session) {
        userRegistry.getOperatorRegistry().remove(session);
        removeOperatorFromReservationRegistry(session);
    }

    @Override
    public void removeCustomer(String userId) {
        userRegistry.getCustomerRegistry().remove(userId);
        cancelReservation(userId);
    }

    @Override
    public void cancelReservation(String userId) {
        userRegistry.getReservationRegistry().remove(userId);
    }

    @Override
    public WebSocketSession getOperatorSession(String operatorToken) {
        return userRegistry.getOperatorRegistry().get(operatorToken);
    }

    @Override
    public WebSocketSession getCustomerSession(String userId) {
        return userRegistry.getCustomerRegistry().get(userId);
    }

    @Override
    public WebSocketSession getOperatorSessionFromReservation(String userId) {
        return userRegistry.getReservationRegistry().get(userId);
    }

    @Override
    public WebSocketSession getCustomerSessionByOperatorSession(WebSocketSession session) {
        Optional<String> userId = userRegistry.getReservationRegistry().entrySet().stream().filter(key -> userRegistry.getReservationRegistry().get(key).equals(session)).map(Map.Entry::getKey).findFirst();
        return userRegistry.getCustomerRegistry().get(userId);
    }

    private void removeOperatorFromReservationRegistry(WebSocketSession session){
        userRegistry.getReservationRegistry().values().removeIf(operatorSession -> operatorSession.equals(session));
    }

}
