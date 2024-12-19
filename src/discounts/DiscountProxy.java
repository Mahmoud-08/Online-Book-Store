package discounts;

public class DiscountProxy implements Discount {
    private final RealDiscount realDiscountHandler;

    public DiscountProxy() {
        this.realDiscountHandler = new RealDiscount();
    }

    @Override
    public double applyDiscount(double originalPrice, String discountCode) {
        if (isValidCode(discountCode)) {
            return realDiscountHandler.applyDiscount(originalPrice, discountCode);
        } else {
            System.out.println("Invalid or expired discount code!");
            return originalPrice; // No discount applied
        }
    }

    private boolean isValidCode(String discountCode) {
        // Simulated validation logic: Ensure code is non-null, non-empty, etc.
        return discountCode != null && !discountCode.isEmpty() && discountCode.matches("^[A-Z0-9]{5,}$");
    }
}

