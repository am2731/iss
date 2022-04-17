package repository.Errors;

public class DuplicatedIDError extends RepositoryError{
    /**
     * comstructor
     * @param s String, the message
     */
    public DuplicatedIDError(String s) {
        super(s);
    }
}
