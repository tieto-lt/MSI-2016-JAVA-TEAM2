package lt.tieto.msi2016.auth.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.auth.repository.model.UserDb;
import lt.tieto.msi2016.utils.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserRepository extends BaseRepository<UserDb> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserRepository() {
        super(ROW_MAPPER, ROW_UNMAPPER, "users", "id");
    }

    private static final RowMapper<UserDb> ROW_MAPPER = (rs, rowNum) -> {
        UserDb user = new UserDb();
        user.setId(rs.getLong("id"));
        user.setUserName(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("phone"));
        return user;
    };

    private static final RowUnmapper<UserDb> ROW_UNMAPPER = userDb -> mapOf(
            "username", userDb.getUserName(),
            "password", userDb.getPassword(),
            "name", userDb.getName(),
            "email", userDb.getEmail(),
            "phone", userDb.getPhone(),
            "id", userDb.getId(),
            "enabled",userDb.getEnabled()
    );

    public boolean exists(String username) {
        return findByUserName(username) != null;
    }

    /**
     * Inserts username and authority to table authorities
     *
     * @param userName
     * @param authority
     */
    public void insertUserAuthority(String userName, String authority) {
        try {
            jdbcTemplate.queryForMap("select username from authorities where username = ?", userName);
            jdbcTemplate.update("UPDATE authorities set authority = ? where username = ?", authority,userName);
        } catch (EmptyResultDataAccessException e) {
            jdbcTemplate.update("INSERT INTO authorities (username,authority) values(?,?)", userName,authority);
        }
    }

    /**
     *
     *
     * @param userName
     * @return UserDb
     */
    public UserDb findByUserName(String userName){
            return jdbcTemplate.queryForObject("select * from users where username = ?",new Object[]{userName},ROW_MAPPER);
    }





}
