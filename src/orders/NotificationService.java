package orders;

public class NotificationService {
    public static void notifyCustomer(Order order) {
        System.out.println("Notification: Order status updated to " + order.getStatus());
    }
}
