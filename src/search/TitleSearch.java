package search;
import books.Book;

import java.util.List;
import java.util.stream.Collectors;

public class TitleSearch implements SearchStrategy {
    @Override
    public List<Book> search(List<Book> books, String query) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

/*    @Override
    public List<Book> sort(List<Book> books, String criteria) {
        return books.stream()
                .sorted((book1, book2) -> book1.getTitle().compareTo(book2.getTitle()))
                .collect(Collectors.toList());
    }*/
}

