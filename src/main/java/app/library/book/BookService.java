package app.library.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public interface BookService {
    public void updateBook(Book book) throws JsonProcessingException;
    public void deleteBook (String bookid) throws JsonProcessingException;
    public void addBook (Book book) throws JsonProcessingException;

}
