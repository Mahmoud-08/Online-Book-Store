package user;

import books.Book;
import cart.Cart;
import orders.Order;
import orders.OrderHistory;
import reviews.Review;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private Cart cart;
    private OrderHistory orderHistory;

    public Customer(String username, String password) {
        super(username, password);
        this.cart = new Cart();
        this.orderHistory = new OrderHistory(); // Initialize order history
    }

    // Getter for Cart
    public Cart getCart() {
        return cart;
    }

    // Add an order to the customer's order history
    public void addOrder(Order order) {
        orderHistory.addOrder(order);
    }

    // Get all orders from order history
    public List<Order> getOrderHistory() {
        return orderHistory.getOrders(this); // Get orders specific to this customer
    }

    // Get all reviews from books in customer's order history
    public List<Review> getReviewHistory() {
        List<Review> reviewHistory = new ArrayList<>();
        // Loop through orders and collect reviews from the books
        for (Order order : orderHistory.getOrders(this)) {
            for (Book book : order.getBooks()) {
                reviewHistory.addAll(book.getReviews()); // Collect reviews from each book
            }
        }
        return reviewHistory;
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Customer");
    }
}
