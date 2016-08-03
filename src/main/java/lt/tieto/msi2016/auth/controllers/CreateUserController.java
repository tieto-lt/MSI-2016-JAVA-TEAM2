package lt.tieto.msi2016.auth.controllers;


import lt.tieto.msi2016.auth.model.User;
import lt.tieto.msi2016.auth.services.UserService;
import lt.tieto.msi2016.utils.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CreateUserController extends BaseController {

    private final String accepts = "application/json";

    @Resource
    private UserService userServiceImpl;

    @RequestMapping(path = "/users", method = RequestMethod.POST, consumes = accepts)
    public void createUser(final User user) {
        userServiceImpl.createUser(user);
    }
}
