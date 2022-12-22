package app.library.book.googlebooks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GoogleBooksWrapper {

    private GoogleBooksInfo volumeInfo;

    public GoogleBooksInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(GoogleBooksInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }


}