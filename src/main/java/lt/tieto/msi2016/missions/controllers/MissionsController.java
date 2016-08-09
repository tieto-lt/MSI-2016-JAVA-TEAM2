package lt.tieto.msi2016.missions.controllers;

import lt.tieto.msi2016.missions.model.MissionResponse;
import lt.tieto.msi2016.missions.services.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by localadmin on 16.8.9.
 */
@RestController
public class MissionsController {

    @Autowired
    private MissionService missionService;

    @RequestMapping(method = RequestMethod.GET, value = "api/missions")
    public ResponseEntity<MissionResponse> getMissions(@RequestParam("operatorToken") String operatorToken) {
        return ResponseEntity.ok(missionService.getDefaultMission());
    }
}
