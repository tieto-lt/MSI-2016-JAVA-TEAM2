package lt.tieto.msi2016.operator.repository.model;

import lt.tieto.msi2016.operator.model.Operator;
import lt.tieto.msi2016.utils.repository.model.DbModel;

/**
 * Created by localadmin on 16.8.8.
 */
public class OperatorDb extends DbModel {

    private String token;

    private Boolean isVerified;

    private Long userId;

    public static OperatorDb valueOf (Operator operator)
    {
        OperatorDb operatorDb = new OperatorDb();

        operatorDb.setId(operator.getId());
        operatorDb.setToken(operator.getToken());
        operatorDb.setVerified(operator.getVerified());
        operatorDb.setUserId(operator.getUserId());
        return operatorDb;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}