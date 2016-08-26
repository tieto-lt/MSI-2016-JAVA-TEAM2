package lt.tieto.msi2016.missions.model.mission;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by localadmin on 16.8.10.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

    private ArrayList<NavigationData> navigationData;

    private ArrayList<Image> images;

    private DateTime missionDate;

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

    public DateTime getMissionDate() {
        return missionDate;
    }

    public void setMissionDate(DateTime missionDate) {
        this.missionDate = missionDate;
    }
}
