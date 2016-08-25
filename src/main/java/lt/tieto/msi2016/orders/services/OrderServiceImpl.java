package lt.tieto.msi2016.orders.services;

import lt.tieto.msi2016.missions.model.mission.MissionCommands;
import lt.tieto.msi2016.missions.repository.MissionRepository;
import lt.tieto.msi2016.missions.repository.model.MissionDb;
import lt.tieto.msi2016.missions.services.MissionService;
import lt.tieto.msi2016.orders.model.Loc;
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
import java.math.BigDecimal;
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
        missionCommands = new MissionCommands[]{
                MissionCommands.newMission().command("takeoff"),
                MissionCommands.newMission().command("takePicture"),
                MissionCommands.newMission().command("land")
        };

    }

    static MissionCommands[] missionCommands1;
    static {
        missionCommands1 = new MissionCommands[]{
                MissionCommands.newMission().command("takeoff"),
                MissionCommands.newMission().command("zero"),

                MissionCommands.newMission().command("switchVerticalCamera"),
                MissionCommands.newMission().command("go").withArguments(new Loc(BigDecimal.valueOf(-0.8), BigDecimal.valueOf(-1), BigDecimal.valueOf(1.5), BigDecimal.valueOf(0))),
                MissionCommands.newMission().command("hover").withArguments(1000),
                MissionCommands.newMission().command("takePicture"),
                MissionCommands.newMission().command("go").withArguments(new Loc(BigDecimal.valueOf(1.2), BigDecimal.valueOf(-2), BigDecimal.valueOf(1.5), BigDecimal.valueOf(0))),
                MissionCommands.newMission().command("hover").withArguments(1000),
                MissionCommands.newMission().command("takePicture"),
                MissionCommands.newMission().command("go").withArguments(new Loc(BigDecimal.valueOf(3.2), BigDecimal.valueOf(-2.2), BigDecimal.valueOf(1.5), BigDecimal.valueOf(0))),
                MissionCommands.newMission().command("hover").withArguments(1000),
                MissionCommands.newMission().command("takePicture"),
                MissionCommands.newMission().command("go").withArguments(new Loc(BigDecimal.valueOf(1.5), BigDecimal.valueOf(1.4), BigDecimal.valueOf(1.5), BigDecimal.valueOf(0))),
                MissionCommands.newMission().command("hover").withArguments(1000),
                MissionCommands.newMission().command("takePicture"),
                MissionCommands.newMission().command("go").withArguments(new Loc(BigDecimal.valueOf(-0.5), BigDecimal.valueOf(-0.5), BigDecimal.valueOf(1.5), BigDecimal.valueOf(0))),

                MissionCommands.newMission().command("switchHorizontalCamera"),
                MissionCommands.newMission().command("cw").withArguments(40),
                MissionCommands.newMission().command("takePicture"),
                MissionCommands.newMission().command("ccw").withArguments(70),
                MissionCommands.newMission().command("takePicture"),
                MissionCommands.newMission().command("ccw").withArguments(60),
                MissionCommands.newMission().command("takePicture"),
                MissionCommands.newMission().command("ccw").withArguments(70),
                MissionCommands.newMission().command("takePicture"),
                MissionCommands.newMission().command("cw").withArguments(180),

                MissionCommands.newMission().command("land"),
        };

    }



    private ArrayList<MissionCommands> getMissionCommands (ArrayList<OrderObject> objects)
    {
        int index = 0;
        ArrayList<MissionCommands> missionCommands = new ArrayList<MissionCommands>();
        missionCommands.add(index, MissionCommands.newMission().command("takeoff")); index++;
        if(objects.get(0).getHow().equals("above")||objects.get(1).getHow().equals("above")||objects.get(2).getHow().equals("above")||objects.get(3).getHow().equals("above")){
            missionCommands.add(index, MissionCommands.newMission().command("switchVerticalCamera")); index++;

            if(objects.get(3).getHow().equals("above")){
                missionCommands.add(index,MissionCommands.newMission().command("go").withArguments(new Loc(BigDecimal.valueOf(-0.8), BigDecimal.valueOf(-1), BigDecimal.valueOf(1.5), BigDecimal.valueOf(0)))); index++;
                missionCommands.add(index, MissionCommands.newMission().command("hover").withArguments(1000)); index++;
                missionCommands.add(index, MissionCommands.newMission().command("takePicture")); index++;
            }
            if(objects.get(1).getHow().equals("above")){
                missionCommands.add(index,MissionCommands.newMission().command("go").withArguments(new Loc(BigDecimal.valueOf(1.2), BigDecimal.valueOf(-2), BigDecimal.valueOf(1.5), BigDecimal.valueOf(0)))); index++;
                missionCommands.add(index, MissionCommands.newMission().command("hover").withArguments(1000)); index++;
                missionCommands.add(index, MissionCommands.newMission().command("takePicture")); index++;
            }
            if(objects.get(0).getHow().equals("above")){
                missionCommands.add(index,MissionCommands.newMission().command("go").withArguments(new Loc(BigDecimal.valueOf(3.2), BigDecimal.valueOf(-2.2), BigDecimal.valueOf(1.5), BigDecimal.valueOf(0)))); index++;
                missionCommands.add(index, MissionCommands.newMission().command("hover").withArguments(1000)); index++;
                missionCommands.add(index, MissionCommands.newMission().command("takePicture")); index++;
            }
            if(objects.get(2).getHow().equals("above")){
                missionCommands.add(index,MissionCommands.newMission().command("go").withArguments(new Loc(BigDecimal.valueOf(1.5), BigDecimal.valueOf(1.4), BigDecimal.valueOf(1.5), BigDecimal.valueOf(0)))); index++;
                missionCommands.add(index, MissionCommands.newMission().command("hover").withArguments(1000)); index++;
                missionCommands.add(index, MissionCommands.newMission().command("takePicture")); index++;
            }
            missionCommands.add(index,MissionCommands.newMission().command("go").withArguments(new Loc(BigDecimal.valueOf(-0.5), BigDecimal.valueOf(-0.5), BigDecimal.valueOf(1.5), BigDecimal.valueOf(0)))); index++;

        }
        else if(objects.get(0).getHow().equals("front")||objects.get(1).getHow().equals("front")||objects.get(2).getHow().equals("front")||objects.get(3).getHow().equals("front")){
            missionCommands.add(index, MissionCommands.newMission().command("switchHorizontalCamera")); index++;
            missionCommands.add(index,MissionCommands.newMission().command("go").withArguments(new Loc(BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(1.5), BigDecimal.valueOf(0)))); index++;
            missionCommands.add(index, MissionCommands.newMission().command("cw").withArguments(40));index++;
            if(objects.get(2).getHow().equals("front")){
                missionCommands.add(index, MissionCommands.newMission().command("takePicture")); index++;
            }
            missionCommands.add(index, MissionCommands.newMission().command("ccw").withArguments(70));

            if(objects.get(0).getHow().equals("front")){
                missionCommands.add(index, MissionCommands.newMission().command("takePicture")); index++;
            }
            missionCommands.add(index, MissionCommands.newMission().command("ccw").withArguments(60));

            if(objects.get(1).getHow().equals("front")){
                missionCommands.add(index, MissionCommands.newMission().command("takePicture")); index++;

            }
            missionCommands.add(index, MissionCommands.newMission().command("ccw").withArguments(70));
            if(objects.get(3).getHow().equals("front")){
                missionCommands.add(index, MissionCommands.newMission().command("takePicture")); index++;
            }
            missionCommands.add(index, MissionCommands.newMission().command("cw").withArguments(180));index++;
            
        }
        missionCommands.add(index, MissionCommands.newMission().command("land")); index++;
        return missionCommands;
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
        String json;
        ArrayList<OrderObject> orderObjects = order.getOrderObjects();
        if(orderObjects.get(0).getObjectName()!=null||orderObjects.get(1).getObjectName()!=null||orderObjects.get(2).getObjectName()!=null||orderObjects.get(3).getObjectName()!=null){

            json = ow.writeValueAsString(getMissionCommands(orderObjects));
        }
        else{
            json = ow.writeValueAsString(missionCommands);
        }

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
        return orderRepository.findAll().stream().map(Order::valueOf).collect(Collectors.toList());
    }



    @Transactional
    public Order updateOrder(Order order, Long id) {

        orderRepository.save(OrderDb.valueOf(order));
        return order;
    }

    @Transactional(readOnly = true)
    public Collection<Order> getCompletedOrdersByUserName(String username) {
        return orderRepository.getCompletedOrdersWithMissionIdByUsername(username).stream().map(Order::valueOf).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public Collection<Order> getOrderByUserName(String username){
        return orderRepository.getOrdersByUserName(username).stream().map(this::fillOrder).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public Collection<OrderObject> getOrderObjects(Long orderId){
        return orderObjectRepository.getOrderObjectsByOrderId(orderId).stream().map(OrderObject::valueOf).collect(Collectors.toList());
    }
    @Transactional
    private Order fillOrder(OrderDb orderDb){
        Order order = Order.valueOf(orderDb);
        ArrayList<OrderObject> orderObjects = new ArrayList<OrderObject>(getOrderObjects(order.getId()));
        order.setOrderObjects(orderObjects);
        return order;
    }


}
