package lt.tieto.msi2016.missions.services;

import lt.tieto.msi2016.missions.model.mission.MissionPlan;
import lt.tieto.msi2016.missions.model.mission.MissionResponse;
import lt.tieto.msi2016.missions.model.mission.MissionResult;
import lt.tieto.msi2016.missions.model.mission.Result;

import java.util.List;

/**
 * Created by localadmin on 16.8.9.
 */
public interface MissionService {

    MissionResponse getDefaultMission();

    void saveResults(Long missionId, String operatorToken, String result);

    Result getResultFromBlob (MissionResult missionResult );

    boolean isAnyMissionDone(String username);

    Result getResultFromOperatorId(Long missionId);

    List<MissionResponse>  getUsersMissions();

    void changeOrderStatus(String status, Long missionId);

    MissionPlan reserve(String operatorToken, Long missionId);
}
