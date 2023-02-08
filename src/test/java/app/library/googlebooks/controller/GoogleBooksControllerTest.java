package app.library.googlebooks.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import app.library.book.Entity.Book;
import app.library.googlebooks.service.GoogleBooksServiceImpl;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {GoogleBooksController.class})
@ExtendWith(SpringExtension.class)
class GoogleBooksControllerTest {
    @Autowired
    private GoogleBooksController googleBooksController;

    @MockBean
    private GoogleBooksServiceImpl googleBooksServiceImpl;

    @Test
    void testGetgooglebooks() throws Exception {
        when(googleBooksServiceImpl.getBooks((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/googlebooks/{id}", "42");
        MockMvcBuilders.standaloneSetup(googleBooksController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testGetgooglebooks2() throws Exception {
        Book book = new Book();
        book.setAuthor("JaneDoe");
        book.setBookId("42");
        book.setDescription("The characteristics of someone or something");
        book.setPrice(1L);
        book.setQuantity(1L);
        book.setTitle("Dr");

        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book);
        when(googleBooksServiceImpl.getBooks((String) any())).thenReturn(bookList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/googlebooks/{id}", "42");
        MockMvcBuilders.standaloneSetup(googleBooksController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"bookId\":\"42\",\"title\":\"Dr\",\"author\":\"JaneDoe\",\"description\":\"The characteristics of someone or"
                                        + " something\",\"price\":1,\"quantity\":1}]"));
    }
}

