package app.library.book.controller;


import app.library.book.Entity.Book;
import app.library.book.models.BookIdRequest;
import app.library.book.models.BookRequest;
import app.library.book.service.BookServiceImpl;
import app.library.common.exceptions.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BookController {

    @Autowired
    BookServiceImpl bookserviceimpl;

    @GetMapping("/booked")
    public String getStrings(){
        return "Hi";
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() throws BookListEmptyException {
        return ResponseEntity.ok(bookserviceimpl.getAllBooks());
    }

    @GetMapping("/books/{bookId}/{user}")
    public ResponseEntity<Book> getBookById(@PathVariable String bookId,@PathVariable String user ) throws BookNotFoundException, InvalidBookRequest {
        return ResponseEntity.ok(bookserviceimpl.getBookById(new BookIdRequest(bookId,user)));
    }
    @PostMapping("/books")
    public ResponseEntity<Void> addBook(@RequestBody BookRequest bookRequest) throws JsonProcessingException, InvalidBookDetailsException, KafkaProducerException, InvalidBookRequest {
        bookserviceimpl.addBook(bookRequest);
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @PutMapping("/books")
     public ResponseEntity<Void> updateBook(@RequestBody BookRequest bookRequest) throws JsonProcessingException, InvalidBookDetailsException, KafkaProducerException, InvalidBookRequest {
        bookserviceimpl.updateBook(bookRequest);
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @DeleteMapping("/books/{BookId}/{user}")
    public ResponseEntity<Void> deleteBook(@PathVariable String bookId,@PathVariable String user) throws JsonProcessingException, InvalidBookDetailsException, KafkaProducerException, InvalidBookRequest {
        bookserviceimpl.deleteBook(new BookIdRequest(bookId,user));
        return new ResponseEntity<>( HttpStatus.OK );
    }
}
