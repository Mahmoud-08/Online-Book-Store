package books;

import reviews.Review;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private String author;
    private double price;
    private int stock;
    private String category;
    private int popularity;
    private List<Review> reviews; // List to store reviews

    public Book(String title, String author, double price, int stock, String category, int popularity) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.popularity = popularity;
        this.reviews = new ArrayList<>(); // Initialize the reviews list
    }

    // Getter methods
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getCategory() {
        return category;
    }

    public int getPopularity() {
        return popularity;
    }

    // Add review to the book
    public void addReview(Review review) {
        reviews.add(review);
    }

    // Getter for reviews
    public List<Review> getReviews() {
        return reviews;
    }

    @Override
    public String toString() {
        return "Title: " + title +
                ", Author: " + author +
                ", Price: $" + price +
                ", Stock: " + stock +
                ", Category: " + category +
                ", Popularity: " + popularity;
    }
}
