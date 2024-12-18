package orders;

import books.Book;
import user.Customer;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Order {
    private List<Book> books;
    private OrderStatus status;
    private Customer customer;
    private Map<Book, String> reviews; // Map to store reviews for books

    public Order(List<Book> books) {
        this.books = books;
        this.status = OrderStatus.PENDING; // Initial status
        this.reviews = new HashMap<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public OrderStatus getStatus() {
        return status;
    }
    public Customer getCustomer() {
        return customer;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void updateStatus(OrderStatus status) {
        this.status = status;
    }

    public void addReview(Book book, String review) {
        reviews.put(book, review);
    }

    public String getReview(Book book) {
        return reviews.get(book);
    }

    public double getTotalCost() {
        double total = 0;
        for (Book book : books) {
            total += book.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "books=" + books +
                ", status=" + status +
                '}';
    }
}
