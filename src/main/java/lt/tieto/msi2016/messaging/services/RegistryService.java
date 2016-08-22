package lt.tieto.msi2016.messaging.services;

import org.springframework.web.socket.WebSocketSession;

/**
 * Created by localadmin on 16.8.22.
 */
public interface RegistryService {

    /**
     * Adds operator session to operator registry
     *
     * @param operatorToken
     * @param session
     */
    void addOperator(String operatorToken, WebSocketSession session);

    /**
     * Adds customer session to customer registry
     *
     * @param userId
     * @param session
     */
    void addCustomer(String userId, WebSocketSession session);

    /**
     * Adds customer {@param userId} as key and operators  {@param session} as value to reservation registry
     *
     * @param userId
     * @param session
     */
    void reserveOperator(String userId, WebSocketSession session);

    /**
     * Removes operator from operator registry
     *
     * @param session
     */
    void removeOperator(WebSocketSession session);

    /**
     * Removes customer from customer registry
     *
     * @param userId
     */
    void removeCustomer(String userId);

    /**
     * Removes customer {@param userId} key {@param operatorToken} value pair from reservation registry
     *
     * @param userId
     */
    void cancelReservation(String userId);

    /**
     *
     * @param operatorToken
     * @return
     */
    WebSocketSession getOperatorSession(String operatorToken);

    /**
     *
     * @param userId
     * @return
     */
    WebSocketSession getCustomerSession(String userId);

    /**
     *
     * @param userId
     * @return
     */
    WebSocketSession getOperatorSessionFromReservation(String userId);


}
