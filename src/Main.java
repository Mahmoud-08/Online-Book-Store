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
import discounts.*;

import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //TODO: SEPERATE MAIN FUNCTION INTO CLASSES TO BE READABLE
        //TODO: FIX SEARCHING BOOKS BY AUTHOR
        //TODO: FIX CATEGORIES IN ADMIN
        //TODO: ADMIN CONFIRMATION AND MANAGEMENT OF ORDERS

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
                    adminMenu(scanner, inventoryManager, orderHistory);
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

    private static void adminMenu(Scanner scanner, InventoryManager inventoryManager,OrderHistory orderHistory) {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. View Inventory");
            System.out.println("2. Add books");
            System.out.println("3. Edit books");
            System.out.println("4. Delete books");
            System.out.println("5. Add categories");
            System.out.println("6. Edit categories");
            System.out.println("7. Delete categories");
            System.out.println("8. Manage orders");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Books in Inventory:");
                    inventoryManager.getInventory().values().forEach(System.out::println);
                    break;
                case 2: {


                    System.out.println("Enter book name:");
                    String title = scanner.nextLine();

                    System.out.println("Enter book price");
                    int price = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter book author");
                    String author = scanner.nextLine();
                    System.out.println("Enter the books' stock");
                    int stock = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter the book's popularity");
                    int popularity = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter the book's category");
                    String category = scanner.nextLine();
                    Book newBook = new Book(title, author, price, stock, category, popularity);
                    inventoryManager.addBook(newBook);

                    break;
                }
                case 3:
                    System.out.println("List of books:");
                    inventoryManager.getInventory().values().forEach(System.out::println);

                    System.out.println("Enter book title to edit");
                    String bookTitle = scanner.nextLine();

                    if (!inventoryManager.getInventory().containsKey(bookTitle)) {
                        System.out.println("Book not found.");
                    } else {
                        System.out.println("Select the attribute to edit:");
                        System.out.println("1. title\n" +
                                "2. category\n" +
                                "3. stock\n" +
                                "4. author\n" +
                                "5. price\n" +
                                "6. popularity");
                        int newChoice = scanner.nextInt();

                        String attribute = "";
                        String newValue = "";

                        switch (newChoice) {
                            case 1:
                                attribute = "title";
                                System.out.println("Enter new title:");
                                newValue = scanner.nextLine();
                                break;
                            case 2:
                                attribute = "category";
                                System.out.println("Enter new category:");
                                newValue = scanner.nextLine();
                                break;
                            case 3:
                                attribute = "stock";
                                System.out.println("Enter new stock:");
                                newValue = String.valueOf(scanner.nextInt());
                                break;
                            case 4:
                                attribute = "author";
                                System.out.println("Enter new author:");
                                newValue = scanner.nextLine();
                                break;
                            case 5:
                                attribute = "price";
                                System.out.println("Enter new price:");
                                newValue = String.valueOf(scanner.nextInt());
                                break;
                            case 6:
                                attribute = "popularity";
                                System.out.println("Enter new popularity:");
                                newValue = String.valueOf(scanner.nextInt());
                                break;
                            default:
                                System.out.println("Invalid choice.");
                                break;
                        }

                        if (!attribute.isEmpty() && !newValue.isEmpty()) {
                            boolean success = inventoryManager.editBook(bookTitle, attribute, newValue);
                            if (success) {
                                System.out.println("Book updated successfully.");
                            } else {
                                System.out.println("Failed to update the book.");
                            }
                        }
                    }
                    break;
                case 4: {
                    System.out.println("Enter book title to be deleted");
                    String title = scanner.nextLine();
                    if(inventoryManager.deleteBook(title))
                        System.out.println("Book successfully got deleted");
                    else
                        System.out.println("Book failed to be deleted");
                }
                case 8:
                    manageOrders(scanner, orderHistory);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageOrders(Scanner scanner, OrderHistory orderHistory) {
        while (true) {
            System.out.println("\nOrder Management:");
            System.out.println("1. View All Orders");
            System.out.println("2. Confirm Order");
            System.out.println("3. Cancel Order");
            System.out.println("4. Exit to Admin Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("All Orders:");
                    List<Order> orders = orderHistory.getOrders();
                    if (orders.isEmpty()) {
                        System.out.println("No orders available.");
                    } else {
                        for (Order order : orders) {
                            System.out.println(order);
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter Order ID to confirm: ");
                    int confirmOrderId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Order orderToConfirm = orderHistory.getOrderById(confirmOrderId);
                    if (orderToConfirm != null) {
                        orderToConfirm.updateStatus(OrderStatus.CONFIRMED);
                        orderHistory.addOrder(orderToConfirm);
                        System.out.println("Order confirmed and moved to history.");
                    } else {
                        System.out.println("Order not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Order ID to cancel: ");
                    int cancelOrderId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Order orderToCancel = orderHistory.getOrderById(cancelOrderId);
                    if (orderToCancel != null) {
                        orderToCancel.updateStatus(OrderStatus.CANCELLED);
                        System.out.println("Order canceled.");
                    } else {
                        System.out.println("Order not found.");
                    }
                    break;
                case 4:
                    return; // Exit to Admin Menu
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
                case 1:{
                    // Browse all books
                    System.out.println("Books in Inventory:");
                    inventoryManager.getInventory().values().forEach(System.out::println);
                    break;}
                case 2:{
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
                    break;}
                case 3:{
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
                    break;}
                case 4:{
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
                    break;}
                case 5:{
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
                    break;}
                case 6:{
                    // View Cart
                    if (cart.isEmpty()) {
                        System.out.println("Your cart is empty! Add books to the cart first.");
                    }else {
                        System.out.println("Your Cart:");
                        cart.getBooks().forEach(System.out::println);
                    }

                    break;}
                case 7:{
                    // Confirm Order
                    if (cart.isEmpty()) {
                        System.out.println("Your cart is empty! Add books to the cart first.");
                    } else {
                        order = cart.checkout(); // Create order from cart
                        if (order != null) {
                            Discount discountProxy = new DiscountProxy(); // Proxy instance for discount handling
                            boolean applyingDiscount = true;

                            while (applyingDiscount) {
                                System.out.println("Do you want to use a discount code? (y/n):");
                                String response = scanner.nextLine().trim().toLowerCase();

                                if (response.equals("y")) {
                                    System.out.println("Enter your discount code:");
                                    String discountCode = scanner.nextLine();

                                    double discountedTotal = discountProxy.applyDiscount(order.getTotalCost(), discountCode);

                                    if (discountedTotal < order.getTotalCost()) {
                                        order.setTotalCost(discountedTotal);
                                        System.out.println("Discount applied successfully!");
                                        applyingDiscount = false; // Exit the loop
                                    } else {
                                        System.out.println("Invalid discount code. Please try again.");
                                    }
                                } else if (response.equals("n")) {
                                    applyingDiscount = false; // Exit the loop
                                } else {
                                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
                                }
                            }

                            System.out.println("Order confirmed!");
                            System.out.println("Your Order:");
                            order.getBooks().forEach(System.out::println);
                            System.out.println("Total Cost: " + order.getTotalCost() + "$");

                            order.setStatus(OrderStatus.PENDING);
                        }
                    }
                    break;}

                case 8:{
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
                    break;}
                case 9:{
                    // Proceed to Checkout
                    if (order != null && order.getStatus() == OrderStatus.PENDING) {
                        System.out.println("Proceeding to Checkout...");
                        System.out.println("Order Details:");
                        order.getBooks().forEach(System.out::println);
                        System.out.println("Total Cost: " + order.getTotalCost());
                        System.out.println("Confirm the order for checkout (y/n): ");
                        String checkoutChoice = scanner.nextLine();
                        if (checkoutChoice.equalsIgnoreCase("y")) {/*
                            order.updateStatus(OrderStatus.COMPLETED);  // Update status to COMPLETED*/
                            System.out.println("Order successfully completed. Thank you!");
                            // Add the completed order to the OrderHistory (now tracking purchases)
                            cart.clearCart();
                            orderHistory.addOrder(order);
                            order = null;// Add to Order History (now tracking completed purchases)
                        } else {
                            System.out.println("Order not confirmed. Returning to main menu.");
                        }
                    } else {
                        System.out.println("Please confirm the order first before proceeding to checkout.");
                    }
                    break;}

                case 10:{
                    orderHistory.getOrders().forEach(System.out::println);
                    break;}

                // Case 11: Leave Review for Purchased Book from Order History
                case 11: {
                    // Get all orders for the customer
                    List<Order> customerOrders = orderHistory.getOrders();
                    if (!customerOrders.isEmpty()) {
                        System.out.println("Your purchased books from all orders:");

                        // Collect all the books from the order history (ensure no duplicates)
                        Set<Book> reviewableBooks = new HashSet<>();
                        for (Order ord : customerOrders) {
                            List<Book> purchasedBooks = ord.getBooks();
                            reviewableBooks.addAll(purchasedBooks); // Add books to the reviewable set
                        }

                        // Display the books and allow the customer to choose which to review
                        int bookIndex = 1;
                        for (Book book : reviewableBooks) {
                            System.out.println(bookIndex + ". " + book.getTitle());
                            bookIndex++;
                        }

                        System.out.print("Enter the index of the book you want to review: ");
                        int chosenIndex = scanner.nextInt() - 1;  // User enters index (1-based)
                        scanner.nextLine(); // Consume newline

                        // Get the chosen book
                        Book bookToReview = (Book) reviewableBooks.toArray()[chosenIndex];

                        // Check if the customer has already reviewed the book
                        boolean hasReviewed = false;
                        for (Review existingReview : bookToReview.getReviews()) {
                            if (existingReview.getCustomer().equals(customer)) {
                                hasReviewed = true;
                                break;  // Stop once we find a review by the same customer
                            }
                        }

                        if (hasReviewed) {
                            System.out.println("You have already reviewed this book.");
                        } else {
                            // Ask for the review rating
                            System.out.print("Enter rating (1-5): ");
                            int rating = scanner.nextInt();
                            scanner.nextLine();  // Consume newline

                            // Validate the rating
                            if (rating < 1 || rating > 5) {
                                System.out.println("Invalid rating. Please enter a rating between 1 and 5.");
                            } else {
                                // Ask for the review text
                                System.out.print("Enter your review text: ");
                                String reviewText = scanner.nextLine();

                                // Create and add the review
                                Review review = new Review(bookToReview, reviewText, rating, customer);
                                bookToReview.addReview(review);

                                System.out.println("Review successfully added for: " + bookToReview.getTitle());
                            }
                        }
                    } else {
                        System.out.println("You have no completed orders to review.");
                    }
                    break;
                }

                // Case 12: View Review History
                case 12: {
                    // Get all orders for the customer
                    List<Order> customerOrders = orderHistory.getOrders();
                    if (!customerOrders.isEmpty()) {
                        System.out.println("Your review history:");

                        boolean foundReviews = false;

                        // Iterate through each order and its books
                        for (Order ord : customerOrders) {
                            List<Book> purchasedBooks = ord.getBooks();

                            // Check each book for reviews
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
                        }

                        if (!foundReviews) {
                            System.out.println("You have not left any reviews yet.");
                        }
                    } else {
                        System.out.println("You have no completed orders to view reviews.");
                    }
                    break;
                }


                case 13:{
                    // Notify customer with order status update
                    if (order != null) {
                        NotificationService.notifyCustomer(order);  // Notify the customer
                    } else {
                        System.out.println("You must have an order to get the notification.");
                    }
                    break;}
                case 14:{
                    // Exit
                    System.out.println("Exiting...");
                    return;}
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}
