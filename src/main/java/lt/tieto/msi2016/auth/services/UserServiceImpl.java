package lt.tieto.msi2016.auth.services;


import lt.tieto.msi2016.auth.model.User;
import lt.tieto.msi2016.auth.repository.UserRepository;
import lt.tieto.msi2016.auth.repository.model.UserDb;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.stream.Collectors;

import static lt.tieto.msi2016.utils.constants.Roles.CUSTOMER;


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
        User newUser = User.valueOf(userRepository.save(userDb));
        userRepository.insertUserAuthority(user.getUserName(),CUSTOMER);
        return newUser;
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    public User getUserByUserName(String userName) {
        return User.valueOf(userRepository.findByUserName(userName));
    }
    @Transactional
    @Override
    public User updateUserInfo(User user) {
        UserDb userDb = userRepository.findOne(user.getId());
        userDb.setUserName(user.getUserName());
        userDb.setEmail(user.getEmail());
        userDb.setPassword(user.getPassword());
        userDb.setPhone(user.getPhone());
        userDb.setName(user.getName());
        User updatedUser = User.valueOf(userRepository.save(userDb));
        userRepository.insertUserAuthority(user.getUserName(),user.getUserRole());
        return updatedUser;
    }

}
