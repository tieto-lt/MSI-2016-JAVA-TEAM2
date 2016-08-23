package lt.tieto.msi2016.messaging.exceptions;

/**
 * Created by localadmin on 16.8.23.
 */
public class NoFreeOperatorsException extends RuntimeException {

    public NoFreeOperatorsException(){
        super("No free operators found");
    }
}
