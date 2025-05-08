package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
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
        // This method should display a list of products from the inventory,
        // and prompt the user to add items to their cart. The method should
        // prompt the user to enter the ID of the product they want to add to
        // their cart. The method should
        // add the selected product to the cart ArrayList.

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
        for (Product product : inventory) {
            if (product.getId().equalsIgnoreCase(id)) {
                cart.add(product);
                System.out.println(product.getName() + " Added to Cart!\n");
                check = true;
            }
        }

        if (!check) {
            System.err.println("No results found for product");
        }



    }

    public static void displayCart(ArrayList<Product> cart, Scanner scanner, double totalAmount) {
        // This method should display the items in the cart ArrayList, along
        // with the total cost of all items in the cart. The method should
        // prompt the user to remove items from their cart by entering the ID
        // of the product they want to remove. The method should update the cart ArrayList and totalAmount
        // variable accordingly.
        System.out.println("==== Cart ====");
        for (Product product : cart) {
            System.out.println(product);
            totalAmount += product.getPrice();
        }
        if (totalAmount == 0) {
            System.out.println("Your cart is Empty\n");
        } else {
            System.out.printf("Your total will be $%.2f\n", totalAmount);

            String id;
            System.out.println("Enter the id of product you would like to remove from cart or press enter to skip");
            id = scanner.nextLine().trim();

            boolean check = false;

            if (!id.isEmpty()) {
                // Just learned you need an iterator in order to safely remove something from a list in Java
                Iterator<Product> iterator = cart.iterator();
                while (iterator.hasNext()) {
                    Product product = iterator.next();
                    if (product.getId().equalsIgnoreCase(id)) {
                        iterator.remove();
                        System.out.println(product.getName() + " Removed to Cart!\n");
                        check = true;
                    }
                }

                if (!check) {
                    System.err.println("No results found for product");
                }

            } else {
                System.out.println("Skip...");

            }

        }


    }

    public static void checkOut(ArrayList<Product> cart, double totalAmount, Scanner scanner) {
        // This method should calculate the total cost of all items in the cart,
        // and display a summary of the purchase to the user. The method should
        // prompt the user to confirm the purchase, and calculate change and clear the cart
        // if they confirm.

        boolean check = false;

        while (!check) {
            System.out.println("Would you like to confirm your Purchase");
            System.out.println("1. yes");
            System.out.println("2. no");
            int confirm = scanner.nextInt();

           
                check = true;

            } else if (confirm == 2) {

                check = true;

            } else {
                System.out.println("Invalid choice!");
            }
        }



    }

    public static Product findProductById(String id, ArrayList<Product> inventory) {
        // This method should search the inventory ArrayList for a product with
        // the specified ID, and return the corresponding com.pluralsight.Product object. If
        // no product with the specified ID is found, the method should return
        // null.

        return null;
    }
}
