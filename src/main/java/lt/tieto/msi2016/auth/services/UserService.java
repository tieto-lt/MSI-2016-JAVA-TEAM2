package lt.tieto.msi2016.auth.services;


import lt.tieto.msi2016.auth.model.User;
import lt.tieto.msi2016.item.model.Item;
import lt.tieto.msi2016.item.service.ItemService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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


    Collection<User> all();
}
