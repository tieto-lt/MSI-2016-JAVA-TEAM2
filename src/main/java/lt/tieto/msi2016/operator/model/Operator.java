package lt.tieto.msi2016.operator.model;

import lt.tieto.msi2016.operator.repository.model.OperatorDb;

/**
 * Created by localadmin on 16.8.8.
 */
public class Operator {

    private Long id;

    private String token;

    private Boolean isVerified;

    public static Operator valueOf (OperatorDb operatorDb)
    {
        Operator operator = new Operator();

        operator.setId(operatorDb.getId());
        operator.setToken(operatorDb.getToken());
        operator.setVerified(operatorDb.getVerified());
        return operator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
