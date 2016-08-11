package lt.tieto.msi2016.auth.model;

import lt.tieto.msi2016.auth.repository.model.UserDb;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {

    private Long id;

    @NotNull
    @Size(min=6,max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9_]+$")
    private String userName;

    @NotNull
    @Size(min=6,max = 30)
    @Pattern(regexp = "^[a-zA-Z0-9_]+$")
    private String password;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Pattern(regexp = "^[A-z0-9!#$%&'*+\\/=?^_`{|}~.-]+@[A-z0-9]([A-z0-9-]*[A-z0-9])?(\\.[A-z0-9]([A-z0-9-]*[A-z0-9])?)*$")
    private String email;

    @NotNull
    @Pattern(regexp = "^[\\+ | 0-9][0-9]*")
    private String phone;

    private String userRole;

    public static User valueOf(UserDb userDb){
        User user = new User();
        user.setId(userDb.getId());
        user.setUserName(userDb.getUserName());
        user.setName(userDb.getName());
        user.setEmail(userDb.getEmail());
        user.setPhone(userDb.getPhone());
        return user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
