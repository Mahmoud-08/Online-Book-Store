package sort;
import books.Book;
import java.util.List;

public class BookSorter {
    private SortStrategy strategy;

    public void setSortStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Book> sortBooks(List<Book> books) {
        if (strategy == null) {
            throw new IllegalStateException("SortStrategy is not set");
        }
        return strategy.sort(books);
    }
}

