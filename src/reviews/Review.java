package reviews;

import books.Book;
import user.Customer;

public class Review {
    private String reviewText;
    private int starRating;  // Rating between 1 and 5
    private Book book;
    private Customer customer;  // Customer who wrote the review

    public Review(Book book, String reviewText, int starRating, Customer customer) {
        if (starRating < 1 || starRating > 5) {
            throw new IllegalArgumentException("Star rating must be between 1 and 5.");
        }
        this.book = book;
        this.reviewText = reviewText;
        this.starRating = starRating;
        this.customer = customer;  // Associate the customer with the review
    }

    public String getReviewText() {
        return reviewText;
    }

    public int getStarRating() {
        return starRating;
    }

    public Book getBook() {
        return book;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "Review for " + book.getTitle() + ": " + reviewText + " - " + starRating + " stars.";
    }
}
