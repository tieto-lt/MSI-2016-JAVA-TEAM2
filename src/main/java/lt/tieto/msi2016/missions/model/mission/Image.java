package lt.tieto.msi2016.missions.model.mission;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by localadmin on 16.8.10.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {

    private String imageBase64;

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}
