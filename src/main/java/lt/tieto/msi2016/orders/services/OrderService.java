package lt.tieto.msi2016.orders.services;

import lt.tieto.msi2016.orders.model.Order;
import lt.tieto.msi2016.orders.model.OrderObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by localadmin on 16.8.11.
 */
public interface OrderService {

   Order createOrder(final Order order) throws IOException;

   Collection<Order> all();

   Order updateOrder(Order order,Long id);

   Collection<Order> getCompletedOrdersByUserName(String username);

   Collection<Order> getOrderByUserName(String username);

   void createOrderObjects(ArrayList<OrderObject> orderObjects, Long orderId);
}
