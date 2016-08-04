package lt.tieto.msi2016.auth.services;


import lt.tieto.msi2016.auth.model.User;

import java.util.Collection;

public interface UserService {

    /**
     * Creates user entry in database from {@param user}
     *
     * @param user
     */
   User createUser(User user);

    /**
     * Returns user info
     *
     * @param id
     * @return
     */
    User getUserInfo(Long id);

    /**
     *
     *
     * @return all user objects
     */
    Collection<User> all();

    /**
     *
     *
     * @param userName
     * @return User object by user name
     */
    User getUserByUserName(String userName);

    boolean checkUsername (String userName);



    /**
     * Updates user details
     *
     * @param user
     * @return updated user object
     */
    User updateUserInfo(User user);
}
