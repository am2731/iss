package Controller;

/**
 * Exception class - exceptions that are thrown in Controller
 */
public class ControllerException extends RuntimeException {

    /**
     * constructor
     *
     * @param s: String - the message
     */
    public ControllerException(String s) {
        super(s);
    }

}
