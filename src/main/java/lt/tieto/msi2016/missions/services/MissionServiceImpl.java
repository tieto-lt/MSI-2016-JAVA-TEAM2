package lt.tieto.msi2016.missions.services;

import lt.tieto.msi2016.missions.model.mission.*;
import lt.tieto.msi2016.missions.repository.MissionResultRepository;
import lt.tieto.msi2016.missions.repository.model.MissionResultDb;
import lt.tieto.msi2016.operator.repository.OperatorRepository;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by localadmin on 16.8.9.
 */
@Service
public class MissionServiceImpl implements MissionService {

    @Autowired
    private MissionResultRepository missionResultRepository;
    @Autowired
    private OperatorRepository operatorRepository;


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

    @Override
    public void saveResults(Long missionId, String operatorToken, String result) {
        MissionResultDb missionResult = new MissionResultDb();
        missionResult.setResult(result);
        missionResult.setMissionId(missionId);
        missionResult.setOperatorId(operatorRepository.findByToken(operatorToken).getId());
        missionResultRepository.save(missionResult);
    }


    public Result getResultFromBlob (MissionResult missionResult )
    {
        try {
            Result result = new ObjectMapper().readValue(missionResult.getResult(), Result.class);
            return result;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Result getResultFromOperatorId(Long operatorId)
    {
        MissionResultDb missionResultDb = missionResultRepository.findByOperatorId(operatorId);
        MissionResult missionResult = MissionResult.missionResult(missionResultDb);
        Result result = getResultFromBlob(missionResult);
        return result;
    }

    public boolean isAnyMissionDone(String username)//hack for US07
    {
        int i = missionResultRepository.selectAllMissionsDoneByUser(username);
        return i != 0;

    }
}
