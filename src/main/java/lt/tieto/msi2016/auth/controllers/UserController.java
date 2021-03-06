package lt.tieto.msi2016.auth.controllers;


import lt.tieto.msi2016.auth.model.User;
import lt.tieto.msi2016.auth.model.user.UpdatePassword;
import lt.tieto.msi2016.auth.services.UserService;
import lt.tieto.msi2016.utils.controller.BaseController;
import lt.tieto.msi2016.utils.exception.FieldValidationException;
import lt.tieto.msi2016.utils.services.SecurityHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

import static lt.tieto.msi2016.utils.constants.Roles.ADMIN;

@RestController
public class UserController extends BaseController {

    private final String accepts = "application/json";

    @Autowired
    private UserService userService;
    @Autowired
    private SecurityHolder securityHolder;

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

    @RequestMapping(value = "/api/users/update", method = RequestMethod.PUT, consumes = accepts)
    public void updateUser(@RequestBody final @Valid User user) {
            userService.updateUserInfo(user);
    }

    @RequestMapping(value = "/api/users/updatePassword", method = RequestMethod.PUT, consumes = accepts)
    public void updatePassword(@RequestBody final @Valid UpdatePassword updatePassword) {
        if(updatePassword.getCurrentPassword().equals(updatePassword.getNewPassword()))
        {
            throw new FieldValidationException("New Password","New password is the same as current");
        }
        else {
            if (userService.checkPassword(updatePassword.getCurrentPassword(), securityHolder.getUserPrincipal().getUsername())) {

                userService.updatePassword(updatePassword.getNewPassword(), securityHolder.getUserPrincipal().getUsername());
            } else {
                throw new FieldValidationException("Current Password", "Wrong password");
            }
        }
    }

    @Secured(ADMIN)
    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    public Collection<User> getUsers() {
            return userService.all();

    }

    @RequestMapping(value = "/api/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        if (canAccessInfo(id)) {
            return ResponseEntity.ok(userService.getUserInfo(id));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @Secured(ADMIN)
    @RequestMapping(value = "/api/users/{id}", method = RequestMethod.PUT,consumes = accepts)
    public User updateUserRole(@RequestBody User user, @PathVariable Long id) {
        return userService.updateUser(user,id);
    }

    private boolean canAccessInfo(Long id) {
        return securityHolder.getUserPrincipal().getUsername().equals(userService.getUserInfo(id).getUserName()) || isUserAdmin();
    }

    private boolean isUserAdmin ()
    {
        return !securityHolder.getUserPrincipal().getAuthorities().stream().filter(grantedAuthority -> grantedAuthority.getAuthority().equals(ADMIN)).collect(Collectors.toList()).isEmpty();
    }

    @RequestMapping(value = "/api/users/logout", method = RequestMethod.POST)
    public void logout() {
        securityHolder.logout();
    }
}
