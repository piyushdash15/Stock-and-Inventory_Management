import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;

public class InventoryManager {

    private final Map<String, Product> products =
            new LinkedHashMap<>();

    private final List<Sale> sales =
            new ArrayList<>();

    private static final Path PRODUCT_FILE =
            Paths.get("products.csv");

    private static final Path SALES_FILE =
            Paths.get("sales.csv");


    // =====================================================
    // LOAD DATA
    // =====================================================

    public void loadData() {

        loadProducts();
        loadSales();
    }


    private void loadProducts() {

        if (!Files.exists(PRODUCT_FILE)) {
            return;
        }

        try (BufferedReader br =
                     Files.newBufferedReader(PRODUCT_FILE)) {

            String line;

            while ((line = br.readLine()) != null) {

                if (!line.isBlank()) {

                    try {

                        Product product =
                                Product.fromCSV(line);

                        products.put(
                                product.getId(),
                                product
                        );

                    } catch (RuntimeException e) {

                        System.out.println(
                                "Skipped invalid product record."
                        );
                    }
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Error loading products: " +
                    e.getMessage()
            );
        }
    }


    private void loadSales() {

        if (!Files.exists(SALES_FILE)) {
            return;
        }

        try (BufferedReader br =
                     Files.newBufferedReader(SALES_FILE)) {

            String line;

            while ((line = br.readLine()) != null) {

                if (!line.isBlank()) {

                    String[] data =
                            line.split("\\|", 6);

                    if (data.length == 6) {

                        try {

                            sales.add(
                                    new Sale(
                                            data[0],
                                            data[1],
                                            data[2],
                                            Integer.parseInt(data[3]),
                                            Double.parseDouble(data[4]),
                                            LocalDateTime.parse(data[5])
                                    )
                            );

                        } catch (RuntimeException ignored) {
                        }
                    }
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Error loading sales: " +
                    e.getMessage()
            );
        }
    }


    // =====================================================
    // SAVE DATA
    // =====================================================

    private void saveProducts() {

        try (BufferedWriter bw =
                     Files.newBufferedWriter(PRODUCT_FILE)) {

            for (Product product : products.values()) {

                bw.write(product.toCSV());
                bw.newLine();
            }

        } catch (IOException e) {

            System.out.println(
                    "Error saving products: " +
                    e.getMessage()
            );
        }
    }


    private void appendSale(Sale sale) {

        try (BufferedWriter bw =
                     Files.newBufferedWriter(
                             SALES_FILE,
                             StandardOpenOption.CREATE,
                             StandardOpenOption.APPEND
                     )) {

            bw.write(sale.toCSV());
            bw.newLine();

        } catch (IOException e) {

            System.out.println(
                    "Error saving sale: " +
                    e.getMessage()
            );
        }
    }


    // =====================================================
    // ADD PRODUCT
    // =====================================================

    public void addProduct(Scanner sc) {

        System.out.println("\n========== ADD PRODUCT ==========");

        String id =
                readNonBlank(sc, "Product ID: ");

        if (products.containsKey(id)) {

            System.out.println(
                    "Product ID already exists."
            );

            return;
        }

        String name =
                readNonBlank(sc, "Product Name: ");

        String category =
                readNonBlank(sc, "Category: ");

        double price =
                readDouble(sc, "Price: ", 0);

        int quantity =
                readInt(sc, "Initial Quantity: ", 0);

        int reorderLevel =
                readInt(sc, "Reorder Level: ", 0);

        Product product =
                new Product(
                        id,
                        name,
                        category,
                        price,
                        quantity,
                        reorderLevel
                );

        products.put(id, product);

        saveProducts();

        System.out.println(
                "Product added successfully!"
        );
    }


    // =====================================================
    // VIEW PRODUCTS
    // =====================================================

    public void viewProducts() {

        System.out.println("\n========== ALL PRODUCTS ==========");

        if (products.isEmpty()) {

            System.out.println(
                    "No products available."
            );

            return;
        }

        printHeader();

        for (Product product : products.values()) {

            printProduct(product);
        }
    }


    // =====================================================
    // SEARCH PRODUCT
    // =====================================================

    public void searchProduct(Scanner sc) {

        System.out.println("\n========== SEARCH PRODUCT ==========");

        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.println("3. Search by Category");

        int choice =
                readInt(sc, "Choice: ", 1);

        String searchValue =
                readNonBlank(
                        sc,
                        "Enter search value: "
                ).toLowerCase();

        boolean found = false;

        for (Product product : products.values()) {

            boolean match = switch (choice) {

                case 1 ->
                        product.getId()
                                .equalsIgnoreCase(
                                        searchValue
                                );

                case 2 ->
                        product.getName()
                                .toLowerCase()
                                .contains(searchValue);

                case 3 ->
                        product.getCategory()
                                .toLowerCase()
                                .contains(searchValue);

                default -> false;
            };

            if (match) {

                if (!found) {
                    printHeader();
                }

                printProduct(product);

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No matching product found."
            );
        }
    }


    // =====================================================
    // UPDATE PRODUCT
    // =====================================================

    public void updateProduct(Scanner sc) {

        String id =
                readNonBlank(
                        sc,
                        "Product ID to update: "
                );

        Product product =
                products.get(id);

        if (product == null) {

            System.out.println(
                    "Product not found."
            );

            return;
        }

        System.out.println("\n1. Name");
        System.out.println("2. Category");
        System.out.println("3. Price");
        System.out.println("4. Reorder Level");

        int choice =
                readInt(sc, "Select field: ", 1);

        switch (choice) {

            case 1 ->
                    product.setName(
                            readNonBlank(
                                    sc,
                                    "New name: "
                            )
                    );

            case 2 ->
                    product.setCategory(
                            readNonBlank(
                                    sc,
                                    "New category: "
                            )
                    );

            case 3 ->
                    product.setPrice(
                            readDouble(
                                    sc,
                                    "New price: ",
                                    0
                            )
                    );

            case 4 ->
                    product.setReorderLevel(
                            readInt(
                                    sc,
                                    "New reorder level: ",
                                    0
                            )
                    );

            default -> {

                System.out.println(
                        "Invalid choice."
                );

                return;
            }
        }

        saveProducts();

        System.out.println(
                "Product updated successfully!"
        );
    }


    // =====================================================
    // DELETE PRODUCT
    // =====================================================

    public void deleteProduct(Scanner sc) {

        String id =
                readNonBlank(
                        sc,
                        "Product ID to delete: "
                );

        Product removed =
                products.remove(id);

        if (removed == null) {

            System.out.println(
                    "Product not found."
            );

            return;
        }

        saveProducts();

        System.out.println(
                "Product deleted successfully!"
        );
    }


    // =====================================================
    // RESTOCK
    // =====================================================

    public void restockProduct(Scanner sc) {

        String id =
                readNonBlank(sc, "Product ID: ");

        Product product =
                products.get(id);

        if (product == null) {

            System.out.println(
                    "Product not found."
            );

            return;
        }

        int amount =
                readInt(
                        sc,
                        "Quantity to add: ",
                        1
                );

        int oldStock =
                product.getQuantity();

        product.addStock(amount);

        saveProducts();

        System.out.println(
                "Stock updated successfully!"
        );

        System.out.println(
                "Old Stock : " + oldStock
        );

        System.out.println(
                "Added     : " + amount
        );

        System.out.println(
                "New Stock : " +
                product.getQuantity()
        );
    }


    // =====================================================
    // SELL PRODUCT
    // =====================================================

    public void sellProduct(Scanner sc) {

        String id =
                readNonBlank(sc, "Product ID: ");

        Product product =
                products.get(id);

        if (product == null) {

            System.out.println(
                    "Product not found."
            );

            return;
        }

        int quantity =
                readInt(
                        sc,
                        "Quantity to sell: ",
                        1
                );

        try {

            product.removeStock(quantity);

            String saleId =
                    "S" +
                    String.format(
                            "%05d",
                            sales.size() + 1
                    );

            Sale sale =
                    new Sale(
                            saleId,
                            product.getId(),
                            product.getName(),
                            quantity,
                            product.getPrice(),
                            LocalDateTime.now()
                    );

            sales.add(sale);

            saveProducts();
            appendSale(sale);

            System.out.printf(
                    "Sale successful! Total = Rs. %.2f%n",
                    sale.getTotal()
            );

            System.out.println(
                    "Remaining stock: " +
                    product.getQuantity()
            );

            if (product.isLowStock()) {

                System.out.println(
                        "WARNING: Product is at/below reorder level!"
                );
            }

        } catch (InsufficientStockException e) {

            System.out.println(
                    "ERROR: " +
                    e.getMessage()
            );
        }
    }


    // =====================================================
    // LOW STOCK REPORT
    // =====================================================

    public void lowStockReport() {

        System.out.println(
                "\n========== LOW STOCK REPORT =========="
        );

        boolean found = false;

        printHeader();

        for (Product product : products.values()) {

            if (product.isLowStock()) {

                printProduct(product);

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No low-stock products."
            );
        }
    }


    // =====================================================
    // SALES REPORT
    // =====================================================

    public void salesReport() {

        System.out.println(
                "\n========== SALES REPORT =========="
        );

        if (sales.isEmpty()) {

            System.out.println(
                    "No sales recorded."
            );

            return;
        }

        System.out.printf(
                "%-10s %-8s %-20s %5s %10s %12s %s%n",
                "Sale ID",
                "Prod ID",
                "Product",
                "Qty",
                "Price",
                "Total",
                "Date"
        );

        System.out.println(
                "-".repeat(95)
        );

        double revenue = 0;
        int unitsSold = 0;

        for (Sale sale : sales) {

            System.out.println(
                    sale.display()
            );

            revenue += sale.getTotal();
            unitsSold += sale.getQuantity();
        }

        System.out.println(
                "-".repeat(95)
        );

        System.out.println(
                "Total transactions : " +
                sales.size()
        );

        System.out.println(
                "Total units sold   : " +
                unitsSold
        );

        System.out.printf(
                "Total revenue      : Rs. %.2f%n",
                revenue
        );
    }


    // =====================================================
    // INVENTORY SUMMARY
    // =====================================================

    public void inventorySummary() {

        int totalUnits = 0;
        int lowStock = 0;
        int outOfStock = 0;

        double inventoryValue = 0;

        for (Product product : products.values()) {

            totalUnits += product.getQuantity();

            inventoryValue +=
                    product.getQuantity() *
                    product.getPrice();

            if (product.getQuantity() == 0) {

                outOfStock++;

            } else if (product.isLowStock()) {

                lowStock++;
            }
        }

        System.out.println(
                "\n========== INVENTORY SUMMARY =========="
        );

        System.out.println(
                "Total Products        : " +
                products.size()
        );

        System.out.println(
                "Total Units           : " +
                totalUnits
        );

        System.out.printf(
                "Inventory Value       : Rs. %.2f%n",
                inventoryValue
        );

        System.out.println(
                "Low Stock Products    : " +
                lowStock
        );

        System.out.println(
                "Out of Stock Products : " +
                outOfStock
        );
    }


    // =====================================================
    // DISPLAY HELPERS
    // =====================================================

    private void printHeader() {

        System.out.printf(
                "%-8s %-20s %-18s %12s %8s %10s%n",
                "ID",
                "NAME",
                "CATEGORY",
                "PRICE",
                "STOCK",
                "REORDER"
        );

        System.out.println(
                "-".repeat(82)
        );
    }


    private void printProduct(Product product) {

        System.out.printf(
                "%-8s %-20s %-18s %12.2f %8d %10d%n",
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getQuantity(),
                product.getReorderLevel()
        );
    }


    // =====================================================
    // INPUT VALIDATION
    // =====================================================

    private String readNonBlank(
            Scanner sc,
            String prompt) {

        while (true) {

            System.out.print(prompt);

            String input =
                    sc.nextLine().trim();

            if (!input.isEmpty()) {

                return input;
            }

            System.out.println(
                    "Input cannot be empty."
            );
        }
    }


    private int readInt(
            Scanner sc,
            String prompt,
            int minimum) {

        while (true) {

            System.out.print(prompt);

            String input =
                    sc.nextLine().trim();

            try {

                int value =
                        Integer.parseInt(input);

                if (value >= minimum) {

                    return value;
                }

            } catch (NumberFormatException ignored) {
            }

            System.out.println(
                    "Please enter a valid integer >= " +
                    minimum
            );
        }
    }


    private double readDouble(
            Scanner sc,
            String prompt,
            double minimum) {

        while (true) {

            System.out.print(prompt);

            String input =
                    sc.nextLine().trim();

            try {

                double value =
                        Double.parseDouble(input);

                if (Double.isFinite(value)
                        && value >= minimum) {

                    return value;
                }

            } catch (NumberFormatException ignored) {
            }

            System.out.println(
                    "Please enter a valid number >= " +
                    minimum
            );
        }
    }
}