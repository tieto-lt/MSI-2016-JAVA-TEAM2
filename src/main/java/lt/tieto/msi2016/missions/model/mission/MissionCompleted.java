package lt.tieto.msi2016.missions.model.mission;

/**
 * Created by localadmin on 16.8.10.
 */
public class MissionCompleted {

    private String name = "Default mission";

    private String description = "Default mission that verifies your drone";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
