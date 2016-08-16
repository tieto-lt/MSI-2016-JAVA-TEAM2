package lt.tieto.msi2016.orders.controllers;

import lt.tieto.msi2016.orders.model.Order;
import lt.tieto.msi2016.orders.services.OrderService;
import lt.tieto.msi2016.utils.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.io.IOException;
import java.util.Collection;

import static lt.tieto.msi2016.utils.constants.Roles.ADMIN;

/**
 * Created by localadmin on 16.8.11.
 */
@RestController
public class OrderController extends BaseController {

    private final String accepts = "application/json";

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/api/orders", method = RequestMethod.POST, consumes = accepts)
    public Order createOrder(@RequestBody final @Valid Order order) throws IOException {

        return orderService.createOrder(order);

    }

    @Secured(ADMIN)
    @RequestMapping(value = "/api/orders", method = RequestMethod.GET)
    public Collection<Order> getOrders(){
        return orderService.all();
    }


}
