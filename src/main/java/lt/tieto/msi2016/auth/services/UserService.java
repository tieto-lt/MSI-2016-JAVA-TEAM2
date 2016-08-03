package lt.tieto.msi2016.auth.services;


import lt.tieto.msi2016.auth.model.User;

public interface UserService {

    /**
     * Creates user entry in database from {@param user}
     *
     * @param user
     */
   void createUser(User user);


}
