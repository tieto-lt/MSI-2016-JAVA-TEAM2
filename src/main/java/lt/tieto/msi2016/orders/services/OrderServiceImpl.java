package lt.tieto.msi2016.orders.services;

import lt.tieto.msi2016.orders.model.Order;
import lt.tieto.msi2016.orders.repository.OrderRepository;
import lt.tieto.msi2016.orders.repository.model.OrderDb;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by localadmin on 16.8.11.
 */
@Service
public class OrderServiceImpl implements  OrderService{

    @Resource
    OrderRepository orderRepository;
    /**
     *{@inheritDoc}
     */
    @Transactional
    public Order createOrder(final Order order)
    {
       OrderDb orderDb = OrderDb.valueOf(order);
        orderDb.setApproved(false);
        Order newOrder = Order.valueOf(orderRepository.create(orderDb));
        return newOrder;

    }

}
