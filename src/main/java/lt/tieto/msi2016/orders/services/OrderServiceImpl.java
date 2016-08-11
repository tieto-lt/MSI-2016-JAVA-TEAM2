package lt.tieto.msi2016.orders.services;

import lt.tieto.msi2016.orders.model.Order;
import lt.tieto.msi2016.orders.repository.OrderRepository;
import lt.tieto.msi2016.orders.repository.model.OrderDb;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.stream.Collectors;

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
        orderDb.setDate(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date(System.currentTimeMillis())));
        Order newOrder = Order.valueOf(orderRepository.create(orderDb));
        return newOrder;
    }
    @Transactional(readOnly = true)
    public Collection<Order> all(){
        return orderRepository.findAll().stream().map(this::fillOrder).collect(Collectors.toList());
    }

    @Transactional
    private Order fillOrder(OrderDb orderDb){
        Order order = Order.valueOf(orderDb);
        return order;
    }


}
