package app.library.googlebooks.controller;

import app.library.book.Entity.Book;
import app.library.googlebooks.service.GoogleBooksServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GoogleBooksController {
    @Autowired
    GoogleBooksServiceImpl googleBooksServiceImpl;


    @GetMapping("/googlebooks/{id}")
    public List<Book> getgooglebooks(@PathVariable("id") String searchId) throws Exception{
        return googleBooksServiceImpl.getBooks(searchId);
    }
}
