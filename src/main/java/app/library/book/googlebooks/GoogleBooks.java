package app.library.book.googlebooks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GoogleBooks {

    private int totalItems;
    private List<GoogleBooksWrapper> items;


    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public List<GoogleBooksWrapper> getItems() {
        return items;
    }

    public void setItems(List<GoogleBooksWrapper> items) {
        this.items = items;
    }


}