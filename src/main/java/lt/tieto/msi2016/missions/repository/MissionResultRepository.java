package lt.tieto.msi2016.missions.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.missions.repository.mode.MissionResultDb;
import lt.tieto.msi2016.utils.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 * Created by localadmin on 16.8.9.
 */
@Component
public class MissionResultRepository extends BaseRepository<MissionResultDb> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public MissionResultRepository() {
        super(ROW_MAPPER, ROW_UNMAPPER, "mission_results", "id");
    }

    private static final RowMapper<MissionResultDb> ROW_MAPPER = (rs, rowNum) -> {
        MissionResultDb missionResultDb = new MissionResultDb();
        missionResultDb.setId(rs.getLong("id"));
        missionResultDb.setMissionId(rs.getLong("missionId"));
        missionResultDb.setOperatorId(rs.getLong("operatorId"));
        missionResultDb.setResult(rs.getString("result"));
        return missionResultDb;
    };

    private static final RowUnmapper<MissionResultDb> ROW_UNMAPPER = missionResultDb -> mapOf(
            "id", missionResultDb.getId(),
            "missionId", missionResultDb.getMissionId(),
            "operatorId", missionResultDb.getOperatorId(),
            "result", missionResultDb.getResult()
    );


}
