package app.library.googlebooks;

import app.library.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class GoogleBooksController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    GoogleBooksService googleBooksService;

    //"https://www.googleapis.com/books/v1/volumes?q=" + searchid + "&maxResults=30"

    @GetMapping("/googlebooks/{id}")
    public List<Book> getgooglebooks(@PathVariable("id") String searchid) throws Exception{
        try {
            GoogleBooks googlebooks = restTemplate.getForObject("https://www.googleapis.com/books/v1/volumes?q=" + searchid + "&maxResults=30", GoogleBooks.class);
            //ResponseEntity<GoogleBooks> responseEntity = restTemplate.getForEntity("https://www.googleapis.com/books/v1/volumes?q=a&maxResults=30", GoogleBooks.class);
            return googleBooksService.getmodifiedbooks(googlebooks);
        }catch(Exception e){
            throw  new HttpClientErrorException(HttpStatus.REQUEST_TIMEOUT);
        }
    }
}
