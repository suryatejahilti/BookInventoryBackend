package app.library.googlebooks;

import app.library.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class GoogleBooksServiceImpl implements GoogleBooksService{
    @Autowired
    RestTemplate restTemplate;

    public List<Book> getBooks(String searchid) throws Exception {
        try{
        GoogleBooks googlebooks = restTemplate.getForObject("https://www.googleapis.com/books/v1/volumes?q=" + searchid + "&maxResults=30", GoogleBooks.class);
            return getModifiedBooks(googlebooks);
        }
        catch (Exception e){
            throw new Exception("Could not reach google servers",e);
        }

    }

    public List<Book> getModifiedBooks(GoogleBooks googlebooks) throws Exception {
        List<Book> bookslist = new ArrayList<>();
        for (GoogleBooksWrapper googleBooksWrapper : googlebooks.getItems()){
            bookslist.add(new Book( googleBooksWrapper));
        }
        return bookslist;
    }
}
