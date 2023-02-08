package app.library.common.exceptions;

public class InvalidUserDetailsException extends Exception{
    public InvalidUserDetailsException(String message) {
        super(message);
    }
}
