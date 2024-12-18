package search;
import books.Book;

import java.util.List;

public interface SearchStrategy {
    List<Book> search(List<Book> books, String query);
/*    List<Book> sort(List<Book> books, String criteria);*/
}
