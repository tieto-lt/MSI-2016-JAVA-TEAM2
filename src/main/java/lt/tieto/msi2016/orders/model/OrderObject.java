package lt.tieto.msi2016.orders.model;


import lt.tieto.msi2016.orders.repository.model.OrderObjectDb;

public class OrderObject {

    private Long id;

    private Long orderId;

    private String objectName;

    private String how;

    public static OrderObject valueOf (OrderObjectDb orderObjectDb)
    {
        OrderObject orderObject = new OrderObject();
        orderObject.setId(orderObjectDb.getId());
        orderObject.setOrderId(orderObjectDb.getOrderId());
        orderObject.setObjectName(orderObjectDb.getObjectName());
        orderObject.setHow(orderObjectDb.getHow());
        return orderObject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
