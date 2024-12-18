package inventory;
import books.Book;
import java.util.HashMap;
import java.util.Map;

public class InventoryManager {
    private static InventoryManager instance;
    private Map<String, Book> inventory;

    private InventoryManager() {
        inventory = new HashMap<>();
    }

    public static InventoryManager getInstance() {
        if (instance == null) {
            instance = new InventoryManager();
        }
        return instance;
    }

    public void addBook(Book book) {
        inventory.put(book.getTitle(), book);
        System.out.println("Book added: " + book.getTitle());
    }

    public Book getBook(String title) {
        return inventory.get(title);
    }

    public void removeBook(String title) {
        inventory.remove(title);
        System.out.println("Book removed: " + title);
    }

    public Map<String, Book> getInventory() {
        return inventory;
    }
}
