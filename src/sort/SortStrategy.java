package sort;

import books.Book;
import java.util.List;

public interface SortStrategy {
    List<Book> sort(List<Book> books);
}

