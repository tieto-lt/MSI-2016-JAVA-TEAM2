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
        missionCommands = new MissionCommands[]{
                MissionCommands.newMission().command("takeoff"),
            MissionCommands.newMission().command("altitude").withArguments(1.5),
                MissionCommands.newMission().command("forward").withArguments("2"),
                MissionCommands.newMission().command("takePicture"),
                MissionCommands.newMission().command("land")
        };

    }

    static MissionCommands[] missionCommands1;

    static {
        missionCommands1 = new MissionCommands[]{
                MissionCommands.newMission().command("takeoff"),
                MissionCommands.newMission().command("hover").withArguments(3000),
                MissionCommands.newMission().command("cw").withArguments(-38),
                MissionCommands.newMission().command("hover").withArguments(3000),
                MissionCommands.newMission().command("swichVertivalCamera"),
                MissionCommands.newMission().command("takePicture"),
                //-5 laipsniu paklaida
                MissionCommands.newMission().command("hover").withArguments(3000),
                MissionCommands.newMission().command("land")
        };

    }

    private void go (ArrayList<MissionCommands> missionCommands, double x1 ,double y1,double r1,double x0 ,double y0,double r0, int index, String camera ){
        double rotation = Math.toDegrees(Math.atan((x1 - x0) / (y1 - y0)));
        missionCommands.add(index, MissionCommands.newMission().command("hover").withArguments(1000)); index++;
        missionCommands.add(index, MissionCommands.newMission().command("cw").withArguments(rotation)); index++;
        /*double forward = Math.sqrt(Math.pow((x1 - x0), 2) + Math.pow((y1 - y0), 2));
        missionCommands.add(index, MissionCommands.newMission().command("forward").withArguments(forward)); index++;
        missionCommands.add(index, MissionCommands.newMission().command("cw").withArguments(r1-Math.abs(rotation))); index++;*/
        missionCommands.add(index, MissionCommands.newMission().command("switch"+camera+"Camera")); index++;
        missionCommands.add(index, MissionCommands.newMission().command("hover").withArguments(1000)); index++;
        missionCommands.add(index, MissionCommands.newMission().command("takePicture")); index++;
        x0=x1;y0=y1;r0=r1;

    }

    private ArrayList<MissionCommands> getMissionCommands (ArrayList<OrderObject> objects)
    {
        int index = 0;
        int errorMeters = 0;
        ArrayList<MissionCommands> missionCommands = new ArrayList<MissionCommands>();
        missionCommands.add(index, MissionCommands.newMission().command("takeoff")); index++;
        missionCommands.add(index, MissionCommands.newMission().command("altitude").withArguments(1.5)); index++;

        double x0=0, y0=0, r0=0;

        for (OrderObject orderObject:objects) {
            if (orderObject.getObjectName()!=null) {
                double x1 = 0, y1 = 0, r1 = 0;
                if (orderObject.getObjectName().equals("Table1") ) {
                    if(orderObject.getHow().equals("above"))
                        go(missionCommands, -8.0, 12.0, 0, x0, y0, r0, index, "Vertical");
                    else
                        go(missionCommands, -10.0, 12.0, -90, x0, y0, r0, index, "Horizontal");
                }
                else if (orderObject.getObjectName().equals("Table2") ) {
                    if(orderObject.getHow().equals("above"))
                        go(missionCommands, -8.0, 4.0, 0, x0, y0, r0, index, "Vertical");
                    else
                        go(missionCommands, -10.0, 6.0, -90, x0, y0, r0, index, "Horizontal");
                }
                else if (orderObject.getObjectName().equals("Table3") ) {
                    if(orderObject.getHow().equals("above"))
                        go(missionCommands, 4.0, 8.0, 0, x0, y0, r0, index, "Vertical");
                    else
                        go(missionCommands, 2.0, 8.0, 90, x0, y0, r0, index, "Horizontal");
                }
                else if (orderObject.getObjectName().equals("Table2") ) {
                    if(orderObject.getHow().equals("above"))
                        go(missionCommands, -4.0, -4.0, 0, x0, y0, r0, index, "Vertical");
                    else
                        go(missionCommands, -4.0, -2.0, 180, x0, y0, r0, index, "Horizontal");
                }
            }
        }
        go(missionCommands, 0, 0, 0, x0, y0, r0, index, "Vertical");
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
        if(orderObjects.get(0).getObjectName()!=null){

             json = ow.writeValueAsString(missionCommands1);
        }
        else{
             json = ow.writeValueAsString(missionCommands1);
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
