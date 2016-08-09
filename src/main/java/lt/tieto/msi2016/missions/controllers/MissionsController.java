package lt.tieto.msi2016.missions.controllers;

import lt.tieto.msi2016.missions.model.mission.MissionPlan;
import lt.tieto.msi2016.missions.model.mission.MissionResponse;
import lt.tieto.msi2016.missions.services.MissionService;
import lt.tieto.msi2016.operator.services.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by localadmin on 16.8.9.
 */
@RestController
public class MissionsController {

    @Autowired
    private MissionService missionService;
    @Autowired
    private OperatorService operatorService;

    @RequestMapping(method = RequestMethod.GET, name = "api/missions")
    public ResponseEntity<MissionResponse> getMissions(@RequestParam("operatorToken") String operatorToken) {
        return ResponseEntity.ok(missionService.getDefaultMission());
    }

    @RequestMapping(value = "/api/missions/{id}/reserve", method = RequestMethod.POST)
    public ResponseEntity<MissionPlan> reserve(@RequestParam("operatorToken") String operatorToken) {
        return ResponseEntity.ok(missionService.getDefaultMission().getMissions().get(0));

    }

    @RequestMapping(value = "/api/missions/{id}", method = RequestMethod.POST)
    public void verifyOperator(@PathVariable Long id, @RequestParam("operatorToken") String operatorToken, @RequestBody String result) {
        operatorService.verifyOperatorService(operatorToken); // TODO: change to mission id after misions are added
        missionService.saveResults(id, operatorToken, result);

    }
}
