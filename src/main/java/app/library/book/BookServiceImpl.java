package app.library.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl  {
   @Autowired
   BookRepository bookRepository;
    public Book getbookbyid(long bookid){
            Book book = bookRepository.findById(bookid).get();
            return book;
    }
    public List<Book> getAllbooks(){
        List<Book> list = new ArrayList<>();
        bookRepository.findAll().forEach(e -> list.add(e));
        return list;
    }
    public void updatebook(Book book){
        bookRepository.save(book);
    }
    public void deletebook (long bookid){
        bookRepository.delete(getbookbyid(bookid));
    }
    public void addbook (Book book){
        bookRepository.save(book);
    }
}
