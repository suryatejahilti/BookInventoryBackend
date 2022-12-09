package app.library.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class bookcontroller {
    @Autowired
    BookServiceImpl bookserviceimpl;

    @PostMapping("/book")
    public void addbook(@RequestBody Book book){
        bookserviceimpl.addbook(book);
    }

    @GetMapping("/books")
    public String getstrings(){
        return "Hi";
    }
    @GetMapping("/book")
    public List<Book> getallbooks(){
        return bookserviceimpl.getAllbooks();
    }
    @GetMapping("/book/{id}")
    public Book getbookbyid(@PathVariable("id") long bookid){
        return bookserviceimpl.getbookbyid(bookid);
    }
    @PutMapping("/book")
     public void updatebook(@RequestBody Book book){
        bookserviceimpl.updatebook(book);
    }
    @DeleteMapping("/book/{id}")
    public void deletebook(@PathVariable("id") long bookid){
        bookserviceimpl.deletebook(bookid);
    }
}
