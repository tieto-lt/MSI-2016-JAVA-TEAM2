package lt.tieto.msi2016.missions.repository.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lt.tieto.msi2016.utils.repository.model.DbModel;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by localadmin on 16.8.9.
 */
public class MissionResultDb extends DbModel {

    private Long operatorId;
    private Long missionId;
    private String result;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
    private Timestamp missionDate;

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
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
