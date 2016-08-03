package lt.tieto.msi2016.auth.services;


import lt.tieto.msi2016.auth.model.User;
import lt.tieto.msi2016.auth.repository.UserRepository;
import lt.tieto.msi2016.auth.repository.model.UserDb;
import static lt.tieto.msi2016.utils.constants.Authorities.*;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private BCryptPasswordEncoder encoder;
    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     *{@inheritDoc}
     */
    @Transactional
    @Override
    public void createUser(final User user) {
        UserDb userDb = UserDb.valueOf(user);
        userDb.setPassword(encoder.encode(userDb.getPassword()));
        userDb.setEnabled(1);
        userRepository.create(userDb);
        insertUserAuthority(user.getUserName(),ADMIN.toString());
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void insertUserAuthority(String userName, String authority) {
        try {
            jdbcTemplate.queryForMap("select username from authorities where username = ?", userName).isEmpty();
            jdbcTemplate.update("UPDATE authorities set authority = ? where username = ?", authority,userName);
        } catch (EmptyResultDataAccessException e) {
            jdbcTemplate.update("INSERT INTO authorities (username,authority) values(?,?)", userName,authority);
        }
    }

    public void checkUsername (User user)
    {
        //userRepository.


    }
}
