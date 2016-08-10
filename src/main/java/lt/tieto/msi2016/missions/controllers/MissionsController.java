package lt.tieto.msi2016.missions.controllers;

import lt.tieto.msi2016.missions.model.mission.MissionPlan;
import lt.tieto.msi2016.missions.model.mission.MissionResponse;
import lt.tieto.msi2016.missions.services.MissionService;
import lt.tieto.msi2016.operator.services.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @RequestMapping(method = RequestMethod.GET, value = "api/missions")
    public ResponseEntity<?> getMissions(@RequestParam("operatorToken") String operatorToken) {
        if(operatorService.tokenExists(operatorToken)) {
            return ResponseEntity.ok(missionService.getDefaultMission());
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @RequestMapping(value = "/api/missions/{id}/reserve", method = RequestMethod.POST)
    public ResponseEntity<?> reserve(@RequestParam("operatorToken") String operatorToken) {
        if (operatorService.tokenExists(operatorToken)){
            return ResponseEntity.ok(missionService.getDefaultMission().getMissions().get(0));
    } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @RequestMapping(value = "/api/missions/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> verifyOperator(@PathVariable Long id, @RequestParam("operatorToken") String operatorToken, @RequestBody String result) {
        if(operatorService.tokenExists(operatorToken)) {
            operatorService.verifyOperatorService(operatorToken); // TODO: change to mission id after misions are added
            missionService.saveResults(id, operatorToken, result);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }
}
