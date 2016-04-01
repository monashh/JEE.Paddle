package business.api.exceptions;

public class InvalidTrainingRegisterationException extends ApiException {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "Fecha no válida";

    public static final int CODE = 1;

    public InvalidTrainingRegisterationException() {
        this("");
    }

    public InvalidTrainingRegisterationException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
