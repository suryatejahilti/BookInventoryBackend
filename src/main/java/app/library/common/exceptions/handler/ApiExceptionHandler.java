package app.library.common.exceptions.handler;

import app.library.common.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static app.library.common.BookConstants.GOOGLE_BOOKS_API_EXCEPTION;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> bookNotFoundException(BookNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("book not found");
    }

    @ExceptionHandler(BookListEmptyException.class)
    public ResponseEntity<String> bookListEmptyException(BookListEmptyException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("book not found");
    }

    @ExceptionHandler(InvalidBookDetailsException.class)
    public ResponseEntity<String> invalidBookDetailsException(InvalidBookDetailsException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Book Details");
    }

    @ExceptionHandler(InvalidUserDetailsException.class)
    public ResponseEntity<String> invalidUserDetailsException(InvalidUserDetailsException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid User Details");
    }
    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<String> userExistsException(UserExistException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
    }
    @ExceptionHandler(GoogleBooksException.class)
    public ResponseEntity<String> googleBooksException(GoogleBooksException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GOOGLE_BOOKS_API_EXCEPTION);
    }

    @ExceptionHandler(InvalidBookRequest.class)
    public ResponseEntity<String> invaildBookRequest(InvalidBookRequest ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invaild Book Request");
    }

}
