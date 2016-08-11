package lt.tieto.msi2016.missions.model.mission;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by localadmin on 16.8.9.
 */
public class MissionCommands {

    private String commandType;
    private List<Object> args = new ArrayList<>();


    public static MissionCommands newMission(){
        return new MissionCommands();
    }

    public MissionCommands command(String commandType){
        this.setCommandType(commandType);
        return this;
    }

    public MissionCommands withArguments(Object argument){
        this.getArgs().add(argument);
        return this;
    }

    public String getCommandType() {
        return commandType;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }

    public List<Object> getArgs() {
        return args;
    }

    public void setArgs(List<Object> args) {
        this.args = args;
    }
}
