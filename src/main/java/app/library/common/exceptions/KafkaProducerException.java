package app.library.common.exceptions;

public class KafkaProducerException extends Exception{
    public KafkaProducerException(String message) {
        super(message);
    }
}
