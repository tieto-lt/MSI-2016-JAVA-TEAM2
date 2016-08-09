package lt.tieto.msi2016.missions.model.mission;

import java.util.List;

/**
 * Created by localadmin on 16.8.9.
 */
public class MissionCommands {

    private String commandType;
    private List<String> args;


    public String getCommandType() {
        return commandType;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }
}
