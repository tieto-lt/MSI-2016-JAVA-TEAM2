package lt.tieto.msi2016.orders.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.orders.repository.model.OrderDb;
import lt.tieto.msi2016.utils.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by localadmin on 16.8.11.
 */
@Repository
public class OrderRepository extends BaseRepository<OrderDb>{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public OrderRepository() {
        super(ROW_MAPPER, ROW_UNMAPPER, "orders", "id");
    }

    private static final  RowMapper<OrderDb> ROW_MAPPER = (rs, rowNum) ->{
        OrderDb order = new OrderDb();
        order.setId(rs.getLong("id"));
        order.setUserId(rs.getLong("userId"));
        order.setName(rs.getString("name"));
        order.setDetails(rs.getString("details"));
        order.setEmail(rs.getString("email"));
        order.setPhone(rs.getString("phone"));
        order.setDate(rs.getString("date"));
        order.setStatus(rs.getString("status"));
        return  order;
    };

    private static final  RowUnmapper<OrderDb> ROW_UNMAPPER = orderDb -> mapOf(
      "id", orderDb.getId(),
      "userId", orderDb.getUserId(),
      "name", orderDb.getName(),
      "details", orderDb.getDetails(),
      "email", orderDb.getEmail(),
      "phone",orderDb.getPhone(),
      "date",orderDb.getDate(),
      "status", orderDb.getStatus()
    );

    /*
        public void changeOperatorVerify(Long userId, Boolean isVerified) {
        try {
            jdbcTemplate.update("UPDATE operators set isVerified = ? where userId = ?", isVerified, userId);
        } catch (EmptyResultDataAccessException e) {

        }
    }
     */


}
