package cart;

import books.Book;
import orders.Order;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static Cart instance;
    private List<Book> books;

    public Cart() {
        this.books = new ArrayList<>();
    }

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Added book: " + book.getTitle());
    }

    public void removeBook(Book book) {
        if (books.remove(book)) {
            System.out.println("Removed book: " + book.getTitle());
        } else {
            System.out.println("Book not found in the cart.");
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public boolean isEmpty() {
        return books.isEmpty();
    }

    public Order checkout() {
        if (books.isEmpty()) {
            System.out.println("Cart is empty. Cannot proceed to checkout.");
            return null;
        }

        Order order = new Order(new ArrayList<>(books));  // Create order from cart items
        System.out.println("Cart checked out successfully. Order placed.");
        return order; // Don't clear the cart yet, only when order is confirmed
    }

    public void clearCart() {
        books.clear();
        System.out.println("Cart cleared.");
    }
}
