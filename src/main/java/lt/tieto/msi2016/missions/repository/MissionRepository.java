package lt.tieto.msi2016.missions.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.missions.repository.model.MissionDb;
import lt.tieto.msi2016.missions.repository.model.MissionOrderDb;
import lt.tieto.msi2016.utils.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by localadmin on 16.8.16.
 */
@Repository
public class MissionRepository extends BaseRepository<MissionDb> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public MissionRepository() {
        super(ROW_MAPPER, ROW_UNMAPPER, "missions", "id");
    }

    private static final RowMapper<MissionDb> ROW_MAPPER = (rs, rowNum) -> {
        MissionDb mission = new MissionDb();
        mission.setId(rs.getLong("id"));
        mission.setOrderId(rs.getLong("orderId"));
        mission.setOperatorId(rs.getLong("operatorId"));
        mission.setMissionJSON(rs.getString("missionJSON"));
        return mission;
    };

    private static final RowUnmapper<MissionDb> ROW_UNMAPPER = missionDb -> mapOf(
            "id", missionDb.getId(),
            "orderId", missionDb.getOrderId(),
            "operatorId", missionDb.getOperatorId(),
            "missionJSON", missionDb.getMissionJSON()
    );

   private static final RowMapper<MissionOrderDb> MISSION_ORDER_ROW_MAPPER = (rs, rowNum) -> {
        MissionOrderDb mission = new MissionOrderDb();
        mission.setId(rs.getLong("id"));
        mission.setStatus(rs.getString("status"));
        mission.setMissionJSON(rs.getString("missionJSON"));
        mission.setSubmittedBy(rs.getString("username"));
        return mission;
    };

    public MissionDb findAllIfApproved ()
    {
        try
        {
            return jdbcTemplate.queryForObject("SELECT missionJSON from missions inner join orders ON orders.id = missions.orderId where orders.status = ?", new Object[]{"approved"}, ROW_MAPPER);
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;

        }
    }
    public void changeOrderStatus(Long missionId, String status)
    {
        try{
            jdbcTemplate.update("UPDATE orders inner join missions on missions.orderId=orders.id set orders.status = ? where missions.id = ?", status, missionId);
        }
        catch (EmptyResultDataAccessException e)
        {

        }

    }

    public Collection<MissionOrderDb> getAllMissionForExecution(){
        try{
           return jdbcTemplate.query("select missions.id,missions.missionJSON,orders.status,users.username from missions " +
                   " inner join orders on orders.id = missions.orderId " +
                   " inner join users on orders.userId = users.id" +
                   " where orders.status = 'approved' and missions.operatorId is null",MISSION_ORDER_ROW_MAPPER);
        } catch (EmptyResultDataAccessException e)
        {
            return null;
        }

    }



}
