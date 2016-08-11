package lt.tieto.msi2016.orders.controllers;

import lt.tieto.msi2016.utils.controller.BaseController;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by localadmin on 16.8.11.
 */
@RestController
public class OrderController extends BaseController{

    /*
    @RequestMapping(value = "/api/users", method = RequestMethod.POST, consumes = accepts)
    public User createUser(@RequestBody final @Valid User user) {
        if(userService.checkUsername(user.getUserName()))
        {

            return userService.createUser(user);
        }
        else
        {
            throw new FieldValidationException("UserName","UserName already exists");
        }


    }
     */
}
