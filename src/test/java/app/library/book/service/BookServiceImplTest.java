package app.library.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import app.library.audit.entity.Audit;
import app.library.book.Entity.Book;
import app.library.book.dao.BookDao;
import app.library.book.models.BookIdRequest;
import app.library.book.models.BookRequest;
import app.library.common.JsonParser;
import app.library.common.exceptions.BookListEmptyException;
import app.library.common.exceptions.BookNotFoundException;
import app.library.common.exceptions.InvalidBookDetailsException;
import app.library.common.exceptions.InvalidBookRequest;
import app.library.common.exceptions.KafkaProducerException;
import app.library.kafka.producer.KafkaProducer;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BookServiceImpl.class})
@ExtendWith(SpringExtension.class)
class BookServiceImplTest {
    @MockBean
    private BookDao bookDao;

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @MockBean
    private JsonParser jsonParser;

    @MockBean
    private KafkaProducer kafkaProducer;


    @Test
    void testGetBookById() throws BookNotFoundException, InvalidBookRequest {
        Book book = new Book("42","Good Book","Surya","The characteristics of someone or something",1L,1L);
        Optional<Book> ofResult = Optional.of(book);
        when(bookDao.getBookById((String) any())).thenReturn(ofResult);
        assertSame(book, bookServiceImpl.getBookById(new BookIdRequest("Bookid", "User")));
        verify(bookDao).getBookById((String) any());
    }
    @Test
    void testGetBookById2() throws BookNotFoundException, InvalidBookRequest {
        when(bookDao.getBookById((String) any())).thenReturn(Optional.empty());
        assertThrows(BookNotFoundException.class, () -> bookServiceImpl.getBookById(new BookIdRequest("Bookid", "User")));
        verify(bookDao).getBookById((String) any());
    }
    @Test
    void testGetBookById3() throws BookNotFoundException, InvalidBookRequest {
        Book book = new Book("42","Good Book","Surya","The characteristics of someone or something",1L,1L);
        Optional<Book> ofResult = Optional.of(book);
        when(bookDao.getBookById((String) any())).thenReturn(ofResult);
        assertThrows(InvalidBookRequest.class, () -> bookServiceImpl.getBookById(null));
    }

    @Test
    void testGetAllBooks() throws BookListEmptyException {
        when(bookDao.findAll()).thenReturn(new ArrayList<>());
        assertThrows(BookListEmptyException.class, () -> bookServiceImpl.getAllBooks());
        verify(bookDao).findAll();
    }

    @Test
    void testGetAllBooks2() throws BookListEmptyException {
        Book book = new Book("42","Good Book","Surya","The characteristics of someone or something",1L,1L);
        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book);
        when(bookDao.findAll()).thenReturn(bookList);
        List<Book> actualAllBooks = bookServiceImpl.getAllBooks();
        assertSame(bookList, actualAllBooks);
        assertEquals(1, actualAllBooks.size());
        verify(bookDao).findAll();
    }

    @Test
    void testAddBook()
            throws InvalidBookDetailsException, InvalidBookRequest, KafkaProducerException, JsonProcessingException {
        doNothing().when(bookDao).addBook((Book) any());
        when(jsonParser.stringify((Audit) any())).thenReturn("Stringify");
        doNothing().when(kafkaProducer).sendMessage((String) any());

        Book book = new Book("42","Good Book","Surya","The characteristics of someone or something",1L,1L);

        BookRequest bookRequest = new BookRequest();
        bookRequest.setBook(book);
        bookServiceImpl.addBook(bookRequest);
        verify(bookDao).addBook((Book) any());
        verify(jsonParser).stringify((Audit) any());
        verify(kafkaProducer).sendMessage((String) any());
    }
    @Test
    void testAddBook2()
            throws InvalidBookDetailsException, InvalidBookRequest, KafkaProducerException, JsonProcessingException {
        doNothing().when(bookDao).addBook((Book) any());
        when(jsonParser.stringify((Audit) any())).thenReturn("Stringify");
        doThrow(new KafkaProducerException("An error occurred")).when(kafkaProducer).sendMessage((String) any());

        Book book = new Book("42","Good Book","Surya","The characteristics of someone or something",1L,1L);

        BookRequest bookRequest = new BookRequest();
        bookRequest.setBook(book);
        assertThrows(KafkaProducerException.class, () -> bookServiceImpl.addBook(bookRequest));
        verify(bookDao).addBook((Book) any());
        verify(jsonParser).stringify((Audit) any());
        verify(kafkaProducer).sendMessage((String) any());
    }
    @Test
    void testAddBook3()
            throws InvalidBookDetailsException, InvalidBookRequest, KafkaProducerException, JsonProcessingException {
        doNothing().when(bookDao).addBook((Book) any());
        assertThrows(InvalidBookRequest.class, () -> bookServiceImpl.addBook(null));

    }

    @Test

    void testUpdateBook()
            throws InvalidBookDetailsException, InvalidBookRequest, KafkaProducerException, JsonProcessingException {
        assertThrows(InvalidBookRequest.class, () -> bookServiceImpl.updateBook(null));
    }


    @Test
    void testUpdateBook2()
            throws InvalidBookDetailsException, InvalidBookRequest, KafkaProducerException, JsonProcessingException {
        doNothing().when(bookDao).updateBook((Book) any());
        when(jsonParser.stringify((Audit) any())).thenReturn("Stringify");
        doNothing().when(kafkaProducer).sendMessage((String) any());

        Book book = new Book("42","Good Book","Surya","The characteristics of someone or something",1L,1L);
        BookRequest bookRequest = new BookRequest();
        bookRequest.setBook(book);
        bookServiceImpl.updateBook(bookRequest);
        verify(bookDao).updateBook((Book) any());
        verify(jsonParser).stringify((Audit) any());
        verify(kafkaProducer).sendMessage((String) any());
    }


    @Test
    void testUpdateBook3()
            throws InvalidBookDetailsException, InvalidBookRequest, KafkaProducerException, JsonProcessingException {
        doNothing().when(bookDao).updateBook((Book) any());
        when(jsonParser.stringify((Audit) any())).thenReturn("Stringify");
        doThrow(new KafkaProducerException("An error occurred")).when(kafkaProducer).sendMessage((String) any());

        Book book = new Book("42","Good Book","Surya","The characteristics of someone or something",1L,1L);

        BookRequest bookRequest = new BookRequest();
        bookRequest.setBook(book);
        assertThrows(KafkaProducerException.class, () -> bookServiceImpl.updateBook(bookRequest));
        verify(bookDao).updateBook((Book) any());
        verify(jsonParser).stringify((Audit) any());
        verify(kafkaProducer).sendMessage((String) any());
    }


    @Test
    void testDeleteBook()
            throws InvalidBookDetailsException, InvalidBookRequest, KafkaProducerException, JsonProcessingException {
        doNothing().when(bookDao).deleteBook((String) any());
        when(jsonParser.stringify((Audit) any())).thenReturn("Stringify");
        doNothing().when(kafkaProducer).sendMessage((String) any());
        bookServiceImpl.deleteBook(new BookIdRequest("Bookid", "User"));
        verify(bookDao).deleteBook((String) any());
        verify(jsonParser).stringify((Audit) any());
        verify(kafkaProducer).sendMessage((String) any());
    }


    @Test
    void testDeleteBook2()
            throws InvalidBookDetailsException, InvalidBookRequest, KafkaProducerException, JsonProcessingException {
        doNothing().when(bookDao).deleteBook((String) any());
        when(jsonParser.stringify((Audit) any())).thenReturn("Stringify");
        doThrow(new KafkaProducerException("An error occurred")).when(kafkaProducer).sendMessage((String) any());
        assertThrows(KafkaProducerException.class, () -> bookServiceImpl.deleteBook(new BookIdRequest("Bookid", "User")));
        verify(bookDao).deleteBook((String) any());
        verify(jsonParser).stringify((Audit) any());
        verify(kafkaProducer).sendMessage((String) any());
    }


    @Test
    void testDeleteBook3()
            throws InvalidBookDetailsException, InvalidBookRequest, KafkaProducerException, JsonProcessingException {
        doNothing().when(bookDao).deleteBook((String) any());
        when(jsonParser.stringify((Audit) any())).thenReturn("Stringify");
        doNothing().when(kafkaProducer).sendMessage((String) any());
        assertThrows(InvalidBookRequest.class, () -> bookServiceImpl.deleteBook(null));
    }
}

