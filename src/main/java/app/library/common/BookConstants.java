package app.library.common;

public class BookConstants {

    public static final String TOPIC_NAME="books";
    public static final String GOOGLE_BOOKS_API="https://www.googleapis.com/books/v1/volumes?q=";
    public static final String GOOGLE_BOOKS_API_MAX_RESULTS="&maxResults=";

    public static final String GOOGLE_BOOKS_API_EXCEPTION="Could not Reach Google Servers";

    public static final String INVALID_BOOK_DETAILS="Invalid Book Details";

    private BookConstants() {
    }
}
