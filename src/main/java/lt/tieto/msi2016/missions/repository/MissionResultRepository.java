package lt.tieto.msi2016.missions.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.missions.repository.model.MissionResultDb;
import lt.tieto.msi2016.utils.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
        missionResultDb.setMissionDate(rs.getTimestamp("missionDate"));
        return missionResultDb;
    };

    private static final RowUnmapper<MissionResultDb> ROW_UNMAPPER = missionResultDb -> mapOf(
            "id", missionResultDb.getId(),
            "missionId", missionResultDb.getMissionId(),
            "operatorId", missionResultDb.getOperatorId(),
            "result", missionResultDb.getResult(),
            "missionDate", missionResultDb.getMissionDate()
    );

    public MissionResultDb findByOperatorId(Long operatorId)
    {
        try
        {
            return jdbcTemplate.queryForObject("select * from mission_results where operatorId = ?", new Object[]{operatorId}, ROW_MAPPER);
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;

        }
    }


    public MissionResultDb findByMissionId(Long missionId) {
        try
        {
            return jdbcTemplate.queryForObject("select * from mission_results where missionId = ?", new Object[]{missionId}, ROW_MAPPER);
        }
        catch (EmptyResultDataAccessException e)
        {
            return new MissionResultDb();

        }
    }


    public  int selectAllMissionsDoneByUser(String username)
    {
        try
        {
           return jdbcTemplate.queryForObject("select mission_results.missionId from users inner join operators on operators.userId = users.id inner join mission_results on mission_results.operatorId = operators.id where users.username = ?",Integer.class,username);
        }
        catch (EmptyResultDataAccessException e)
        {
            return 0;
        }
    }



}
