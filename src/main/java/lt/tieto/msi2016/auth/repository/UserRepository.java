package lt.tieto.msi2016.auth.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.auth.repository.model.UserDb;
import lt.tieto.msi2016.utils.repository.BaseRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends BaseRepository<UserDb> {

    public UserRepository() {
        super(ROW_MAPPER, ROW_UNMAPPER, "users", "username");
    }

    private static final RowMapper<UserDb> ROW_MAPPER = (rs, rowNum) -> {
        UserDb user = new UserDb();
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
            "phone", userDb.getPhone()
    );

}
