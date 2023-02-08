package app.library.book.Entity;

import app.library.common.exceptions.GoogleBooksMissingException;
import app.library.googlebooks.apiwrapper.GoogleBooksWrapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Document(collection = "lib")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @MongoId
    @Field("_id")
    String bookId;

    @Field(name="title")
    String title;

    @Field(name="author")
    String author;

    @Field(name="description")
    String description;

    @Field(name="price")
    Long price;

    @Field(name="quantity")
    Long quantity;


    public Book(GoogleBooksWrapper googleBooksWrapper) throws Exception {
        try {
            this.title=googleBooksWrapper.getVolumeInfo().getTitle();
            this.description=googleBooksWrapper.getVolumeInfo().getDescription();
            this.author=googleBooksWrapper.getVolumeInfo().getAuthors()[0];
        }
        catch (Exception err){
            throw new GoogleBooksMissingException("values are missing from the book");
        }
    }


}
