package app.library.book;

import app.library.audit.Audit;
import app.library.audit.AuditRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl  {
    public BookServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Autowired
   BookRepository bookRepository;
    ObjectMapper mapper = new ObjectMapper();
   private KafkaTemplate<String,String> kafkaTemplate;
    public Book getbookbyid(long bookid){
            Book book = bookRepository.findById(bookid).get();
            return book;
    }
    public List<Book> getAllbooks(){
        List<Book> list = new ArrayList<>();
        bookRepository.findAll().forEach(e -> list.add(e));
        return list;
    }
    public void updatebook(Book book) throws JsonProcessingException {
        Audit audit=new Audit(book.getBookid(),"UPDATE");
        kafkaTemplate.send("books",mapper.writerWithDefaultPrettyPrinter().writeValueAsString(audit));
        bookRepository.save(book);
    }
    public void deletebook (long bookid) throws JsonProcessingException {
        Audit audit=new Audit(bookid,"DELETE");
        kafkaTemplate.send("books",mapper.writerWithDefaultPrettyPrinter().writeValueAsString(audit));
        bookRepository.delete(getbookbyid(bookid));
    }
    public void addbook (Book book) throws JsonProcessingException {
        Audit audit=new Audit(book.getBookid(),"CREATE");
        kafkaTemplate.send("books",mapper.writerWithDefaultPrettyPrinter().writeValueAsString(audit));
        bookRepository.save(book);
    }
}
