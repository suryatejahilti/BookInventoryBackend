package app.library.book;

import app.library.googlebooks.GoogleBooks;
import app.library.googlebooks.GoogleBooksWrapper;
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
    long price;

    @Field(name="quantity")
    long quantity;

    public Book(GoogleBooksWrapper googleBooksWrapper) throws Exception {
        try {
            this.title=googleBooksWrapper.getVolumeInfo().getTitle();
            this.description=googleBooksWrapper.getVolumeInfo().getDescription();
            this.author=googleBooksWrapper.getVolumeInfo().getAuthors()[0];
        }
        catch (Exception err){
            throw new Exception("values are missing from the book",err);
        }
    }
}
