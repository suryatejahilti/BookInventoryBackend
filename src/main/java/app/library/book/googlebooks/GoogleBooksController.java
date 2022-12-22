package app.library.book.googlebooks;

import app.library.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class GoogleBooksController {
    RestTemplate restTemplate = new RestTemplate();
    @Autowired
    GoogleBooksService googleBooksService;
    @GetMapping("/googlebooks/{id}")
    public List<Book> getgooglebooks(@PathVariable("id") String searchid){
        GoogleBooks googlebooks =restTemplate.getForObject("https://www.googleapis.com/books/v1/volumes?q="+searchid, GoogleBooks.class);
        return googleBooksService.getmodifiedbooks(googlebooks);
    }
}
