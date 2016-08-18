package lt.tieto.msi2016.missions.repository.model;

import lt.tieto.msi2016.orders.repository.model.OrderDb;
import lt.tieto.msi2016.utils.repository.model.DbModel;

/**
 * Class which is used to represent the result of joining Mission and Order tables
 *
 * Created by localadmin on 16.8.17.
 */
public class MissionOrderDb extends DbModel {

    private String missionJSON;
    private String submittedBy;
    private String status;


    public String getMissionJSON() {
        return missionJSON;
    }

    public void setMissionJSON(String missionJSON) {
        this.missionJSON = missionJSON;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }
}
