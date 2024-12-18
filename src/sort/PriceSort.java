package sort;

import books.Book;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PriceSort implements SortStrategy {
    @Override
    public List<Book> sort(List<Book> books) {
        return books.stream()
                .sorted(Comparator.comparingDouble(Book::getPrice))
                .collect(Collectors.toList());
    }
}
