package lt.tieto.msi2016.orders.model;

import lt.tieto.msi2016.orders.repository.model.OrderDb;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by localadmin on 16.8.11.
 */
public class Order {

    private Long id;

    @NotNull
    private Long userId;


    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Size(max = 500)
    private String details;

    @NotNull
    @Pattern(regexp = "^[A-z0-9!#$%&'*+\\/=?^_`{|}~.-]+@[A-z0-9]([A-z0-9-]*[A-z0-9])?(\\.[A-z0-9]([A-z0-9-]*[A-z0-9])?)*$")
    private String email;

    @NotNull
    @Pattern(regexp = "^[\\+ | 0-9][0-9]*")
    private String phone;

    private String date;

    private String status;

    public static Order valueOf (OrderDb orderDb)
    {
        Order order = new Order();
        order.setId(orderDb.getId());
        order.setUserId(orderDb.getUserId());
        order.setDetails(orderDb.getDetails());
        order.setName(orderDb.getName());
        order.setEmail(orderDb.getEmail());
        order.setPhone(orderDb.getPhone());
        order.setDate(orderDb.getDate());
        order.setStatus(orderDb.getStatus());
        return order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
