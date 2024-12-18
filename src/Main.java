import books.Book;
import orders.NotificationService;
import sort.*;
import filter.CategoryFilter;
import inventory.InventoryManager;
import search.TitleSearch;
import sort.*;
import user.Admin;
import user.Customer;
import cart.Cart;
import orders.Order;
import orders.OrderStatus;
import orders.OrderHistory;
import reviews.Review;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create books manually
        Book book1 = new Book("Java Programming", "Author A", 25.99, 10, "IT", 50);
        Book book2 = new Book("Java", "Author B", 30.99, 5, "History", 80);
        Book book3 = new Book("Advanced Java", "Author C", 35.99, 8, "IT", 40);

        // Create Admin and Customer
        Admin admin = new Admin("admin", "admin");
        Customer customer = new Customer("m", "m");  // Ensure customer object is created properly

        // Admin adds books to inventory
        InventoryManager inventoryManager = admin.getInventoryManager();
        inventoryManager.addBook(book1);
        inventoryManager.addBook(book2);
        inventoryManager.addBook(book3);

        // Create OrderHistory to manage past orders
        OrderHistory orderHistory = new OrderHistory();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nWelcome to the Online Bookstore");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as Customer");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int userType = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (userType == 1) {
                // Admin login
                System.out.print("Enter admin username: ");
                String username = scanner.nextLine();
                System.out.print("Enter admin password: ");
                String password = scanner.nextLine();

                if (admin.validatePassword(password)) {
                    System.out.println("\nWelcome, Admin!");
                    adminMenu(scanner, inventoryManager);
                } else {
                    System.out.println("Invalid credentials!");
                }

            } else if (userType == 2) {
                // Customer login
                System.out.print("Enter customer username: ");
                String username = scanner.nextLine();
                System.out.print("Enter customer password: ");
                String password = scanner.nextLine();

                if (customer.validatePassword(password)) {
                    System.out.println("\nWelcome, Customer!");
                    customerMenu(scanner, inventoryManager, customer, orderHistory);  // Pass the orderHistory
                } else {
                    System.out.println("Invalid credentials!");
                }

            } else if (userType == 3) {
                System.out.println("Exiting... Thank you for visiting!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void adminMenu(Scanner scanner, InventoryManager inventoryManager) {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. View Inventory");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Books in Inventory:");
                    inventoryManager.getInventory().values().forEach(System.out::println);
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void customerMenu(Scanner scanner, InventoryManager inventoryManager, Customer customer, OrderHistory orderHistory) {
        Cart cart = Cart.getInstance(); // Singleton cart
        Order order = null; // Order will be created after confirming

        while (true) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. Browse All Books");
            System.out.println("2. Search Books by Title or Author");
            System.out.println("3. Filter Books by Category");
            System.out.println("4. Sort Books by Price or Popularity");
            System.out.println("5. Add Book to Cart");
            System.out.println("6. View Cart");
            System.out.println("7. Confirm Order");
            System.out.println("8. View and Cancel Order");
            System.out.println("9. Proceed to Checkout");
            System.out.println("10. View Order History");
            System.out.println("11. Leave Review for Purchased Book");
            System.out.println("12. View Review History");
            System.out.println("13. Notify Customer with Order Status");
            System.out.println("14. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Browse all books
                    System.out.println("Books in Inventory:");
                    inventoryManager.getInventory().values().forEach(System.out::println);
                    break;
                case 2:
                    // Search by title or author
                    System.out.print("Enter title or author to search: ");
                    String query = scanner.nextLine();
                    TitleSearch searchHandler = new TitleSearch();
                    List<Book> searchResults = searchHandler.search(
                            inventoryManager.getInventory().values().stream().toList(), query);
                    if (searchResults.isEmpty()) {
                        System.out.println("No books found.");
                    } else {
                        System.out.println("Search Results:");
                        searchResults.forEach(System.out::println);
                    }
                    break;
                case 3:
                    // Filter by category
                    System.out.print("Enter category to filter by: ");
                    String category = scanner.nextLine();
                    CategoryFilter filter = new CategoryFilter();
                    List<Book> filteredBooks = filter.filter(
                            inventoryManager.getInventory().values().stream().toList(), category);
                    if (filteredBooks.isEmpty()) {
                        System.out.println("No books found in this category.");
                    } else {
                        System.out.println("Filtered Books:");
                        filteredBooks.forEach(System.out::println);
                    }
                    break;
                case 4:
                    // Sort by price or popularity
                    System.out.print("Sort by (1: Price, 2: Popularity): ");
                    int sortChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    BookSorter sorter = new BookSorter();

                    if (sortChoice == 1) {
                        sorter.setSortStrategy(new PriceSort());
                    } else if (sortChoice == 2) {
                        sorter.setSortStrategy(new PopularitySort());
                    } else {
                        System.out.println("Invalid choice.");
                        break;
                    }

                    List<Book> sortedBooks = sorter.sortBooks(
                            inventoryManager.getInventory().values().stream().toList());
                    System.out.println("Sorted Books:");
                    sortedBooks.forEach(System.out::println);
                    break;
                case 5:
                    // Add Book to Cart
                    System.out.print("Enter the book title to add to cart: ");
                    String bookTitle = scanner.nextLine();
                    Book bookToAdd = inventoryManager.getInventory().values().stream()
                            .filter(book -> book.getTitle().equalsIgnoreCase(bookTitle))
                            .findFirst().orElse(null);
                    if (bookToAdd != null) {
                        cart.addBook(bookToAdd);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 6:
                    // View Cart
                    if (cart.isEmpty()) {
                        System.out.println("Your cart is empty! Add books to the cart first.");
                    }else {
                        System.out.println("Your Cart:");
                        cart.getBooks().forEach(System.out::println);
                    }

                    break;
                case 7:
                    // Confirm Order
                    if (cart.isEmpty()) {
                        System.out.println("Your cart is empty! Add books to the cart first.");
                    } else {
                        order = cart.checkout(); // Create order from cart
                        if (order != null) {
                            System.out.println("Order confirmed!");
                            System.out.println("Your Order:");
                            order.getBooks().forEach(System.out::println);
                            System.out.println("Total Cost: " + order.getTotalCost());
                        }
                    }
                    break;
                case 8:
                    // View and Cancel Order
                    if (order != null) {
                        if (order.getStatus() == OrderStatus.PENDING) {
                            System.out.println("Your current order:");
                            order.getBooks().forEach(System.out::println);
                            System.out.println("Total Cost: " + order.getTotalCost());
                            System.out.print("Do you want to cancel this order? (y/n): ");
                            String cancelChoice = scanner.nextLine();
                            if (cancelChoice.equalsIgnoreCase("y")) {
                                order.updateStatus(OrderStatus.CANCELLED);
                                cart.clearCart();
                                System.out.println("Order cancelled.");
                            }
                        } else {
                            System.out.println("The order cannot be cancelled as it is not pending.");
                        }
                    } else {
                        System.out.println("You have no active order to view or cancel.");
                    }
                    break;
                case 9:
                    // Proceed to Checkout
                    if (order != null && order.getStatus() == OrderStatus.PENDING) {
                        System.out.println("Proceeding to Checkout...");
                        System.out.println("Order Details:");
                        order.getBooks().forEach(System.out::println);
                        System.out.println("Total Cost: " + order.getTotalCost());
                        System.out.println("Confirm the order for checkout (y/n): ");
                        String checkoutChoice = scanner.nextLine();
                        if (checkoutChoice.equalsIgnoreCase("y")) {
                            order.updateStatus(OrderStatus.COMPLETED);  // Update status to COMPLETED
                            System.out.println("Order successfully completed. Thank you!");
                            // Add the completed order to the OrderHistory (now tracking purchases)
                            cart.clearCart();
                            orderHistory.addOrder(order); // Add to Order History (now tracking completed purchases)
                        } else {
                            System.out.println("Order not confirmed. Returning to main menu.");
                        }
                    } else {
                        System.out.println("Please confirm the order first before proceeding to checkout.");
                    }
                    break;

                case 10:
                    // View Purchase History
                    if (order != null && order.getStatus() == OrderStatus.COMPLETED) {
                        System.out.println("Your completed orders:");
                        List<Book> purchasedBooks = order.getBooks();
                        purchasedBooks.forEach(System.out::println);
                    } else {
                        System.out.println("You must complete an order to view purchase history.");
                    }
                    break;

                case 11:
                    // Leave Review for Purchased Book
                    if (order != null && order.getStatus() == OrderStatus.COMPLETED) {
                        System.out.println("Your completed orders:");
                        List<Book> purchasedBooks = order.getBooks();
                        for (int i = 0; i < purchasedBooks.size(); i++) {
                            System.out.println((i + 1) + ". " + purchasedBooks.get(i).getTitle());
                        }

                        System.out.print("Enter the number of the book you want to review: ");
                        int bookChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (bookChoice < 1 || bookChoice > purchasedBooks.size()) {
                            System.out.println("Invalid choice. Please select a valid book.");
                            break;
                        }

                        Book bookToReview = purchasedBooks.get(bookChoice - 1);
                        System.out.print("Enter rating (1-5): ");
                        int rating = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (rating < 1 || rating > 5) {
                            System.out.println("Invalid rating. Please enter a rating between 1 and 5.");
                            break;
                        }

                        System.out.print("Enter your review: ");
                        String reviewText = scanner.nextLine();

                        Review review = new Review(bookToReview, reviewText, rating, customer);
                        bookToReview.addReview(review);
                        System.out.println("Review added successfully!");
                    } else {
                        System.out.println("You must complete an order before leaving a review.");
                    }
                    break;

                case 12:
                    // View Review History
                    if (order != null && order.getStatus() == OrderStatus.COMPLETED) {
                        System.out.println("Your review history:");
                        List<Book> purchasedBooks = order.getBooks();
                        boolean foundReviews = false;

                        for (Book book : purchasedBooks) {
                            List<Review> reviews = book.getReviews();
                            for (Review review : reviews) {
                                if (review.getCustomer() != null && review.getCustomer().equals(customer)) {
                                    System.out.println("Book: " + book.getTitle());
                                    System.out.println("Rating: " + review.getStarRating());
                                    System.out.println("Review: " + review.getReviewText());
                                    System.out.println("-----------------------------");
                                    foundReviews = true;
                                }
                            }
                        }

                        if (!foundReviews) {
                            System.out.println("You have not left any reviews yet.");
                        }
                    } else {
                        System.out.println("You must complete an order before viewing your review history.");
                    }
                    break;

                case 13:
                    // Notify customer with order status update
                    if (order != null) {
                        NotificationService.notifyCustomer(order);  // Notify the customer
                    } else {
                        System.out.println("You must have an order to get the notification.");
                    }
                    break;

                case 14:
                    // Exit
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}
