package app.library.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BookController {

    @Autowired
    BookServiceImpl bookserviceimpl;


    @PostMapping("/books")
    public void addbook(@RequestBody Book book) throws JsonProcessingException { bookserviceimpl.addBook(book); }

    @GetMapping("/booked")
    public String getstrings(){
        return "Hi";
    }

    @GetMapping("/books")
    public List<Book> getallbooks(){
        return bookserviceimpl.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Book getbookbyid(@PathVariable("id") String bookid){
        return bookserviceimpl.getBookById(bookid);
    }

    @PutMapping("/books/{id}")
     public void updatebook(@RequestBody Book book) throws JsonProcessingException { bookserviceimpl.updateBook(book); }

    @DeleteMapping("/books/{id}")
    public void deletebook(@PathVariable("id") String bookid) throws JsonProcessingException { bookserviceimpl.deleteBook(bookid); }
}
