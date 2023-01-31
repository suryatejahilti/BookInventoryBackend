package app.library.googlebooks;

import app.library.book.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GoogleBooksService {

    public List<Book> getModifiedBooks(GoogleBooks googlebooks) throws Exception;
    public List<Book> getBooks(String searchid) throws Exception;
}
