package lt.tieto.msi2016.missions.services;

import lt.tieto.msi2016.missions.model.MissionCommands;
import lt.tieto.msi2016.missions.model.MissionPlan;
import lt.tieto.msi2016.missions.model.MissionResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by localadmin on 16.8.9.
 */
@Service
public class MissionServiceImpl implements MissionService {

    private static MissionResponse defaultMission;

    {
        defaultMission = new MissionResponse();

        List<MissionPlan> missionPlanList = new ArrayList<>();
        MissionPlan defaultMissionPlan = new MissionPlan();
        defaultMissionPlan.setMissionId(-1L);
        defaultMissionPlan.setSubmittedBy("System");
        missionPlanList.add(defaultMissionPlan);

        List<MissionCommands> missionCommandsList = new ArrayList<>();

        MissionCommands firstMissionCommand = new MissionCommands();
        firstMissionCommand.setCommandType("takeoff");
        MissionCommands secondMissionCommand = new MissionCommands();
        secondMissionCommand.setCommandType("land");
        missionCommandsList.add(firstMissionCommand);
        missionCommandsList.add(secondMissionCommand);

        defaultMissionPlan.setCommands(missionCommandsList);

        defaultMission.setMissions(missionPlanList);

    }

    @Override
    public MissionResponse getDefaultMission() {
        return defaultMission;
    }
}
