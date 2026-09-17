# Stock-Inventory-Management-System
# Inventory & Stock Management System

A **command-line based Inventory & Stock Management System** developed using **Core Java**. The system allows users to manage products, monitor stock levels, process sales, generate inventory reports, and persist data using local files.

The project is designed to run completely through a **terminal/command-line environment** without requiring any graphical user interface.

---

## 📌 Project Overview

The Inventory & Stock Management System is a Java-based application that helps manage products and their stock efficiently.

It provides functionality for:

* Adding new products
* Viewing all products
* Searching products
* Updating product information
* Deleting products
* Restocking inventory
* Selling products
* Detecting low-stock products
* Maintaining sales records
* Generating inventory summaries
* Saving data permanently using files

The project demonstrates important **Core Java and Object-Oriented Programming concepts** through a practical real-world application.

---

## 🎯 Objectives

The main objectives of this project are:

1. To develop a fully terminal-based inventory management application.
2. To implement Object-Oriented Programming using Java.
3. To provide efficient product and stock management.
4. To prevent invalid stock operations such as selling unavailable products.
5. To maintain persistent product and sales data.
6. To demonstrate Java Collections, File I/O, Exception Handling, and Date/Time APIs.
7. To create a modular and easily extendable application.

---

## ✨ Features

### 1. Product Management

The system allows users to perform complete CRUD operations:

* Add Product
* View Products
* Search Product
* Update Product
* Delete Product

Each product contains:

```text
Product ID
Product Name
Category
Price
Quantity
Reorder Level
```

---

### 2. Stock Management

Users can manage inventory levels through:

* Stock Restocking
* Product Selling
* Stock Availability Checking
* Automatic Stock Reduction
* Low-Stock Detection

When a product reaches or falls below its reorder level, the system generates a warning.

---

### 3. Sales Management

The system records every successful sale with:

```text
Sale ID
Product ID
Product Name
Quantity Sold
Unit Price
Total Amount
Date and Time
```

The total sale amount is calculated automatically.

```text
Total Amount = Quantity × Unit Price
```

---

### 4. Low Stock Report

The system automatically identifies products whose stock is less than or equal to their reorder level.

Example:

```text
========== LOW STOCK REPORT ==========

ID       NAME                  CATEGORY             PRICE    STOCK
----------------------------------------------------------------------
P101     Mechanical Keyboard  Electronics          1499.00      4
P105     USB Cable            Accessories             299.00      3
```

---

### 5. Inventory Summary

The application generates an overall inventory summary containing:

* Total number of products
* Total units in stock
* Total inventory value
* Number of low-stock products
* Number of out-of-stock products

Inventory value is calculated as:

```text
Inventory Value =
Product Price × Available Quantity
```

for all products.

---

### 6. Data Persistence

The project uses local files to preserve information after the application is closed.

The following files are automatically created:

```text
products.csv
sales.csv
```

Therefore, previously stored products and sales remain available when the application is started again.

---

### 7. Input Validation

The application validates user input to prevent invalid data such as:

* Negative prices
* Negative quantities
* Empty product names
* Invalid menu choices
* Non-numeric input where numbers are required

---

### 8. Exception Handling

A custom exception is used for insufficient stock:

```text
InsufficientStockException
```

Example:

```text
Available Stock : 5
Requested Stock : 10

ERROR: Insufficient stock. Available: 5
```

---

# 🛠️ Technologies Used

| Technology         | Purpose                   |
| ------------------ | ------------------------- |
| Java               | Core programming language |
| OOP                | Application architecture  |
| Java Collections   | Product and sales storage |
| File I/O           | Data persistence          |
| LocalDateTime      | Sale date and time        |
| Exception Handling | Error management          |
| CSV/Text Files     | Local data storage        |
| Command Line       | User interface            |

---

# 🧠 Core Java Concepts Demonstrated

This project demonstrates several important Java concepts:

### Object-Oriented Programming

* Classes and Objects
* Encapsulation
* Constructors
* Getters and Setters
* Abstraction of business logic

### Collections

The project uses:

```java
Map<String, Product>
List<Sale>
ArrayList
LinkedHashMap
```

### File Handling

The project uses Java file APIs such as:

```java
BufferedReader
BufferedWriter
Files
Path
StandardOpenOption
```

### Exception Handling

The project demonstrates:

```java
try
catch
custom exceptions
input validation
```

### Date and Time API

Sales use:

```java
LocalDateTime
```

to store the date and time of each transaction.

---

# 📂 Project Structure

```text
InventoryManagementSystem/
│
├── Main.java
├── InventoryManager.java
├── Product.java
├── Sale.java
├── InsufficientStockException.java
├── ProductNotFoundException.java
│
├── products.csv
└── sales.csv
```

### File Description

#### `Main.java`

The entry point of the application.

Responsible for:

* Displaying the main menu
* Reading user input
* Navigating between operations
* Starting and terminating the application

---

#### `InventoryManager.java`

Contains the main business logic of the system.

Responsible for:

* Product management
* Stock management
* Sales processing
* Reports
* File loading and saving
* Input validation

---

#### `Product.java`

Represents a product in the inventory.

Contains:

* Product information
* Stock operations
* Low-stock checking
* CSV conversion

---

#### `Sale.java`

Represents a completed sale.

Contains:

* Sale ID
* Product information
* Quantity sold
* Price
* Date and time
* Total calculation

---

#### `InsufficientStockException.java`

Custom exception thrown when a user attempts to sell more units than are available.

---

#### `ProductNotFoundException.java`

Custom exception reserved for product lookup operations and future project expansion.

---

#### `products.csv`

Stores persistent product data.

Example:

```text
P101|Mechanical Keyboard|Electronics|1499.0|20|5
P102|Wireless Mouse|Electronics|799.0|35|5
```

---

#### `sales.csv`

Stores completed sales.

Example:

```text
S00001|P101|Mechanical Keyboard|2|1499.0|2026-09-17T15:30:00
```

---

# 🚀 Installation & Setup

## Prerequisites

Make sure Java is installed on your system.

Recommended:

```text
Java 17 or later
```

Check your Java version:

```bash
java -version
```

Check the Java compiler:

```bash
javac -version
```

---

# ▶️ How to Run

## 1. Clone the repository

```bash
git clone <YOUR-GITHUB-REPOSITORY-URL>
```

Move into the project directory:

```bash
cd InventoryManagementSystem
```

---

## 2. Compile the project

Since all Java files are in the same directory:

```bash
javac *.java
```

---

## 3. Run the application

```bash
java Main
```

---

# 🖥️ Application Menu

After starting the application, the following menu is displayed:

```text
===============================================
      INVENTORY & STOCK MANAGEMENT SYSTEM
===============================================

================ MAIN MENU ================

1. Add Product
2. View All Products
3. Search Product
4. Update Product
5. Delete Product
6. Restock Product
7. Sell Product
8. Low Stock Report
9. Sales Report
10. Inventory Summary
11. Exit

===========================================

Enter choice:
```

---

# 📋 Example Workflow

## Add a Product

```text
Enter choice: 1

========== ADD PRODUCT ==========

Product ID: P101
Product Name: Mechanical Keyboard
Category: Electronics
Price: 1499
Initial Quantity: 20
Reorder Level: 5

Product added successfully!
```

---

## View Products

```text
Enter choice: 2

========== ALL PRODUCTS ==========

ID       NAME                  CATEGORY                PRICE    STOCK    REORDER
----------------------------------------------------------------------------------
P101     Mechanical Keyboard   Electronics            1499.00       20          5
P102     Wireless Mouse        Electronics             799.00       35          5
```

---

## Sell a Product

```text
Enter choice: 7

Product ID: P101
Quantity to sell: 3

Sale successful!
Total = Rs. 4497.00
Remaining stock: 17
```

---

## Attempt to Sell Excess Stock

```text
Enter choice: 7

Product ID: P101
Quantity to sell: 50

ERROR: Insufficient stock. Available: 17
```

---

## View Inventory Summary

```text
========== INVENTORY SUMMARY ==========

Total Products        : 2
Total Units           : 52
Inventory Value       : Rs. 57965.00
Low Stock Products    : 0
Out of Stock Products : 0
```

---

# 🔄 Application Workflow

```text
                START
                  │
                  ▼
          Load Saved Data
                  │
                  ▼
             Main Menu
                  │
        ┌─────────┼─────────┐
        ▼         ▼         ▼
     Products   Stock     Reports
        │         │         │
        └─────────┼─────────┘
                  │
                  ▼
          Save Updated Data
                  │
                  ▼
             Main Menu
                  │
                  ▼
                EXIT
```

---

# 🔐 Business Rules

The application follows several basic inventory rules:

### Product ID must be unique

A product cannot be added using an existing ID.

### Price cannot be negative

```text
Price >= 0
```

### Quantity cannot be negative

```text
Quantity >= 0
```

### Sold quantity must be positive

```text
Quantity Sold >= 1
```

### Stock cannot become negative

The system prevents sales when requested quantity exceeds available stock.

### Low-stock condition

A product is considered low stock when:

```text
Current Quantity <= Reorder Level
```

---

# 📊 Reports

The current system provides:

### Low Stock Report

Identifies products that require restocking.

### Sales Report

Displays:

* Sale ID
* Product ID
* Product Name
* Quantity
* Unit Price
* Total
* Date/Time

### Inventory Summary

Displays:

* Total products
* Total units
* Inventory valuation
* Low-stock products
* Out-of-stock products

---

# 🧪 Testing Scenarios

The following cases should be tested before submission:

| Test Case                      | Expected Result             |
| ------------------------------ | --------------------------- |
| Add valid product              | Product added               |
| Add duplicate product ID       | Error message               |
| Enter negative price           | Input rejected              |
| Enter negative quantity        | Input rejected              |
| Search existing product        | Product displayed           |
| Search invalid ID              | Product not found           |
| Update product                 | Product information updated |
| Delete existing product        | Product removed             |
| Restock product                | Quantity increased          |
| Sell available stock           | Sale completed              |
| Sell more than available stock | Sale rejected               |
| Reach reorder level            | Low-stock warning           |
| Restart application            | Previous data remains       |

---

# 📈 Future Enhancements

The current application can be expanded with several advanced features:

* Supplier Management
* Multi-product Shopping Cart
* Invoice/Bill Generation
* Most-Sold Product Report
* Category-wise Sales Analysis
* User Authentication
* Admin and Employee Roles
* MySQL Database Integration using JDBC
* Automatic Inventory Reordering
* Product Expiry Tracking
* GST/Tax Calculation
* Discount Management
* Export Reports to CSV/Excel
* Barcode/QR Code Integration
* Dashboard-based GUI in a future version

---

---

# 💡 Why This Project?

This project provides practical exposure to:

```text
Problem Solving
      ↓
Object-Oriented Programming
      ↓
Data Structures
      ↓
File Handling
      ↓
Exception Handling
      ↓
Business Logic
      ↓
Software Design
```

It is particularly useful for understanding how Java can be used to build a real-world management system without depending on a graphical interface.

---

# 👨‍💻 Project Type

```text
Project Type      : Command-Line Application
Language          : Java
Application       : Inventory & Stock Management
Interface         : Terminal / Command Line
Database          : Local CSV/Text Files
GUI               : Not Required
Framework         : None
```

---
# 👤 Student Details

Name: Piyush Kumar Dash

Registration Number: 25BAI10103

---
# 📜 Important Instruction

This project is intended for educational and academic purposes.

You are free to modify and extend the project according to your requirements.

---

# ⭐ Acknowledgement

This project was developed as a practical implementation of **Core Java, Object-Oriented Programming, Collections, File Handling, Exception Handling, and basic software engineering concepts**.
