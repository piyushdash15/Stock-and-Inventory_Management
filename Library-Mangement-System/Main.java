import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        InventoryManager manager =
                new InventoryManager();

        manager.loadData();

        Scanner sc =
                new Scanner(System.in);

        System.out.println(
                "==============================================="
        );

        System.out.println(
                "      INVENTORY & STOCK MANAGEMENT SYSTEM"
        );

        System.out.println(
                "==============================================="
        );

        while (true) {

            printMenu();

            String input =
                    sc.nextLine().trim();

            int choice;

            try {

                choice =
                        Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid choice. Enter a number."
                );

                continue;
            }


            switch (choice) {

                case 1 ->
                        manager.addProduct(sc);

                case 2 ->
                        manager.viewProducts();

                case 3 ->
                        manager.searchProduct(sc);

                case 4 ->
                        manager.updateProduct(sc);

                case 5 ->
                        manager.deleteProduct(sc);

                case 6 ->
                        manager.restockProduct(sc);

                case 7 ->
                        manager.sellProduct(sc);

                case 8 ->
                        manager.lowStockReport();

                case 9 ->
                        manager.salesReport();

                case 10 ->
                        manager.inventorySummary();

                case 11 -> {

                    System.out.println(
                            "\nThank you for using the system!"
                    );

                    sc.close();

                    return;
                }

                default ->
                        System.out.println(
                                "Please choose between 1 and 11."
                        );
            }

            System.out.println();
        }
    }


    private static void printMenu() {

        System.out.println(
                "\n================ MAIN MENU ================"
        );

        System.out.println(
                "1. Add Product"
        );

        System.out.println(
                "2. View All Products"
        );

        System.out.println(
                "3. Search Product"
        );

        System.out.println(
                "4. Update Product"
        );

        System.out.println(
                "5. Delete Product"
        );

        System.out.println(
                "6. Restock Product"
        );

        System.out.println(
                "7. Sell Product"
        );

        System.out.println(
                "8. Low Stock Report"
        );

        System.out.println(
                "9. Sales Report"
        );

        System.out.println(
                "10. Inventory Summary"
        );

        System.out.println(
                "11. Exit"
        );

        System.out.println(
                "==========================================="
        );

        System.out.print(
                "Enter choice: "
        );
    }
}
