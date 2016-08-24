package lt.tieto.msi2016.missions.model.mission;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by localadmin on 16.8.9.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MissionPlan {

    private Long missionId;
    private String submittedBy;
    private String state;
    private List<MissionCommands> commands;


    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<MissionCommands> getCommands() {
        return commands;
    }

    public void setCommands(List<MissionCommands> commands) {
        this.commands = commands;
    }
}
