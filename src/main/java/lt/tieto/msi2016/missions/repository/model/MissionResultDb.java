package lt.tieto.msi2016.missions.repository.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lt.tieto.msi2016.utils.repository.model.DbModel;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Created by localadmin on 16.8.9.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MissionResultDb extends DbModel {

    private Long operatorId;
    private Long missionId;
    private String result;

    private DateTime missionDate;

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

    public DateTime getMissionDate() {
        return missionDate;
    }

    public void setMissionDate(DateTime missionDate) {
        this.missionDate = missionDate;
    }

}
