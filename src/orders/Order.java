package orders;

import books.Book;
import user.Customer;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Order {
    private int Id;
    private static int IdCounter = 0;
    private List<Book> books;
    private OrderStatus status;
    private Customer customer;
    private Map<Book, String> reviews; // Map to store reviews for books
    private double totalCost; // Field to store total cost (with discounts if applicable)

    public Order(List<Book> books) {
        this.Id = ++IdCounter;
        this.books = books;
        this.status = OrderStatus.PENDING; // Initial status
        this.reviews = new HashMap<>();
        this.totalCost = calculateTotalCost(); // Initialize total cost
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
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost; // Update total cost
    }

    private double calculateTotalCost() {
        double total = 0;
        for (Book book : books) {
            total += book.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "ID=" + Id +
                ", books=" + books +
                ", status=" + status +
                ", totalCost=" + totalCost +
                '}';
    }

    public int getId() {
        return Id;
    }
}
