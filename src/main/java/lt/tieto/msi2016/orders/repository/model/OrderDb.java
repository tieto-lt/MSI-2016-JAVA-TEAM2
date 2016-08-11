package lt.tieto.msi2016.orders.repository.model;

import lt.tieto.msi2016.orders.model.Order;
import lt.tieto.msi2016.utils.repository.model.DbModel;

/**
 * Created by localadmin on 16.8.11.
 */
public class OrderDb extends DbModel {

    private Long userId;

    private Boolean isApproved;

    private String name;

    private String details;

    private String email;

    private String phone;

    public static OrderDb valueOf(Order order) {
        OrderDb orderDb = new OrderDb();
        orderDb.setUserId(order.getUserId());
        orderDb.setApproved(order.getApproved());
        orderDb.setName(order.getName());
        orderDb.setDetails(order.getDetails());
        orderDb.setEmail(order.getEmail());
        orderDb.setPhone(order.getPhone());
        return orderDb;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
