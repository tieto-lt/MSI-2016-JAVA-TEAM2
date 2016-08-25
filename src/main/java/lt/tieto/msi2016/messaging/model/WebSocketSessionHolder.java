package lt.tieto.msi2016.messaging.model;

import org.springframework.web.socket.WebSocketSession;

/**
 * Created by localadmin on 16.8.24.
 */
public class WebSocketSessionHolder {

    private String mapKey;
    private WebSocketSession videoSession;
    private WebSocketSession controlSession;

    public String getMapKey() {
        return mapKey;
    }

    public void setMapKey(String mapKey) {
        this.mapKey = mapKey;
    }

    public WebSocketSession getVideoSession() {
        return videoSession;
    }

    public void setVideoSession(WebSocketSession videoSession) {
        this.videoSession = videoSession;
    }

    public WebSocketSession getControlSession() {
        return controlSession;
    }

    public void setControlSession(WebSocketSession controlSession) {
        this.controlSession = controlSession;
    }
}
