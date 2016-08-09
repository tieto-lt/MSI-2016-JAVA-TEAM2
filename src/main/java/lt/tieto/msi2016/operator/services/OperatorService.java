package lt.tieto.msi2016.operator.services;

import lt.tieto.msi2016.operator.model.Operator;

/**
 * Created by localadmin on 16.8.8.
 */
public interface OperatorService {

    /**
     * Returns user info
     *
     * @param id
     * @return
     */
    Operator getOperatorState(Long id);
    Operator generateId(Long id);

    void verifyOperatorService (String token);
}
