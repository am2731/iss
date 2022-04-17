package repository.Errors;

public class RepositoryError extends RuntimeException{
    String s;
    /**
     * comstructor
     * @param s String, the message
     */
    public RepositoryError(String s) {
        super(s);
        this.s = s;
    }

    /**
     * override method getMessage
     * @return it returns the String s
     */
    @Override
    public String getMessage(){
        return s;
    }
}
