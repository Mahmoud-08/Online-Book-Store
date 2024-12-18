package user;
import inventory.InventoryManager;

public class Admin extends User {
    private InventoryManager inventoryManager;

    public Admin(String username, String password) {
        super(username, password);
        this.inventoryManager = InventoryManager.getInstance();
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Admin");
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }
}