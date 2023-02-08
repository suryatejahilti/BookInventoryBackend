package app.library.googlebooks.service;

import app.library.book.Entity.Book;
import app.library.googlebooks.apiwrapper.GoogleBooks;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GoogleBooksService {

    public List<Book> getModifiedBooks(GoogleBooks googlebooks) throws Exception;
    public List<Book> getBooks(String searchid) throws Exception;
}
