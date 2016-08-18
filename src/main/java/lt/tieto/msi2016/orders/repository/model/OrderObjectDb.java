package lt.tieto.msi2016.orders.repository.model;

import lt.tieto.msi2016.orders.model.OrderObject;
import lt.tieto.msi2016.utils.repository.model.DbModel;

/**
 * Created by localadmin on 16.8.18.
 */
public class OrderObjectDb extends DbModel {

    private Long Id;

    private Long orderId;

    private String object;

    private String how;

    public static OrderObjectDb valueOf(OrderObject orderObject) {
        OrderObjectDb orderObjectDb = new OrderObjectDb();
        orderObjectDb.setId(orderObject.getId());
        orderObjectDb.setOrderId(orderObject.getOrderId());
        orderObjectDb.setObject(orderObject.getObject());
        orderObjectDb.setHow(orderObject.getHow());
        return orderObjectDb;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getHow() {
        return how;
    }

    public void setHow(String how) {
        this.how = how;
    }
}
