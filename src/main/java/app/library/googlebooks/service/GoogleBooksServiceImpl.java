package app.library.googlebooks.service;

import app.library.book.Entity.Book;
import app.library.common.exceptions.GoogleBooksException;
import app.library.common.exceptions.GoogleBooksMissingException;
import app.library.googlebooks.apiwrapper.GoogleBooks;
import app.library.googlebooks.apiwrapper.GoogleBooksWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static app.library.common.BookConstants.*;

@Service
public class GoogleBooksServiceImpl implements GoogleBooksService {
    @Autowired
    RestTemplate restTemplate;

    public List<Book> getBooks(String searchid) throws Exception {
        try{
        GoogleBooks googlebooks = restTemplate.getForObject(GOOGLE_BOOKS_API + searchid +GOOGLE_BOOKS_API_MAX_RESULTS+ "30", GoogleBooks.class);
            return getModifiedBooks(googlebooks);
        }
        catch (Exception e){
            throw new GoogleBooksException(GOOGLE_BOOKS_API_EXCEPTION);
        }

    }

    public List<Book> getModifiedBooks(GoogleBooks googlebooks) throws Exception {
        List<Book> bookslist = new ArrayList<>();
        for (GoogleBooksWrapper googleBooksWrapper : googlebooks.getItems()){
            try {
                bookslist.add(new Book(googleBooksWrapper));
            }
            catch (GoogleBooksMissingException ex){
                continue;
            }
            catch (Exception ex){
                throw new GoogleBooksException("Could not Convert the Google Books");
            }
        }
        return bookslist;
    }
}
