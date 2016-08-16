package lt.tieto.msi2016.missions.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.missions.repository.model.MissionDb;
import lt.tieto.msi2016.utils.repository.BaseRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by localadmin on 16.8.16.
 */
@Repository
public class MissionRepository extends BaseRepository<MissionDb> {


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

}
