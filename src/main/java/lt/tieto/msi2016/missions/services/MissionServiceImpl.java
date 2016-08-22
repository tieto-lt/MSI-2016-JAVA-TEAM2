package lt.tieto.msi2016.missions.services;

import lt.tieto.msi2016.missions.model.mission.*;
import lt.tieto.msi2016.missions.repository.MissionRepository;
import lt.tieto.msi2016.missions.repository.MissionResultRepository;
import lt.tieto.msi2016.missions.repository.model.MissionDb;
import lt.tieto.msi2016.missions.repository.model.MissionOrderDb;
import lt.tieto.msi2016.missions.repository.model.MissionResultDb;
import lt.tieto.msi2016.operator.repository.OperatorRepository;
import lt.tieto.msi2016.orders.repository.OrderRepository;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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


    @Transactional(readOnly = true)
    public MissionResponse getUsersMissions() {
        MissionResponse response = new MissionResponse();
        response.setMissions(missionRepository.getAllMissionForExecution().stream().map(this::fillMission).collect(Collectors.toList()));
        return response;
    }

    private MissionPlan fillMission (MissionOrderDb missionOrderDb)
    {
        MissionPlan mission = new MissionPlan();
        mission.setMissionId(missionOrderDb.getId());
        mission.setSubmittedBy(missionOrderDb.getSubmittedBy());
        mission.setState(missionOrderDb.getStatus());
        try {
            mission.setCommands(Arrays.asList(new ObjectMapper().readValue(missionOrderDb.getMissionJSON(),MissionCommands[].class)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mission;
    }

    @Override
    public MissionResponse getDefaultMission() {
        MissionResponse response = new MissionResponse();
        response.setMissions(Arrays.asList(new MissionPlan[]{fillMission(missionRepository.getDefaultMission())}));
        return response;
    }

    @Transactional
    @Override
    public void saveResults(Long missionId, String operatorToken, String result) {
        MissionResultDb missionResult = new MissionResultDb();
        missionResult.setResult(result);
        missionResult.setMissionId(missionId);
        missionResult.setOperatorId(operatorRepository.findByToken(operatorToken).getId());
        missionResult.setMissionDate(DateTime.now());
        missionResultRepository.save(missionResult);
    }

    @Transactional
    public void changeOrderStatus(String status, Long missionId){missionRepository.changeOrderStatus(missionId, status);}


    public Result getResultFromBlob (MissionResult missionResult )
    {
        try {
            if(missionResult.getResult() == null) {
                return new Result();
            }
            Result result = new ObjectMapper().readValue(missionResult.getResult(), Result.class);
            return result;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Result getMissionResult(Long missionId)
    {
        MissionResultDb missionResultDb = missionResultRepository.findByMissionId(missionId);
        MissionResult missionResult = MissionResult.missionResult(missionResultDb);
        Result result = getResultFromBlob(missionResult);
        result.setMissionDate(missionResult.getMissionDate());
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
        if(missionId != -1L) {
            MissionDb mission = missionRepository.findOne(missionId);
            mission.setOperatorId(operatorRepository.findByToken(operatorToken).getId());
            missionRepository.save(mission);
            MissionPlan missionPlan = new MissionPlan();
            missionPlan.setMissionId(missionId);
            missionPlan.setCommands(new ArrayList<>());
            return missionPlan;
        }
        return getDefaultMission().getMissions().get(0);
    }




}
