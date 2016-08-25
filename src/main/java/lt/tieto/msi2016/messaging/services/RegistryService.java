package lt.tieto.msi2016.messaging.services;

import org.springframework.web.socket.WebSocketSession;

/**
 * Created by localadmin on 16.8.22.
 */
public interface RegistryService {

    /**
     * adds customer video session and reserves operator
     *
     * @param userId
     * @param session
     */
    void addCustomerVideoSession(String userId,WebSocketSession session);

    /**
     * adds customer control session and reserves operator
     *
     * @param userId
     * @param session
     */
    void addCustomerControlSession(String userId,WebSocketSession session);

    /**
     * adds operators video session
     *
     * @param operatorToken
     * @param session
     */
    void addOperatorVideoSession(String operatorToken,WebSocketSession session);

    /**
     * adds operators controll session
     *
     * @param operatorToken
     * @param session
     */
    void addOperatorControlSession(String operatorToken,WebSocketSession session);

    /**
     * Removes operator cancels user reservation
     *
     * @param operatorToken
     */
    void removeOperator(String operatorToken);

    /**
     * Removes customer cancels frees up operator
     *
     * @param userId
     */
    void removeCustomer(String userId);

    /**
     * Returns path variable from given session
     *
     * @param session
     * @return
     */
    String getPathVariable(WebSocketSession session);

    /**
     *
     * @param operatorToken
     * @return customers WebSocketSession
     */
    WebSocketSession getCustomerVideoSession(String operatorToken);

    /**
     *
     * @param operatorToken
     * @return customers WebSocketSession
     */
    WebSocketSession getCustomerControlSession(String operatorToken);

    /**
     *
     * @param userId
     * @return operators WebSocketSession
     */
    WebSocketSession getOperatorVideoSession(String userId);

    /**
     *
     * @param userId
     * @return operators WebSocketSession
     */
    WebSocketSession getOperatorControlSession(String userId);

    /**
     * Finds operator which is already reserved by user or free operator
     *
     * @param userId
     * @return operatorToken
     */
    String findOperatorForReservation(String userId);


}
