package lt.tieto.msi2016.orders.services;

import lt.tieto.msi2016.missions.model.mission.MissionCommands;
import lt.tieto.msi2016.missions.repository.MissionRepository;
import lt.tieto.msi2016.missions.repository.model.MissionDb;
import lt.tieto.msi2016.missions.services.MissionService;
import lt.tieto.msi2016.orders.model.Order;
import lt.tieto.msi2016.orders.model.OrderObject;
import lt.tieto.msi2016.orders.repository.OrderObjectRepository;
import lt.tieto.msi2016.orders.repository.OrderRepository;
import lt.tieto.msi2016.orders.repository.model.OrderDb;
import lt.tieto.msi2016.orders.repository.model.OrderObjectDb;
import lt.tieto.msi2016.utils.services.SecurityHolder;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by localadmin on 16.8.11.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderRepository orderRepository;
    @Resource
    OrderObjectRepository orderObjectRepository;

    @Autowired
    SecurityHolder securityHolder;

    @Autowired
    MissionService missionService;
    @Autowired
    MissionRepository missionRepository;

    private static MissionCommands[] missionCommands;

    static {
        missionCommands = new MissionCommands[]{MissionCommands.newMission().command("altitude").withArguments(1.5),
                MissionCommands.newMission().command("forward").withArguments("2"),
                MissionCommands.newMission().command("takePicture"),
                MissionCommands.newMission().command("land")
        };

    }

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
        String json = ow.writeValueAsString(missionCommands);
        missionDb.setMissionJSON(json);
        missionRepository.create(missionDb);
        return newOrder;
    }

    @Transactional
    public void createOrderObjects(ArrayList<OrderObject> orderObjects, Long orderId) {
        for (OrderObject orderObject: orderObjects)
        {
            OrderObjectDb orderObjectDb = OrderObjectDb.valueOf(orderObject);
            orderObjectDb.setOrderId(orderId);
            orderObjectRepository.create(orderObjectDb);
        }
    }


    @Transactional(readOnly = true)
    public Collection<Order> all() {
        return orderRepository.findAll().stream().map(this::fillOrder).collect(Collectors.toList());
    }

    private Order fillOrder(OrderDb orderDb) {
        Order order = Order.valueOf(orderDb);
        return order;
    }


    @Transactional
    public Order updateOrder(Order order, Long id) {
        orderRepository.save(OrderDb.valueOf(order));
        return order;
    }

    @Transactional(readOnly = true)
    public Collection<Order> getCompletedOrdersByUserName(String username) {
        return orderRepository.getCompletedOrdersWithMissionIdByUsername(username).stream().map(this::fillOrder).collect(Collectors.toList());
    }

    public Collection<Order> getOrderByUserName(String username){
        return orderRepository.getOrdersByUserName(username).stream().map(this::fillOrder).collect(Collectors.toList());
    }


}
