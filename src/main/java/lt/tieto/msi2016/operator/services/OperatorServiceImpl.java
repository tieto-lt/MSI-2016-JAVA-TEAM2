package lt.tieto.msi2016.operator.services;

import lt.tieto.msi2016.operator.model.Operator;
import lt.tieto.msi2016.operator.repository.OperatorRepository;
import lt.tieto.msi2016.operator.repository.model.OperatorDb;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by localadmin on 16.8.8.
 */
public class OperatorServiceImpl implements OperatorService {

    @Resource
    private OperatorRepository operatorRepository;


    @Transactional(readOnly = true)
    @Override
    public Operator getOperatorState(Long id)
    {
        return fillOperator(operatorRepository.findOne(id));
    }

    @Transactional
    private Operator fillOperator(OperatorDb operatorDb){
        Operator operator = Operator.valueOf(operatorDb);

        return operator;
    }
}
