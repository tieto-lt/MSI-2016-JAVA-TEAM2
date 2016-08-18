package lt.tieto.msi2016.orders.model;

/**
 * Created by localadmin on 16.8.18.
 */
public class OrderObject {

    private Long id;

    private Long orderId;

    private String object;

    private String how;

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
