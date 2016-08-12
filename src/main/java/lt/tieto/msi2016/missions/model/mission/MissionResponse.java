package lt.tieto.msi2016.missions.model.mission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by localadmin on 16.8.9.
 */
public class MissionResponse {

    private List<MissionPlan> missions = new ArrayList<>();

    public List<MissionPlan> getMissions() {
        return missions;
    }

    public void setMissions(List<MissionPlan> missions) {
        this.missions = missions;
    }

}
