import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Sale {

    private final String saleId;
    private final String productId;
    private final String productName;
    private final int quantity;
    private final double unitPrice;
    private final LocalDateTime dateTime;

    public Sale(String saleId,
                String productId,
                String productName,
                int quantity,
                double unitPrice,
                LocalDateTime dateTime) {

        this.saleId = saleId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.dateTime = dateTime;
    }

    public double getTotal() {
        return unitPrice * quantity;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    // Save sale to file
    public String toCSV() {

        return saleId + "|" +
               productId + "|" +
               productName.replace("|", " ") + "|" +
               quantity + "|" +
               unitPrice + "|" +
               dateTime;
    }

    // Display sale
    public String display() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        return String.format(
                "%-10s %-8s %-20s %5d %10.2f %12.2f %s",
                saleId,
                productId,
                productName,
                quantity,
                unitPrice,
                getTotal(),
                dateTime.format(formatter)
        );
    }
}
