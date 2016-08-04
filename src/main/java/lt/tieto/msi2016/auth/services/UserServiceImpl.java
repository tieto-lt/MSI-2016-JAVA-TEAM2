package lt.tieto.msi2016.auth.services;


import lt.tieto.msi2016.auth.model.User;
import lt.tieto.msi2016.auth.repository.UserRepository;
import lt.tieto.msi2016.auth.repository.model.UserDb;
import static lt.tieto.msi2016.utils.constants.Roles.*;

import lt.tieto.msi2016.item.service.ItemService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private BCryptPasswordEncoder encoder;

    /**
     *{@inheritDoc}
     */
    @Transactional
    @Override
    public User createUser(final User user) {
        UserDb userDb = UserDb.valueOf(user);
        userDb.setPassword(encoder.encode(userDb.getPassword()));
        userDb.setEnabled(1);
        userRepository.create(userDb);
        userRepository.insertUserAuthority(user.getUserName(),CUSTOMER);
        return User.valueOf(userRepository.findByUserName(user.getUserName()));
    }

    @Override
    public User getUserInfo(Long id) {
        return User.valueOf(userRepository.findOne(id));
    }

    public void checkUsername (User user)
    {
        //userRepository.


    }

    @Transactional(readOnly = true)
    public Collection<User> all() {
        return userRepository.findAll().stream().map(User::valueOf).collect(Collectors.toList());
    }
}
