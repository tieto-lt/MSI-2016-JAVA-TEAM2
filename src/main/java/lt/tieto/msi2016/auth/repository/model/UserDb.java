package lt.tieto.msi2016.auth.repository.model;

import lt.tieto.msi2016.auth.model.User;
import lt.tieto.msi2016.utils.repository.model.DbModel;

public class UserDb extends DbModel{

    private String userName;

    private String password;

    private String name;

    private String email;

    private String phone;

    public static UserDb valueOf(User user){
        UserDb userDb = new UserDb();
        userDb.setId(user.getId());
        userDb.setUserName(user.getUserName());
        userDb.setPassword(user.getPassword());
        userDb.setName(user.getName());
        userDb.setEmail(user.getEmail());
        userDb.setPhone(user.getPhone());
        return userDb;
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


}
