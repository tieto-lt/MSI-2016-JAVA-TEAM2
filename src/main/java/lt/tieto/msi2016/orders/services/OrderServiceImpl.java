package lt.tieto.msi2016.orders.services;

import lt.tieto.msi2016.missions.repository.MissionRepository;
import lt.tieto.msi2016.missions.repository.model.MissionDb;
import lt.tieto.msi2016.missions.services.MissionService;
import lt.tieto.msi2016.operator.repository.OperatorRepository;
import lt.tieto.msi2016.orders.model.Order;
import lt.tieto.msi2016.orders.repository.OrderRepository;
import lt.tieto.msi2016.orders.repository.model.OrderDb;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by localadmin on 16.8.11.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderRepository orderRepository;

    @Autowired
    MissionService missionService;
    @Autowired
    MissionRepository missionRepository;

    /**
     * {@inheritDoc}
     */
    @Transactional
    public Order createOrder(final Order order) throws IOException {
        OrderDb orderDb = OrderDb.valueOf(order);
        orderDb.setDate(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date(System.currentTimeMillis())));
        orderDb.setStatus("not completed");
        Order newOrder = Order.valueOf(orderRepository.create(orderDb));

        MissionDb missionDb = new MissionDb();
        missionDb.setOrderId(newOrder.getId());
        ObjectWriter ow = new ObjectMapper().writer();
        String json = ow.writeValueAsString(missionService.getDefaultMission());
        missionDb.setMissionJSON(json);
        missionRepository.create(missionDb);
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


    public Order updateOrder(Order order,Long id) {
        orderRepository.updateOrderStatus(id, order.getStatus());
        return order;
    }

    public Collection<Order> getCompletedOrdersByUserName(String username){
        return orderRepository.getCompletedOrdersByUsername(username).stream().map(this::fillOrder).collect(Collectors.toList());
    }


}
