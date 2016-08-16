package lt.tieto.msi2016.orders.services;

import lt.tieto.msi2016.orders.model.Order;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by localadmin on 16.8.11.
 */
public interface OrderService {

   Order createOrder(final Order order) throws IOException;

   Collection<Order> all();

   Order updateOrder(Order order,Long id);
}
