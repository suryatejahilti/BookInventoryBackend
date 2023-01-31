package app.library.book;

import app.library.audit.Audit;
import app.library.kafka.KafkaProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl  implements BookService{

    @Autowired
    BookDao bookDao;

    @Autowired
    KafkaProducer kafkaProducer;
    ObjectMapper mapper = new ObjectMapper();

    public Book getBookById(String bookid){ return bookDao.getBookById(bookid); }
    public List<Book> getAllBooks(){
        return bookDao.findAll();
    }

    public void updateBook(Book book) throws JsonProcessingException {
        bookDao.updateBook(book);
        sendMessageBooks(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new Audit(book.getBookId(),"UPDATE")));
    }

    public void deleteBook (String bookid) throws JsonProcessingException {
        bookDao.deleteBook(bookid);
        sendMessageBooks(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new Audit(bookid,"DELETE")));
    }

    public void addBook (Book book) throws JsonProcessingException {
        bookDao.addBook(book);
        sendMessageBooks(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new Audit(book.getBookId(),"CREATE")));
    }

    private void sendMessageBooks(String message){
        kafkaProducer.sendMessage(message);
    }



}
