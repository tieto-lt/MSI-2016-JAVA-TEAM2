package lt.tieto.msi2016.missions.model;

import lt.tieto.msi2016.missions.repository.model.MissionDb;

/**
 * Created by localadmin on 16.8.16.
 */
public class Mission {

    private Long id;

    private Long orderId;

    private Long operatorId;

    private String missionJSON;

    public static Mission valueOf(MissionDb missionDb)
    {
        Mission mission = new Mission();
        mission.setId(missionDb.getId());
        mission.setOrderId(missionDb.getOrderId());
        mission.setOperatorId(missionDb.getOperatorId());
        mission.setMissionJSON(missionDb.getMissionJSON());
        return mission;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getMissionJSON() {
        return missionJSON;
    }

    public void setMissionJSON(String missionJSON) {
        this.missionJSON = missionJSON;
    }
}
