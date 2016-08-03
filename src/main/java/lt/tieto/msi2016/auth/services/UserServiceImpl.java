package lt.tieto.msi2016.auth.services;


import lt.tieto.msi2016.auth.model.User;
import lt.tieto.msi2016.auth.repository.UserRepository;
import lt.tieto.msi2016.auth.repository.model.UserDb;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;


    /**
     *{@inheritDoc}
     */
    @Override
    public void createUser(final User user) {
        userRepository.create(UserDb.valueOf(user));
    }

    public void checkUsername (User user)
    {
        //userRepository.


    }
}
