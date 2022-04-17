package repository.Errors;

public class DatabaseError extends RuntimeException{

    /**
     * comstructor
     * @param s String, the message
     */
    public DatabaseError(String s) {
        super(s);
    }

}