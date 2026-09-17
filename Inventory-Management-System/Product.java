public class Product {

    private String id;
    private String name;
    private String category;
    private double price;
    private int quantity;
    private int reorderLevel;

    public Product(String id, String name, String category,
                   double price, int quantity, int reorderLevel) {

        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    // Add stock
    public void addStock(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Stock amount cannot be negative.");
        }
        quantity += amount;
    }

    // Remove stock
    public void removeStock(int amount)
            throws InsufficientStockException {

        if (amount < 0) {
            throw new IllegalArgumentException("Stock amount cannot be negative.");
        }

        if (amount > quantity) {
            throw new InsufficientStockException(
                    "Insufficient stock. Available: " + quantity
            );
        }

        quantity -= amount;
    }

    // Check low stock
    public boolean isLowStock() {
        return quantity <= reorderLevel;
    }

    // Convert object to CSV format
    public String toCSV() {

        return id + "|" +
               escape(name) + "|" +
               escape(category) + "|" +
               price + "|" +
               quantity + "|" +
               reorderLevel;
    }

    // Create Product from CSV
    public static Product fromCSV(String line) {

        String[] data = line.split("\\|", -1);

        if (data.length != 6) {
            throw new IllegalArgumentException(
                    "Invalid product record"
            );
        }

        return new Product(
                data[0],
                unescape(data[1]),
                unescape(data[2]),
                Double.parseDouble(data[3]),
                Integer.parseInt(data[4]),
                Integer.parseInt(data[5])
        );
    }

    private static String escape(String value) {
        return value.replace("|", " ");
    }

    private static String unescape(String value) {
        return value;
    }
}
