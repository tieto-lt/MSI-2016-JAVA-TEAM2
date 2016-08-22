package lt.tieto.msi2016.missions.model.mission;

import com.fasterxml.jackson.annotation.JsonFormat;
import lt.tieto.msi2016.missions.repository.model.MissionResultDb;
import org.joda.time.DateTime;

import java.sql.Time;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Created by localadmin on 16.8.10.
 */
public class MissionResult {

    private Long id;

    private Long missionId;

    private Long operatorId;

    private String result;

    private DateTime missionDate;

    public static MissionResult missionResult (MissionResultDb missionResultDb)
    {
        MissionResult missionResult = new MissionResult();
        missionResult.setId(missionResultDb.getId());
        missionResult.setMissionId(missionResultDb.getMissionId());
        missionResult.setOperatorId(missionResultDb.getOperatorId());
        missionResult.setResult(missionResultDb.getResult());
        missionResult.setMissionDate(missionResultDb.getMissionDate());
        return missionResult;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public DateTime getMissionDate() {
        return missionDate;
    }

    public void setMissionDate(DateTime missionDate) {
        this.missionDate = missionDate;
    }
}
