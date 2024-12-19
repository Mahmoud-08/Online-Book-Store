package discounts;

import java.util.Map;
import java.util.HashMap;

public class RealDiscount implements Discount {
    private final Map<String, Double> discountCodes;

    public RealDiscount() {
        discountCodes = new HashMap<>();
        discountCodes.put("SAVE10", 0.10);
        discountCodes.put("HOLIDAY20", 0.20);
        discountCodes.put("NEWYEAR25", 0.25);
    }

    @Override
    public double applyDiscount(double originalPrice, String discountCode) {
        if (discountCodes.containsKey(discountCode)) {
            double discount = discountCodes.get(discountCode);
            return originalPrice - (originalPrice * discount);
        }
        return originalPrice; // No discount applied if code is invalid
    }
}

