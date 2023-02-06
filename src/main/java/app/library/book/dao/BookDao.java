package app.library.book.dao;


import app.library.book.Entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public class BookDao{

    @Autowired
    BookRepository bookRepository;
    public Optional<Book> getBookById(String bookid){ return Optional.ofNullable(bookRepository.findByBookId(bookid));}

    public List<Book> findAll() { return (bookRepository.findAll()); }

    public void updateBook(Book book){ bookRepository.save(book);}

    public void deleteBook (String bookid){
        Optional<Book> book=getBookById(bookid);
        book.ifPresent(value -> bookRepository.delete(value));
    }

    public void addBook(Book book){ bookRepository.save(book);}
}
