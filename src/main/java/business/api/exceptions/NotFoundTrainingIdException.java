package business.api.exceptions;

public class NotFoundTrainingIdException extends ApiException {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "No se ha encontrado ningun Training con este ID";

    public static final int CODE = 1;

    public NotFoundTrainingIdException() {
        this("");
    }

    public NotFoundTrainingIdException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
