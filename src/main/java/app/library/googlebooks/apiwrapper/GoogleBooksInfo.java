package app.library.googlebooks.apiwrapper;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown=true)
@Getter
@Setter
public class GoogleBooksInfo {

    private String title;
    private String[] authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private int pageCount;
    private String printType;
    private String[] categories;
    private Map<String, String> imageLinks = new HashMap<>();
    private String language;



}