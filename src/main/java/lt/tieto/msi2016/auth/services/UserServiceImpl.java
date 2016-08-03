package lt.tieto.msi2016.auth.services;


import lt.tieto.msi2016.auth.model.User;
import lt.tieto.msi2016.auth.repository.UserRepository;
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

    }
}
