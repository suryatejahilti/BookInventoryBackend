package app.library.book.service;

import app.library.book.Entity.Book;
import app.library.book.models.BookIdRequest;
import app.library.book.models.BookRequest;
import app.library.common.exceptions.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    public Book getBookById(BookIdRequest bookIdRequest) throws BookNotFoundException, InvalidBookRequest;
    public List<Book> getAllBooks() throws BookListEmptyException;
    public void updateBook(BookRequest bookRequest) throws JsonProcessingException, InvalidBookDetailsException, KafkaProducerException, InvalidBookRequest;
    public void deleteBook (BookIdRequest bookIdRequest) throws JsonProcessingException, InvalidBookDetailsException, KafkaProducerException, InvalidBookRequest;
    public void addBook (BookRequest bookRequest) throws JsonProcessingException, InvalidBookDetailsException, KafkaProducerException, InvalidBookRequest;

}
