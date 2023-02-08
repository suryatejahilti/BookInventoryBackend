package app.library.book.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import app.library.book.Entity.Book;
import app.library.book.models.BookIdRequest;
import app.library.book.models.BookRequest;
import app.library.book.service.BookServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {BookController.class})
@ExtendWith(SpringExtension.class)
class BookControllerTest {
    @Autowired
    private BookController bookController;

    @MockBean
    private BookServiceImpl bookServiceImpl;


    @Test
    void testGetAllBooks() throws Exception {
        when(bookServiceImpl.getAllBooks()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books");
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testGetBookById() throws Exception {
        Book book = new Book("42", "Good Book", "Surya", "The characteristics of someone or something", 1L, 1L);
        when(bookServiceImpl.getBookById((BookIdRequest) any())).thenReturn(book);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/{bookId}/{user}", "42", "User");
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"bookId\":\"42\",\"title\":\"Good Book\",\"author\":\"Surya\",\"description\":\"The characteristics of someone or"
                                        + " something\",\"price\":1,\"quantity\":1}"));
    }


    @Test
    void testAddBook() throws Exception {
        doNothing().when(bookServiceImpl).addBook((BookRequest) any());

        Book book = new Book("42", "Good Book", "Surya", "The characteristics of someone or something", 1L, 1L);

        BookRequest bookRequest = new BookRequest();
        bookRequest.setBook(book);
        bookRequest.setUser("User");
        String content = (new ObjectMapper()).writeValueAsString(bookRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void testUpdateBook() throws Exception {
        doNothing().when(bookServiceImpl).updateBook((BookRequest) any());

        Book book = new Book("42", "Good Book", "Surya", "The characteristics of someone or something", 1L, 1L);

        BookRequest bookRequest = new BookRequest();
        bookRequest.setBook(book);
        bookRequest.setUser("User");
        String content = (new ObjectMapper()).writeValueAsString(bookRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteBook() throws Exception {
        doNothing().when(bookServiceImpl).deleteBook((BookIdRequest) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/books/{bookId}/{user}", "42",
                "User");
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void testGetStrings() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/booked");
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Hi"));
    }


    @Test
    void testGetStrings2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/booked");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Hi"));
    }
}

