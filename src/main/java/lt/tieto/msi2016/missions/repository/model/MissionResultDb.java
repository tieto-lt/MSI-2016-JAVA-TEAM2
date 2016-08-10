package lt.tieto.msi2016.missions.repository.model;

import lt.tieto.msi2016.utils.repository.model.DbModel;

/**
 * Created by localadmin on 16.8.9.
 */
public class MissionResultDb extends DbModel {

    private Long operatorId;
    private Long missionId;
    private String result;

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
}
