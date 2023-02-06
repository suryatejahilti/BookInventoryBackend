package app.library.book.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import app.library.book.Entity.Book;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BookDao.class})
@ExtendWith(SpringExtension.class)
class BookDaoTest {
    @Autowired
    private BookDao bookDao;

    @MockBean
    private BookRepository bookRepository;


    @Test
    void testGetBookById() {
        Book book = new Book("42","Good Book","Surya","The characteristics of someone or something",1L,1L);
        when(bookRepository.findByBookId((String) any())).thenReturn(book);
        assertTrue(bookDao.getBookById("Bookid").isPresent());
        verify(bookRepository).findByBookId((String) any());
    }


    @Test
    void testFindAll() {
        ArrayList<Book> bookList = new ArrayList<>();
        when(bookRepository.findAll()).thenReturn(bookList);
        List<Book> actualFindAllResult = bookDao.findAll();
        assertSame(bookList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(bookRepository).findAll();
    }


    @Test
    void testUpdateBook() {
        Book book = new Book("42","Good Book","Surya","The characteristics of someone or something",1L,1L);
        when(bookRepository.save((Book) any())).thenReturn(book);

        Book book1 = new Book("42","Good Book","Surya","The characteristics of someone or something",1L,1L);
        bookDao.updateBook(book1);
        verify(bookRepository).save((Book) any());
        assertEquals("Surya", book1.getAuthor());
        assertEquals("Good Book", book1.getTitle());
        assertEquals(1L, book1.getQuantity().longValue());
        assertEquals(1L, book1.getPrice().longValue());
        assertEquals("The characteristics of someone or something", book1.getDescription());
        assertEquals("42", book1.getBookId());
        assertTrue(bookDao.findAll().isEmpty());
    }


    @Test
    void testDeleteBook() {
        Book book = new Book("42","Good Book","Surya","The characteristics of someone or something",1L,1L);
        doNothing().when(bookRepository).delete((Book) any());
        when(bookRepository.findByBookId((String) any())).thenReturn(book);
        bookDao.deleteBook("Bookid");
        verify(bookRepository).findByBookId((String) any());
        verify(bookRepository).delete((Book) any());
        assertTrue(bookDao.findAll().isEmpty());
    }


    @Test
    void testAddBook() {
        Book book = new Book("42","Good Book","Surya","The characteristics of someone or something",1L,1L);
        when(bookRepository.save((Book) any())).thenReturn(book);

        Book book1 = new Book("42","Good Book","Surya","The characteristics of someone or something",1L,1L);
        bookDao.addBook(book1);
        verify(bookRepository).save((Book) any());
        assertEquals("Surya", book1.getAuthor());
        assertEquals("Good Book", book1.getTitle());
        assertEquals(1L, book1.getQuantity().longValue());
        assertEquals(1L, book1.getPrice().longValue());
        assertEquals("The characteristics of someone or something", book1.getDescription());
        assertEquals("42", book1.getBookId());
        assertTrue(bookDao.findAll().isEmpty());
    }
}

