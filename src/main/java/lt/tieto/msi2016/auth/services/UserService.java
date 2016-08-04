package lt.tieto.msi2016.auth.services;


import lt.tieto.msi2016.auth.model.User;

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
}
