package lt.tieto.msi2016.operator.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.operator.repository.model.OperatorDb;
import lt.tieto.msi2016.utils.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by localadmin on 16.8.8.
 */
@Repository
public class OperatorRepository extends BaseRepository<OperatorDb> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public OperatorRepository()//(RowMapper<OperatorDb> rowMapper, RowUnmapper<OperatorDb> rowUnmapper, String tableName, String idColumn) {
    {
        super(ROW_MAPPER, ROW_UNMAPPER, "operators", "id");
    }

    private static final RowMapper<OperatorDb> ROW_MAPPER = (rs, rowNum) ->
    {
        OperatorDb operator = new OperatorDb();
        operator.setId(rs.getLong("id"));
        operator.setVerified(rs.getBoolean("isVerified"));
        operator.setToken(rs.getString("token"));
        return operator;
    };

    private static final  RowUnmapper<OperatorDb> ROW_UNMAPPER = operatorDb -> mapOf(
      "id", operatorDb.getId(),
      "token", operatorDb.getToken(),
      "isVerified", operatorDb.getVerified()
    );


}
