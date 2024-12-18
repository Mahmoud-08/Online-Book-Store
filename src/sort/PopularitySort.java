package sort;
import books.Book;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PopularitySort implements SortStrategy {
    @Override
    public List<Book> sort(List<Book> books) {
        return books.stream()
                .sorted(Comparator.comparingInt(Book::getPopularity).reversed())
                .collect(Collectors.toList());
    }
}