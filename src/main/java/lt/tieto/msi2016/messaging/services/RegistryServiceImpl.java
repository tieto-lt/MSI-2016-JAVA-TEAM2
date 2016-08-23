package lt.tieto.msi2016.messaging.services;

import lt.tieto.msi2016.messaging.UserRegistry;
import lt.tieto.msi2016.messaging.exceptions.NoFreeOperatorsException;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import javax.xml.ws.WebServiceException;
import java.nio.channels.NotYetConnectedException;
import java.util.HashMap;
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
    public void removeOperator(String operatorToken) {
        userRegistry.getOperatorRegistry().remove(operatorToken);
        removeOperatorFromReservationRegistry(operatorToken);
    }

    @Override
    public void removeCustomer(String userId) {
        userRegistry.getCustomerRegistry().remove(userId);
        cancelReservation(userId);
    }

    @Override
    public void cancelReservation(String userId) {
        WebSocketSession operatorsSession = userRegistry.getReservationRegistry().get("userId");
        userRegistry.getOperatorRegistry().put(getPathVariable(operatorsSession),operatorsSession);
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
        Optional<String> userId = userRegistry.getReservationRegistry().entrySet().stream().filter(entry -> userRegistry.getReservationRegistry().get(entry.getKey()).equals(session)).map(Map.Entry::getKey).findFirst();
        return userRegistry.getCustomerRegistry().get(userId.equals(Optional.empty()) ? userId : userId.get());
    }

    @Override
    public String getPathVariable(WebSocketSession session) {
        return session.getUri().getPath().substring(session.getUri().getPath().lastIndexOf("/")).substring(1);
    }

    @Override
    public WebSocketSession getFreeOperatorsSession() {
        return userRegistry.getOperatorRegistry().values().stream().findFirst().get();
    }

    private void removeOperatorFromReservationRegistry(String operatorToken){
        userRegistry.getReservationRegistry().entrySet().removeIf(key -> userRegistry.getOperatorRegistry().get(key).equals(userRegistry.getOperatorRegistry().get(operatorToken)));
    }



}
