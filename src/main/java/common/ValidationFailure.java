package common;


public interface ValidationFailure {

    static ValidationFailure withErrorMessage(String exceptionMessage) {
        return new ValidationWithException(exceptionMessage);
    }
}
