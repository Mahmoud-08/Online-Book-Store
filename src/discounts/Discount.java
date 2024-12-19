package discounts;

public interface Discount {
    double applyDiscount(double originalPrice, String discountCode);
}
