package lt.tieto.msi2016.missions.services;

import lt.tieto.msi2016.missions.model.Mission;
import lt.tieto.msi2016.missions.model.mission.*;
import lt.tieto.msi2016.missions.repository.MissionRepository;
import lt.tieto.msi2016.missions.repository.MissionResultRepository;
import lt.tieto.msi2016.missions.repository.model.MissionDb;
import lt.tieto.msi2016.missions.repository.model.MissionResultDb;
import lt.tieto.msi2016.operator.repository.OperatorRepository;
import lt.tieto.msi2016.orders.model.Order;
import lt.tieto.msi2016.orders.repository.OrderRepository;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MissionServiceImpl implements MissionService {

    @Autowired
    private MissionResultRepository missionResultRepository;
    @Autowired
    private OperatorRepository operatorRepository;
    @Autowired
    private MissionRepository missionRepository;
    @Autowired
    private OrderRepository orderRepository;


    private static MissionResponse defaultMission;

    static {
        defaultMission = new MissionResponse();

        List<MissionPlan> missionPlanList = new ArrayList<>();
        MissionPlan defaultMissionPlan = new MissionPlan();
        defaultMissionPlan.setMissionId(-1L);
        defaultMissionPlan.setSubmittedBy("System");
        missionPlanList.add(defaultMissionPlan);

        List<MissionCommands> missionCommandsList = Arrays.asList(MissionCommands.newMission().command("takeoff"),
                MissionCommands.newMission().command("calibrate"),
                MissionCommands.newMission().command("altitude").withArguments(1.5),
                MissionCommands.newMission().command("forward").withArguments("2"),
                MissionCommands.newMission().command("takePicture"),
                MissionCommands.newMission().command("altitude").withArguments(1),
                MissionCommands.newMission().command("cw").withArguments(60),
                MissionCommands.newMission().command("takePicture"),
                MissionCommands.newMission().command("altitude").withArguments(1.5),
                MissionCommands.newMission().command("cw").withArguments(60),
                MissionCommands.newMission().command("takePicture"),
                MissionCommands.newMission().command("altitude").withArguments(1),
                MissionCommands.newMission().command("cw").withArguments(60),
                MissionCommands.newMission().command("takePicture"),
                MissionCommands.newMission().command("altitude").withArguments(1.5),
                MissionCommands.newMission().command("cw").withArguments(60),
                MissionCommands.newMission().command("takePicture"),
                MissionCommands.newMission().command("altitude").withArguments(1),
                MissionCommands.newMission().command("cw").withArguments(60),
                MissionCommands.newMission().command("takePicture"),
                MissionCommands.newMission().command("altitude").withArguments(1.5),
                MissionCommands.newMission().command("cw").withArguments(60),
                MissionCommands.newMission().command("takePicture"),
                MissionCommands.newMission().command("land"));

        defaultMissionPlan.setCommands(missionCommandsList);

        defaultMission.setMissions(missionPlanList);

    }

    @Transactional(readOnly = true)
    public List<MissionResponse> getUsersMissions() {
        return missionRepository.findAll().stream().filter(missionDb -> "approved".equals(orderRepository.findOne(missionDb.getId()).getStatus()) && missionDb.getOperatorId()==null).map(this::fillMission).collect(Collectors.toList());
    }

    @Transactional
    private MissionResponse fillMission (MissionDb missionDb)
    {
        MissionResponse missionResponse = new MissionResponse();
        Mission mission = Mission.valueOf(missionDb);
        try {
            missionResponse = new ObjectMapper().readValue(mission.getMissionJSON(), MissionResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return missionResponse;
    }

    @Override
    public MissionResponse getDefaultMission() {
        return defaultMission;
    }

    @Transactional
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

    @Transactional(readOnly = true)
    public Result getResultFromOperatorId(Long operatorId)
    {
        MissionResultDb missionResultDb = missionResultRepository.findByOperatorId(operatorId);
        MissionResult missionResult = MissionResult.missionResult(missionResultDb);
        Result result = getResultFromBlob(missionResult);
        return result;
    }

    @Transactional(readOnly = true)
    public boolean isAnyMissionDone(String username)//hack for US07
    {
        int i = missionResultRepository.selectAllMissionsDoneByUser(username);
        return i != 0;

    }

    @Transactional
    @Override
    public MissionPlan reserve(String operatorToken, Long missionId) {
        MissionDb mission = missionRepository.findOne(missionId);
        mission.setOperatorId(operatorRepository.findByToken(operatorToken).getId());
        missionRepository.save(mission);
        return getDefaultMission().getMissions().get(0);
    }




}
