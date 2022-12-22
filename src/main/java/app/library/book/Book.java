package app.library.book;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@Table(name="lib")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long bookid;
    @Column(name="title")
    String title;
    @Column(name="author")
    String author;
    @Column(name="description")
    String description;
    @Column(name="price")
    long price;
    @Column(name="quantity")
    long quantity;

    public String getTitle() {
        return title;
    }

    public long getBookid() {
        return bookid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
