package lt.tieto.msi2016.missions.model.mission;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by localadmin on 16.8.10.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

    private ArrayList<NavigationData> navigationData;

    private ArrayList<Image> images;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
    private Timestamp missionDate;

    public ArrayList<NavigationData> getNavigationData() {
        return navigationData;
    }

    public void setNavigationData(ArrayList<NavigationData> navigationData) {
        this.navigationData = navigationData;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    public Timestamp getMissionDate() {
        return missionDate;
    }

    public void setMissionDate(Timestamp missionDate) {
        this.missionDate = missionDate;
    }
}
