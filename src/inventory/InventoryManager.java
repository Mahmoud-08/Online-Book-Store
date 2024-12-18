package inventory;
import books.Book;
import java.util.HashMap;
import java.util.Map;

public class InventoryManager {
    private static InventoryManager instance;
    private Map<String, Book> inventory;

    //TODO: MUST SEPERATE BOOK AND CATEGORIES IN INVENTORIES

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


    public boolean editBook(String title, String... properties) {
        if (!inventory.containsKey(title)) {
            return false; // Book not found
        }

        Book book = inventory.get(title);

        // Assuming properties are in pairs: field name, new value
        for (int i = 0; i < properties.length; i += 2) {
            String field = properties[i];
            String newValue = properties[i + 1];

            switch (field.toLowerCase()) {
                case "author":
                    book.setAuthor(newValue);
                    break;
                case "price":
                    book.setPrice(Double.parseDouble(newValue)); // Convert string to double
                    break;
                case "stock":
                    book.setStock(Integer.parseInt(newValue));
                    break;
                case "title":
                    book.setTitle(newValue);
                    break;
                case "category":
                    book.setCategory(newValue);
                    break;
                case "popularity":
                    book.setPopularity(Integer.parseInt(newValue));
                    break;

                default:
                    System.out.println("Unknown field: " + field);
            }
        }

        return true;
    }
    public Book getBook(String title) {
        return inventory.get(title);
    }
    public boolean deleteBook(String title){
        if (!inventory.containsKey(title)) {
            return false;
        }
        inventory.remove(title);
        return true;
    }
    public void removeBook(String title) {
        inventory.remove(title);
        System.out.println("Book removed: " + title);
    }

    public Map<String, Book> getInventory() {
        return inventory;
    }
}
