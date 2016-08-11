package lt.tieto.msi2016.missions.model.mission;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by localadmin on 16.8.10.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

    private ArrayList<NavigationData> navigationData;

    private ArrayList<Image> images;

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


}
