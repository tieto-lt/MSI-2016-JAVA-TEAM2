package lt.tieto.msi2016.auth.controllers;

import lt.tieto.msi2016.auth.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by it9 on 16.8.4.
 */
@RestController
public class UserController {
    static final List<User> USERS_LIST = Arrays.asList(
            new User(1l,"Petriukas","Petras","petras@one.lt","+3705463544"),
            new User(1l,"Petriukas1","Petras1","petras1@one.lt","+3705463541"),
            new User(1l,"Petriukas2","Petras2","petras2@one.lt","+3705463542")
    );

  /*  @RequestMapping(method = RequestMethod.GET, path = "/api/items/{id}")
    public User get(@PathVariable Long id) {
        return USERS_LIST.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElse(null);
    }*/

    @RequestMapping(method = RequestMethod.GET, path = "/userList")
    public List<User> users() {
        return USERS_LIST;
    }
}
