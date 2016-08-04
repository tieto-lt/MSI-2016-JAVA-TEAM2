package lt.tieto.msi2016.utils.exception;

/**
 * Created by localadmin on 16.8.4.
 */
public class FieldValidationException extends RuntimeException {

    public FieldValidationException(String fieldName, String errorMessage) {
        this.fieldName = fieldName;
        this.errorMessage = errorMessage;
    }

    private String fieldName;

    private String errorMessage;

    public String getFieldName() {
        return fieldName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
