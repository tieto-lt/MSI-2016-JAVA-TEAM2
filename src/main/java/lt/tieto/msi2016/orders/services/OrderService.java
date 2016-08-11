package lt.tieto.msi2016.orders.services;

import lt.tieto.msi2016.orders.model.Order;

/**
 * Created by localadmin on 16.8.11.
 */
public interface OrderService {

    Order createOrder(final Order order);
}
