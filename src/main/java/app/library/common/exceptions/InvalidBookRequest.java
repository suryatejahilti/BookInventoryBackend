package app.library.common.exceptions;

public class InvalidBookRequest extends Exception{
    public InvalidBookRequest(String message) {
        super(message);
    }
}
