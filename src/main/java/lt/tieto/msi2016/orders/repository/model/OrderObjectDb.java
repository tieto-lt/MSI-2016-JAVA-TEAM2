package lt.tieto.msi2016.orders.repository.model;

import lt.tieto.msi2016.orders.model.OrderObject;
import lt.tieto.msi2016.utils.repository.model.DbModel;

/**
 * Created by localadmin on 16.8.18.
 */
public class OrderObjectDb extends DbModel {

    private Long Id;

    private Long orderId;

    private String objectName;

    private String how;

    public static OrderObjectDb valueOf(OrderObject orderObject) {
        OrderObjectDb orderObjectDb = new OrderObjectDb();
        orderObjectDb.setId(orderObject.getId());
        orderObjectDb.setOrderId(orderObject.getOrderId());
        orderObjectDb.setObjectName(orderObject.getObjectName());
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


    public String getHow() {
        return how;
    }

    public void setHow(String how) {
        this.how = how;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}
