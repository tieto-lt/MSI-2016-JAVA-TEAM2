package lt.tieto.msi2016.auth.controllers;


import lt.tieto.msi2016.auth.model.User;
import lt.tieto.msi2016.auth.services.UserService;

import static lt.tieto.msi2016.utils.constants.Roles.*;

import lt.tieto.msi2016.utils.controller.BaseController;
import lt.tieto.msi2016.utils.services.SecurityHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserController extends BaseController {

    private final String accepts = "application/json";

    @Autowired
    private UserService userService;
    @Autowired
    private SecurityHolder securityHolder;

    @RequestMapping(value = "/api/users", method = RequestMethod.POST, consumes = accepts)
    public User createUser(@RequestBody final User user) {
        return userService.createUser(user);
    }

    @RequestMapping(value = "/api/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        if (canAccessInfo(id)) {
            return ResponseEntity.ok(userService.getUserInfo(id));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    private boolean canAccessInfo(Long id) {
        return securityHolder.getUserPrincipal().getUsername() == userService.getUserInfo(id).getUserName() || securityHolder.getUserPrincipal().getAuthorities().stream().filter(grantedAuthority -> grantedAuthority.getAuthority().equals(ADMIN)) != null;
    }

}
