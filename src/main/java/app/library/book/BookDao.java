package app.library.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDao{

    @Autowired
    BookRepository bookRepository;
    public Book getBookById(String bookid){ return bookRepository.findByBookId(bookid);}

    public List<Book> findAll() { return new ArrayList<>(bookRepository.findAll()); }

    public void updateBook(Book book){ bookRepository.save(book);}

    public void deleteBook (String bookid){ bookRepository.delete(getBookById(bookid)); }

    public void addBook(Book book){ bookRepository.save(book);}
}
