package repository.Errors;

public class NotFoundError extends RepositoryError {
    /**
     * comstructor
     * @param s String, the message
     */
    public NotFoundError(String s) {
        super(s);
    }
}
