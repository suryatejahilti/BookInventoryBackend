package app.library.googlebooks.apiwrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
@Getter
@Setter
public class GoogleBooks {

    private int totalItems;
    private List<GoogleBooksWrapper> items;

}