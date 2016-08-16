package lt.tieto.msi2016.missions.repository.model;

import lt.tieto.msi2016.missions.model.Mission;
import lt.tieto.msi2016.utils.repository.model.DbModel;

/**
 * Created by localadmin on 16.8.16.
 */
public class MissionDb extends DbModel {

    private Long Id;

    private Long orderId;

    private Long operatorId;

    private String missionJSON;

    public static MissionDb valueOf (Mission mission)
    {
        MissionDb missionDb = new MissionDb();
        missionDb.setId(mission.getId());
        missionDb.setOrderId(mission.getOrderId());
        missionDb.setOperatorId(mission.getOperatorId());
        missionDb.setMissionJSON(mission.getMissionJSON());
        return missionDb;
    }

    @Override
    public Long getId() {
        return Id;
    }

    @Override
    public void setId(Long id) {
        Id = id;
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
