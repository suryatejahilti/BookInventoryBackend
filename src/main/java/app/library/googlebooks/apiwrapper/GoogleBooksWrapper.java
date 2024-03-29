package app.library.googlebooks.apiwrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown=true)
@Getter
@Setter
public class GoogleBooksWrapper {

    private GoogleBooksInfo volumeInfo;

}