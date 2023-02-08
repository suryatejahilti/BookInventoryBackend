package app.library.googlebooks.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import app.library.googlebooks.apiwrapper.GoogleBooks;
import app.library.googlebooks.apiwrapper.GoogleBooksInfo;
import app.library.googlebooks.apiwrapper.GoogleBooksWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(classes = {GoogleBooksServiceImpl.class})
@ExtendWith(SpringExtension.class)
class GoogleBooksServiceImplTest {
    @Autowired
    private GoogleBooksServiceImpl googleBooksServiceImpl;

    @MockBean
    private RestTemplate restTemplate;


    @Test
    void testGetBooks() throws Exception {
        GoogleBooks googleBooks = new GoogleBooks();
        googleBooks.setItems(new ArrayList<>());
        googleBooks.setTotalItems(1000);
        when(restTemplate.getForObject((String) any(), (Class<GoogleBooks>) any(), (Object[]) any()))
                .thenReturn(googleBooks);
        assertTrue(googleBooksServiceImpl.getBooks("Searchid").isEmpty());
        verify(restTemplate).getForObject((String) any(), (Class<GoogleBooks>) any(), (Object[]) any());
    }


    @Test
    void testGetBooks2() throws Exception {
        GoogleBooksInfo googleBooksInfo = new GoogleBooksInfo();
        googleBooksInfo.setAuthors(new String[]{"JaneDoe"});
        googleBooksInfo.setCategories(new String[]{"Categories"});
        googleBooksInfo.setDescription("The characteristics of someone or something");
        googleBooksInfo.setImageLinks(new HashMap<>());
        googleBooksInfo.setLanguage("en");
        googleBooksInfo.setPageCount(3);
        googleBooksInfo.setPrintType("Print Type");
        googleBooksInfo.setPublishedDate("2020-03-01");
        googleBooksInfo.setPublisher("Publisher");
        googleBooksInfo.setTitle("Dr");

        GoogleBooksWrapper googleBooksWrapper = new GoogleBooksWrapper();
        googleBooksWrapper.setVolumeInfo(googleBooksInfo);

        ArrayList<GoogleBooksWrapper> googleBooksWrapperList = new ArrayList<>();
        googleBooksWrapperList.add(googleBooksWrapper);

        GoogleBooks googleBooks = new GoogleBooks();
        googleBooks.setItems(googleBooksWrapperList);
        googleBooks.setTotalItems(1000);
        when(restTemplate.getForObject((String) any(), (Class<GoogleBooks>) any(), (Object[]) any()))
                .thenReturn(googleBooks);
        assertEquals(1, googleBooksServiceImpl.getBooks("Searchid").size());
        verify(restTemplate).getForObject((String) any(), (Class<GoogleBooks>) any(), (Object[]) any());
    }


    @Test
    void testGetBooks3() throws Exception {
        GoogleBooksInfo googleBooksInfo = new GoogleBooksInfo();
        googleBooksInfo.setAuthors(new String[]{"JaneDoe"});
        googleBooksInfo.setCategories(new String[]{"Categories"});
        googleBooksInfo.setDescription("The characteristics of someone or something");
        googleBooksInfo.setImageLinks(new HashMap<>());
        googleBooksInfo.setLanguage("en");
        googleBooksInfo.setPageCount(3);
        googleBooksInfo.setPrintType("Print Type");
        googleBooksInfo.setPublishedDate("2020-03-01");
        googleBooksInfo.setPublisher("Publisher");
        googleBooksInfo.setTitle("Dr");
        GoogleBooksInfo googleBooksInfo1 = mock(GoogleBooksInfo.class);
        when(googleBooksInfo1.getDescription()).thenReturn("The characteristics of someone or something");
        when(googleBooksInfo1.getTitle()).thenReturn("Dr");
        when(googleBooksInfo1.getAuthors()).thenReturn(new String[]{});
        doNothing().when(googleBooksInfo1).setAuthors((String[]) any());
        doNothing().when(googleBooksInfo1).setCategories((String[]) any());
        doNothing().when(googleBooksInfo1).setDescription((String) any());
        doNothing().when(googleBooksInfo1).setImageLinks((Map<String, String>) any());
        doNothing().when(googleBooksInfo1).setLanguage((String) any());
        doNothing().when(googleBooksInfo1).setPageCount(anyInt());
        doNothing().when(googleBooksInfo1).setPrintType((String) any());
        doNothing().when(googleBooksInfo1).setPublishedDate((String) any());
        doNothing().when(googleBooksInfo1).setPublisher((String) any());
        doNothing().when(googleBooksInfo1).setTitle((String) any());
        googleBooksInfo1.setAuthors(new String[]{"JaneDoe"});
        googleBooksInfo1.setCategories(new String[]{"Categories"});
        googleBooksInfo1.setDescription("The characteristics of someone or something");
        googleBooksInfo1.setImageLinks(new HashMap<>());
        googleBooksInfo1.setLanguage("en");
        googleBooksInfo1.setPageCount(3);
        googleBooksInfo1.setPrintType("Print Type");
        googleBooksInfo1.setPublishedDate("2020-03-01");
        googleBooksInfo1.setPublisher("Publisher");
        googleBooksInfo1.setTitle("Dr");
        GoogleBooksWrapper googleBooksWrapper = mock(GoogleBooksWrapper.class);
        when(googleBooksWrapper.getVolumeInfo()).thenReturn(googleBooksInfo1);
        doNothing().when(googleBooksWrapper).setVolumeInfo((GoogleBooksInfo) any());
        googleBooksWrapper.setVolumeInfo(googleBooksInfo);

        ArrayList<GoogleBooksWrapper> googleBooksWrapperList = new ArrayList<>();
        googleBooksWrapperList.add(googleBooksWrapper);

        GoogleBooks googleBooks = new GoogleBooks();
        googleBooks.setItems(googleBooksWrapperList);
        googleBooks.setTotalItems(1000);
        when(restTemplate.getForObject((String) any(), (Class<GoogleBooks>) any(), (Object[]) any()))
                .thenReturn(googleBooks);
        assertTrue(googleBooksServiceImpl.getBooks("Searchid").isEmpty());
        verify(restTemplate).getForObject((String) any(), (Class<GoogleBooks>) any(), (Object[]) any());
        verify(googleBooksWrapper, atLeast(1)).getVolumeInfo();
        verify(googleBooksWrapper).setVolumeInfo((GoogleBooksInfo) any());
        verify(googleBooksInfo1).getDescription();
        verify(googleBooksInfo1).getTitle();
        verify(googleBooksInfo1).getAuthors();
        verify(googleBooksInfo1).setAuthors((String[]) any());
        verify(googleBooksInfo1).setCategories((String[]) any());
        verify(googleBooksInfo1).setDescription((String) any());
        verify(googleBooksInfo1).setImageLinks((Map<String, String>) any());
        verify(googleBooksInfo1).setLanguage((String) any());
        verify(googleBooksInfo1).setPageCount(anyInt());
        verify(googleBooksInfo1).setPrintType((String) any());
        verify(googleBooksInfo1).setPublishedDate((String) any());
        verify(googleBooksInfo1).setPublisher((String) any());
        verify(googleBooksInfo1).setTitle((String) any());
    }


    @Test
    void testGetModifiedBooks() throws Exception {
        GoogleBooks googleBooks = new GoogleBooks();
        googleBooks.setItems(new ArrayList<>());
        googleBooks.setTotalItems(1000);
        assertTrue(googleBooksServiceImpl.getModifiedBooks(googleBooks).isEmpty());
    }


    @Test
    void testGetModifiedBooks2() throws Exception {
        GoogleBooks googleBooks = mock(GoogleBooks.class);
        when(googleBooks.getItems()).thenReturn(new ArrayList<>());
        doNothing().when(googleBooks).setItems((List<GoogleBooksWrapper>) any());
        doNothing().when(googleBooks).setTotalItems(anyInt());
        googleBooks.setItems(new ArrayList<>());
        googleBooks.setTotalItems(1000);
        assertTrue(googleBooksServiceImpl.getModifiedBooks(googleBooks).isEmpty());
        verify(googleBooks).getItems();
        verify(googleBooks).setItems((List<GoogleBooksWrapper>) any());
        verify(googleBooks).setTotalItems(anyInt());
    }


    @Test
    void testGetModifiedBooks3() throws Exception {
        GoogleBooksInfo googleBooksInfo = new GoogleBooksInfo();
        googleBooksInfo.setAuthors(new String[]{"JaneDoe"});
        googleBooksInfo.setCategories(new String[]{"Categories"});
        googleBooksInfo.setDescription("The characteristics of someone or something");
        googleBooksInfo.setImageLinks(new HashMap<>());
        googleBooksInfo.setLanguage("en");
        googleBooksInfo.setPageCount(3);
        googleBooksInfo.setPrintType("Print Type");
        googleBooksInfo.setPublishedDate("2020-03-01");
        googleBooksInfo.setPublisher("Publisher");
        googleBooksInfo.setTitle("Dr");

        GoogleBooksWrapper googleBooksWrapper = new GoogleBooksWrapper();
        googleBooksWrapper.setVolumeInfo(googleBooksInfo);

        ArrayList<GoogleBooksWrapper> googleBooksWrapperList = new ArrayList<>();
        googleBooksWrapperList.add(googleBooksWrapper);
        GoogleBooks googleBooks = mock(GoogleBooks.class);
        when(googleBooks.getItems()).thenReturn(googleBooksWrapperList);
        doNothing().when(googleBooks).setItems((List<GoogleBooksWrapper>) any());
        doNothing().when(googleBooks).setTotalItems(anyInt());
        googleBooks.setItems(new ArrayList<>());
        googleBooks.setTotalItems(1000);
        assertEquals(1, googleBooksServiceImpl.getModifiedBooks(googleBooks).size());
        verify(googleBooks).getItems();
        verify(googleBooks).setItems((List<GoogleBooksWrapper>) any());
        verify(googleBooks).setTotalItems(anyInt());
    }


    @Test
    void testGetModifiedBooks4() throws Exception {
        GoogleBooksInfo googleBooksInfo = new GoogleBooksInfo();
        googleBooksInfo.setAuthors(new String[]{"JaneDoe"});
        googleBooksInfo.setCategories(new String[]{"Categories"});
        googleBooksInfo.setDescription("The characteristics of someone or something");
        googleBooksInfo.setImageLinks(new HashMap<>());
        googleBooksInfo.setLanguage("en");
        googleBooksInfo.setPageCount(3);
        googleBooksInfo.setPrintType("Print Type");
        googleBooksInfo.setPublishedDate("2020-03-01");
        googleBooksInfo.setPublisher("Publisher");
        googleBooksInfo.setTitle("Dr");
        GoogleBooksInfo googleBooksInfo1 = mock(GoogleBooksInfo.class);
        when(googleBooksInfo1.getDescription()).thenReturn("The characteristics of someone or something");
        when(googleBooksInfo1.getTitle()).thenReturn("Dr");
        when(googleBooksInfo1.getAuthors()).thenReturn(new String[]{});
        doNothing().when(googleBooksInfo1).setAuthors((String[]) any());
        doNothing().when(googleBooksInfo1).setCategories((String[]) any());
        doNothing().when(googleBooksInfo1).setDescription((String) any());
        doNothing().when(googleBooksInfo1).setImageLinks((Map<String, String>) any());
        doNothing().when(googleBooksInfo1).setLanguage((String) any());
        doNothing().when(googleBooksInfo1).setPageCount(anyInt());
        doNothing().when(googleBooksInfo1).setPrintType((String) any());
        doNothing().when(googleBooksInfo1).setPublishedDate((String) any());
        doNothing().when(googleBooksInfo1).setPublisher((String) any());
        doNothing().when(googleBooksInfo1).setTitle((String) any());
        googleBooksInfo1.setAuthors(new String[]{"JaneDoe"});
        googleBooksInfo1.setCategories(new String[]{"Categories"});
        googleBooksInfo1.setDescription("The characteristics of someone or something");
        googleBooksInfo1.setImageLinks(new HashMap<>());
        googleBooksInfo1.setLanguage("en");
        googleBooksInfo1.setPageCount(3);
        googleBooksInfo1.setPrintType("Print Type");
        googleBooksInfo1.setPublishedDate("2020-03-01");
        googleBooksInfo1.setPublisher("Publisher");
        googleBooksInfo1.setTitle("Dr");
        GoogleBooksWrapper googleBooksWrapper = mock(GoogleBooksWrapper.class);
        when(googleBooksWrapper.getVolumeInfo()).thenReturn(googleBooksInfo1);
        doNothing().when(googleBooksWrapper).setVolumeInfo((GoogleBooksInfo) any());
        googleBooksWrapper.setVolumeInfo(googleBooksInfo);

        ArrayList<GoogleBooksWrapper> googleBooksWrapperList = new ArrayList<>();
        googleBooksWrapperList.add(googleBooksWrapper);
        GoogleBooks googleBooks = mock(GoogleBooks.class);
        when(googleBooks.getItems()).thenReturn(googleBooksWrapperList);
        doNothing().when(googleBooks).setItems((List<GoogleBooksWrapper>) any());
        doNothing().when(googleBooks).setTotalItems(anyInt());
        googleBooks.setItems(new ArrayList<>());
        googleBooks.setTotalItems(1000);
        assertTrue(googleBooksServiceImpl.getModifiedBooks(googleBooks).isEmpty());
        verify(googleBooks).getItems();
        verify(googleBooks).setItems((List<GoogleBooksWrapper>) any());
        verify(googleBooks).setTotalItems(anyInt());
        verify(googleBooksWrapper, atLeast(1)).getVolumeInfo();
        verify(googleBooksWrapper).setVolumeInfo((GoogleBooksInfo) any());
        verify(googleBooksInfo1).getDescription();
        verify(googleBooksInfo1).getTitle();
        verify(googleBooksInfo1).getAuthors();
        verify(googleBooksInfo1).setAuthors((String[]) any());
        verify(googleBooksInfo1).setCategories((String[]) any());
        verify(googleBooksInfo1).setDescription((String) any());
        verify(googleBooksInfo1).setImageLinks((Map<String, String>) any());
        verify(googleBooksInfo1).setLanguage((String) any());
        verify(googleBooksInfo1).setPageCount(anyInt());
        verify(googleBooksInfo1).setPrintType((String) any());
        verify(googleBooksInfo1).setPublishedDate((String) any());
        verify(googleBooksInfo1).setPublisher((String) any());
        verify(googleBooksInfo1).setTitle((String) any());
    }
}

