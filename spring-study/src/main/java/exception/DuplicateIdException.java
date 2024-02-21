package exception;

public class DuplicateIdException extends RuntimeException{
    private String message;

    public DuplicateIdException(){
        super();
    }

    public DuplicateIdException(String message){
        super(message);
    }
}
