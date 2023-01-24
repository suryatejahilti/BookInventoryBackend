package app.library.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class bookcontroller {

    @Autowired
    BookServiceImpl bookserviceimpl;


    @PostMapping("/books")
    public void addbook(@RequestBody Book book) throws JsonProcessingException {
        bookserviceimpl.addbook(book);
    }

    @GetMapping("/booked")
    public String getstrings(){
        return "Hi";
    }
    @GetMapping("/books")
    public List<Book> getallbooks(){
        return bookserviceimpl.getAllbooks();
    }
    @GetMapping("/books/{id}")
    public Book getbookbyid(@PathVariable("id") long bookid){
        return bookserviceimpl.getbookbyid(bookid);
    }
    @PutMapping("/books/{id}")
     public void updatebook(@RequestBody Book book) throws JsonProcessingException {
        bookserviceimpl.updatebook(book);
    }
    @DeleteMapping("/books/{id}")
    public void deletebook(@PathVariable("id") long bookid) throws JsonProcessingException {
        bookserviceimpl.deletebook(bookid);
    }
}
