package lt.tieto.msi2016.missions.model.mission;

import com.fasterxml.jackson.annotation.JsonFormat;
import lt.tieto.msi2016.missions.repository.model.MissionResultDb;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by localadmin on 16.8.10.
 */
public class MissionResult {

    private Long id;

    private Long missionId;

    private Long operatorId;

    private String result;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
    private Timestamp missionDate;

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

    public Timestamp getMissionDate() {
        return missionDate;
    }

    public void setMissionDate(Timestamp missionDate) {
        this.missionDate = missionDate;
    }
}
