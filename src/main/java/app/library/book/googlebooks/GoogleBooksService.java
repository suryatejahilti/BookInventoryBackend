package app.library.book.googlebooks;

import app.library.book.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class GoogleBooksService {

    public List<Book> getmodifiedbooks(GoogleBooks googlebooks) {
        List<GoogleBooksWrapper> googlebookitems=googlebooks.getItems();

        List<Book> bookslist = new ArrayList<>();
        for (GoogleBooksWrapper googleBooksWrapper : googlebookitems){
            Book book=new Book();
            try {
                book.setTitle(googleBooksWrapper.getVolumeInfo().getTitle());
            }
            catch (Exception err){

            }
            try{
                book.setAuthor(googleBooksWrapper.getVolumeInfo().getAuthors()[0]);
            }
            catch (Exception err){

            }
            try{
                book.setDescription(googleBooksWrapper.getVolumeInfo().getDescription());
            }
            catch (Exception err){

            }


            bookslist.add(book);
        }
        return bookslist;
    }
}
