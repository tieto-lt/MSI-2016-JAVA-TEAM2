package lt.tieto.msi2016.auth.model;


public class User {
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CreateUser {

    @NotNull
    private String userName;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @NotNull
    @Pattern(regexp="\\*@\\*")
    private String email;

    @NotNull
    private String phone;

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
}
