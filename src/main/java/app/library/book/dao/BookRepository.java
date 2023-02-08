package app.library.book.dao;
import app.library.book.Entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, Long> {
    List<Book> findByTitle(String title);

    Book findByBookId(String bookid);

}
