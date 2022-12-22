package app.library.book;

import app.library.book.googlebooks.GoogleBooks;
import app.library.book.googlebooks.GoogleBooksWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class bookcontroller {

    @Autowired
    BookServiceImpl bookserviceimpl;

    @PostMapping("/books")
    public void addbook(@RequestBody Book book){
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
     public void updatebook(@RequestBody Book book){
        bookserviceimpl.updatebook(book);
    }
    @DeleteMapping("/books/{id}")
    public void deletebook(@PathVariable("id") long bookid){
        bookserviceimpl.deletebook(bookid);
    }
}
