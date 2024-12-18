package books;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private List<Category> subcategories;
    private List<Book> books;

    public Category(String name) {
        this.name = name;
        this.subcategories = new ArrayList<>();
        this.books = new ArrayList<>();
    }

    public void addSubcategory(Category category) {
        subcategories.add(category);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }

    public String getName() {
        return name;
    }
}
