package app.library.book.service;

import app.library.audit.entity.Audit;
import app.library.book.Entity.Book;
import app.library.book.dao.BookDao;
import app.library.book.models.BookIdRequest;
import app.library.book.models.BookRequest;
import app.library.common.JsonParser;
import app.library.common.exceptions.*;
import app.library.kafka.producer.KafkaProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static app.library.common.BookConstants.INVALID_BOOK_DETAILS;

@Service
public class BookServiceImpl  implements BookService {

    @Autowired
    BookDao bookDao;

    @Autowired
    JsonParser jsonParser;

    @Autowired
    KafkaProducer kafkaProducer;

    public Book getBookById(BookIdRequest bookIdRequest) throws BookNotFoundException, InvalidBookRequest {
        if (bookIdRequest==null){
            throw new InvalidBookRequest("Book request is null ");
        }
        Optional<Book> book=bookDao.getBookById(bookIdRequest.getBookid());
        if (book.isEmpty()){
            throw new BookNotFoundException("Book not found");
        }
        else {
            return book.get();
        }
    }

    public List<Book> getAllBooks() throws BookListEmptyException {
        List<Book> bookList=bookDao.findAll();
        if(bookList.isEmpty()) {
            throw new BookListEmptyException("Book List Empty");
        }
        else  {
            return bookList;
        }
    }
    public void addBook (BookRequest bookRequest) throws JsonProcessingException, InvalidBookDetailsException, KafkaProducerException, InvalidBookRequest {
        if (bookRequest==null){
            throw new InvalidBookRequest("Book Request is null");
        }
        try {
            bookDao.addBook(bookRequest.getBook());
        }catch(Exception ex){
            throw new InvalidBookDetailsException("Invalid Book Details");
        }
        sendMessageBooks(jsonParser.stringify(new Audit(bookRequest.getBook().getBookId(),bookRequest.getUser(),"CREATE")));
    }
    public void updateBook(BookRequest bookRequest) throws JsonProcessingException, InvalidBookDetailsException, KafkaProducerException, InvalidBookRequest {
        if (bookRequest==null){
            throw new InvalidBookRequest(INVALID_BOOK_DETAILS);
        }
        try {
            bookDao.updateBook(bookRequest.getBook());
        }catch(Exception ex){
            throw new InvalidBookDetailsException(INVALID_BOOK_DETAILS);
        }
        sendMessageBooks(jsonParser.stringify(new Audit(bookRequest.getBook().getBookId(),bookRequest.getUser(),"UPDATE")));

    }

    public void deleteBook (BookIdRequest bookIdRequest) throws JsonProcessingException, InvalidBookDetailsException, KafkaProducerException, InvalidBookRequest {
        if (bookIdRequest==null){
            throw new InvalidBookRequest(INVALID_BOOK_DETAILS);
        }
        try{
        bookDao.deleteBook(bookIdRequest.getBookid());}
        catch(Exception ex){
            throw new InvalidBookDetailsException(INVALID_BOOK_DETAILS);
        }
        sendMessageBooks(jsonParser.stringify(new Audit(bookIdRequest.getBookid(), bookIdRequest.getUser(), "DELETE")));

    }



    private void sendMessageBooks(String message) throws KafkaProducerException {

        kafkaProducer.sendMessage(message);
    }



}
