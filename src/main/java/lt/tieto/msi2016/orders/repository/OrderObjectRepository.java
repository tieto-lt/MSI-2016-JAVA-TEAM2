package lt.tieto.msi2016.orders.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.orders.repository.model.OrderObjectDb;
import lt.tieto.msi2016.utils.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by localadmin on 16.8.18.
 */
@Repository
public class OrderObjectRepository extends BaseRepository<OrderObjectDb> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public OrderObjectRepository() {
        super(ROW_MAPPER, ROW_UNMAPPER, "objects", "id");
    }

    private static final RowMapper<OrderObjectDb> ROW_MAPPER = (rs, rowNum) ->{
        OrderObjectDb orderObjectDb = new OrderObjectDb();
        orderObjectDb.setId(rs.getLong("id"));
        orderObjectDb.setOrderId(rs.getLong("orderId"));
        orderObjectDb.setObjectName(rs.getString("object"));
        orderObjectDb.setHow(rs.getString("how"));
        return  orderObjectDb;
    };

    private static final RowUnmapper<OrderObjectDb> ROW_UNMAPPER = orderObjectDb -> mapOf(
            "id", orderObjectDb.getId(),
            "orderId", orderObjectDb.getOrderId(),
            "object", orderObjectDb.getObjectName(),
            "how", orderObjectDb.getHow()
    );

    public List<OrderObjectDb> getOrderObjectsByOrderId(Long orderId){
        try {
            return jdbcTemplate.query("Select * from objects where orderId= ?", ROW_MAPPER, new Object[]{orderId});
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
