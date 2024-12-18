package search;
import books.Book;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorSearch implements SearchStrategy {
    @Override
    public List<Book> search(List<Book> books, String query) {
        return books.stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

/*    @Override
    public List<Book> sort(List<Book> books, String criteria) {
        return books.stream()
                .sorted((book1, book2) -> book1.getAuthor().compareTo(book2.getAuthor()))
                .collect(Collectors.toList());
    }*/
}
