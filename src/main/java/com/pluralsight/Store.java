package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Store {

    public static void main(String[] args) {
        // Initialize variables
        ArrayList<Product> inventory = new ArrayList<Product>();
        ArrayList<Product> cart = new ArrayList<Product>();
        double totalAmount = 0.0;

        // Load inventory from CSV file
        loadInventory("products.csv", inventory);

        // Create scanner to read user input
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        // Display menu and get user choice until they choose to exit
        while (choice != 3) {
            System.out.println("Welcome to the Online Store!");
            System.out.println("1. Show Products");
            System.out.println("2. Show Cart");
            System.out.println("3. Exit");

            choice = scanner.nextInt();
            scanner.nextLine();

            // Call the appropriate method based on user choice
            switch (choice) {
                case 1:
                    displayProducts(inventory, cart, scanner);
                    break;
                case 2:
                    displayCart(cart, scanner, totalAmount);
                    checkOut(cart, totalAmount, scanner);
                    break;
                case 3:
                    System.out.println("Thank you for shopping with us!");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }
    // Reads transactions from file
    public static void loadInventory(String fileName, ArrayList<Product> inventory) {

        String line;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            while((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String id = parts[0];
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                Product myP = new Product(id, name, price);
                inventory.add(myP);
            }
        }catch (Exception e) {
            System.err.println("Error reading file");
        }
    }

    public static void displayProducts(ArrayList<Product> inventory, ArrayList<Product> cart, Scanner scanner) {


        // Prints out all products within the inventory
        System.out.println("==== Products ====");
        for (Product product : inventory) {
            System.out.println(product);
        }

        String id;
        System.out.println("Enter the id of product you would like to purchase");
        id = scanner.nextLine().trim();

        boolean check = false;
        // Looks for the ID that the user enters which is inside inventory and adds the product to cart Arraylist
        if (!id.isEmpty()) {
            Product product = Store.findProductById(id, inventory);
            if (product != null) {
                cart.add(product);
                System.out.println(product.getName() + " Added to cart.\n");
            } else {
                System.out.println("Product not found in cart.\n");
            }
        } else {
            System.out.println("No items Added.\n");
        }



    }

    public static void displayCart(ArrayList<Product> cart, Scanner scanner, double totalAmount) {
        // Adds up the price of the total amount in the cart array list
        System.out.println("==== Cart ====");
        for (Product product : cart) {
            System.out.println(product);
            totalAmount += product.getPrice();
        }
        // Checks if the amount is empty
        if (totalAmount == 0) {
            System.out.println("Your cart is Empty\n");
        } else {
            // Display the official total amount
            System.out.printf("Your total will be $%.2f\n", totalAmount);

            String id;
            System.out.println("Enter the id of product you would like to remove from cart or press enter to skip");
            id = scanner.nextLine().trim();

            boolean check = false;
            // Checks if the id is empty
            if (!id.isEmpty()) {
                // Just learned you need an iterator in order to safely remove something from a list in Java
                Product product = Store.findProductById(id, cart);
                if (product != null) {
                    cart.remove(product);
                    System.out.println(product.getName() + " Removed from Cart!\n");
                } else {
                    System.err.println("No results found for product");
                }

            } else {
                System.out.println("Skip...");

            }

        }


    }

    public static void checkOut(ArrayList<Product> cart, double totalAmount, Scanner scanner) {
        // Checks if the cart is empty
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Nothing to check out.");
            return;
        }
        //
        System.out.println("Would you like to confirm your Purchase");
        System.out.println("1. yes");
        System.out.println("2. no");
        int confirm = scanner.nextInt();
        // Asking the user for confirmation of purchase
        if (confirm == 1) {
            System.out.println("Please enter your cash payment:");
            double payment = scanner.nextDouble();

            //Counts out all products within the inventory
            for (Product product : cart) {
                System.out.println(product);
                totalAmount += product.getPrice();
            }

            if (payment < totalAmount) {
                System.out.println("Insufficient funds");
            } else {
                double change = payment - totalAmount;
                System.out.printf("Change: $%.2f\n", change);
                // Printing receipt and showing it to the user
                System.out.println("===== Receipt =====");
                for (Product product : cart) {
                    System.out.println(product.getName() + " - $" + String.format("%.2f", product.getPrice()));
                }
                System.out.printf("Total Paid: $%.2f\n", payment);
                System.out.printf("Change: $%.2f\n", change);
                System.out.println("Thank you for your purchase!");

                // Clear cart
                cart.clear();
            }
        } else {
            System.out.println("");
        }







    }

    public static Product findProductById(String id, ArrayList<Product> inventory) {
        // Helps automate finding the product by the id
        for (Product product : inventory) {
            if (product.getId().equalsIgnoreCase(id)) {
                return product;
            }
        }
        return null;
    }
}
