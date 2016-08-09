package lt.tieto.msi2016.missions.model;

import java.util.List;

/**
 * Created by localadmin on 16.8.9.
 */
public class MissionResponse {

    private List<MissionPlan> missions;

    public List<MissionPlan> getMissions() {
        return missions;
    }

    public void setMissions(List<MissionPlan> missions) {
        this.missions = missions;
    }
}
