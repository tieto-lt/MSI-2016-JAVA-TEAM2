package lt.tieto.msi2016.missions.model.mission;

/**
 * Created by localadmin on 16.8.10.
 */
public class NavigationData {
    private int x;

    private int y;

    private int z;

    private int altitude;

    private int altitudeMeters;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public int getAltitudeMeters() {
        return altitudeMeters;
    }

    public void setAltitudeMeters(int altitudeMeters) {
        this.altitudeMeters = altitudeMeters;
    }
}
