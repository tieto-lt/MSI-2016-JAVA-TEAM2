package lt.tieto.msi2016.orders.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.orders.repository.model.OrderDb;
import lt.tieto.msi2016.utils.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

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


    public Collection<OrderDb> getCompletedOrdersWithMissionIdByUsername(String username){
        try {
             return jdbcTemplate.query("select " +
                     "missions.id,orders.userId,COALESCE(orders.name,'default') as name,COALESCE(orders.details,'default') as details,orders.email,orders.phone,orders.date,COALESCE(orders.status,'done') as status, missionDate" +
                     " from mission_results " +
                     "inner join missions on missions.id = mission_results.missionId " +
                     "inner join operators on operators.id = mission_results.operatorId " +
                     "inner join users on users.id = operators.userId " +
                     "left join orders on orders.id = missions.orderId " +
                     "where users.username = ?", ROW_MAPPER, username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Collection<OrderDb> getOrdersByUserName(String username){
        try {
            return jdbcTemplate.query("Select * from orders inner join users on orders.userId=users.id where username= ?", ROW_MAPPER, username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }




}
