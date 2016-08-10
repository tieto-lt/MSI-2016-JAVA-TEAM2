package lt.tieto.msi2016.missions.model.mission;

import lt.tieto.msi2016.missions.repository.model.MissionResultDb;

/**
 * Created by localadmin on 16.8.10.
 */
public class MissionResult {

    private Long id;

    private Long missionId;

    private Long operatorId;

    private String result;

    public static MissionResult missionResult (MissionResultDb missionResultDb)
    {
        MissionResult missionResult = new MissionResult();
        missionResult.setId(missionResultDb.getId());
        missionResult.setMissionId(missionResultDb.getMissionId());
        missionResult.setOperatorId(missionResultDb.getOperatorId());
        missionResult.setResult(missionResultDb.getResult());
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
}
