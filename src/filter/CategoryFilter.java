package filter;
import books.Book;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryFilter {
    public List<Book> filter(List<Book> books, String category) {
        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}

