package orders;

import user.Customer;
import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
    private List<Order> orders;

    public OrderHistory() {
        orders = new ArrayList<>();
    }

    // Add an order to history
// Add completed order to history
    public void addOrder(Order order) {
        if (order.getStatus() == OrderStatus.COMPLETED) {
            orders.add(order);
        }
    }

    // Get all orders for a customer
    public List<Order> getOrders(Customer customer) {
        List<Order> customerOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getCustomer() != null && order.getCustomer().equals(customer)) {
                customerOrders.add(order);
            }
        }
        return customerOrders;
    }

    // Get all orders (no customer filter)
    public List<Order> getOrders() {
        return orders;
    }
}
