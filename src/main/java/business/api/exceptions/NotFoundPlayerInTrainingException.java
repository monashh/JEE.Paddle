package business.api.exceptions;

public class NotFoundPlayerInTrainingException extends ApiException {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "Este usuario no se ha apuntado a este entrenamiento";

    public static final int CODE = 1;

    public NotFoundPlayerInTrainingException() {
        this("");
    }

    public NotFoundPlayerInTrainingException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
