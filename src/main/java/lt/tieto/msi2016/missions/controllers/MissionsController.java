package lt.tieto.msi2016.missions.controllers;

import lt.tieto.msi2016.auth.services.UserService;
import lt.tieto.msi2016.messaging.services.RegistryService;
import lt.tieto.msi2016.missions.model.mission.Result;
import lt.tieto.msi2016.missions.services.MissionService;
import lt.tieto.msi2016.operator.services.OperatorService;
import lt.tieto.msi2016.orders.services.OrderService;
import lt.tieto.msi2016.utils.controller.BaseController;
import lt.tieto.msi2016.utils.services.SecurityHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import static lt.tieto.msi2016.utils.constants.Roles.CUSTOMER;
import static lt.tieto.msi2016.utils.constants.Roles.OPERATOR;

/**
 * Created by localadmin on 16.8.9.
 */
@RestController
public class MissionsController extends BaseController {

    @Autowired
    private MissionService missionService;
    @Autowired
    private OperatorService operatorService;
    @Autowired
    private SecurityHolder securityHolder;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RegistryService registryService;

    @RequestMapping(method = RequestMethod.GET, value = "/api/missions")
    public ResponseEntity<?> getMissions(@RequestParam("operatorToken") String operatorToken) {
        if(!operatorService.isVerified(operatorToken)) {
            return ResponseEntity.ok(missionService.getDefaultMission());
        } else if(operatorService.isVerified(operatorToken)) {
            return ResponseEntity.ok(missionService.getUsersMissions());
        } else {
             return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/getOperators/{userId}")
    public ResponseEntity<?> getOperators(@PathVariable("userId") String userId) {
        if(registryService.findOperatorForReservation(userId)!=null){
            return ResponseEntity.ok(true);
        }
        else{
            return ResponseEntity.ok(false);
        }

    }


    @RequestMapping(value = "/api/missions/{id}/reserve", method = RequestMethod.POST)
    public ResponseEntity<?> reserve(@RequestParam("operatorToken") String operatorToken,@PathVariable("id") Long id) {
        if (operatorService.tokenExists(operatorToken)) {
            return ResponseEntity.ok(missionService.reserve(operatorToken,id));
    } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Secured(OPERATOR)
    @RequestMapping(value = "/api/int/missions", method = RequestMethod.GET, params = "onlyCompleted=true")
    public ResponseEntity<?> getCompletedMissions() {
        return ResponseEntity.ok(orderService.getCompletedOrdersByUserName(securityHolder.getUserPrincipal().getUsername()));
    }

    @Secured(CUSTOMER)
    @RequestMapping(value = "/api/int/missions", method = RequestMethod.GET)
    public ResponseEntity<?> getMissions() {
        return ResponseEntity.ok(orderService.getOrderByUserName(securityHolder.getUserPrincipal().getUsername()));
    }

    @RequestMapping(value = "/api/missions/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> verifyOperator(@PathVariable("id") Long id, @RequestParam("operatorToken") String operatorToken, @RequestBody Result result) {
        if(operatorService.tokenExists(operatorToken)) {
            if(id.equals(-1L)) {
                operatorService.verifyOperatorService(operatorToken);
            }
            else {
                missionService.changeOrderStatus("done", id);
            }
            missionService.saveResults(id, operatorToken, result);

            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }


    @RequestMapping(value = "/api/int/missions/{id}", method = RequestMethod.GET)
    public ResponseEntity<Result> returnMissionDetails (@PathVariable Long id)
    {
        return ResponseEntity.ok(missionService.getMissionResult(id));
    }
}
