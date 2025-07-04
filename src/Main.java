import customer.*;
import factory.ProductFactory;
import products.Product;
import service.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Product cheese = ProductFactory.createProduct(
                "Cheese", 50, 10, true, true, 1.5, LocalDate.now().plusDays(5));

        Product tv = ProductFactory.createProduct(
                "TV", 300, 5, true, false, 10.0, null);

        Product biscuits = ProductFactory.createProduct(
                "Biscuit", 25, 10, false, true, null, LocalDate.now().plusDays(2));

        Product Mobile = ProductFactory.createProduct(
                "Mobile Card", 10, 50, false, false, null, null);

        Customer customer = new Customer("Sara", 8000);
        Cart cart = new Cart();
        cart.addItem(cheese, 2);
        cart.addItem(tv, 1);
        cart.addItem(Mobile, 3);
        cart.addItem(biscuits, 1);



        try {
            System.out.println("use case shippable and non , expired and non ");
            Checkout.checkout(cart, customer);

        } catch (Exception e) {
            System.out.println("Checkout failed: " + e.getMessage());
        }

        //empty cart use case
        Customer user = new Customer("User", 5000);
        Cart emptyCart = new Cart();

        try {
            System.out.println("use case empty cart");
            Checkout.checkout(emptyCart, user);

        } catch (Exception e) {
            System.out.println("Checkout failed: " + e.getMessage());
        }


        // out of stock use case
        Customer user2 = new Customer("StockError", 5000);
        Cart cart2 = new Cart();

        try {
            System.out.println("use case out of stock");
            cart2.addItem(cheese, 10);
            cart2.addItem(tv,1);
            Checkout.checkout(cart2, user2);
        } catch (Exception e) {
            System.out.println("Failed " + e.getMessage());
        }

        //use case items is not shipabale

        try {
            System.out.println("use case no shipping notice as no shippable items");
            Customer digital = new Customer("DigitalUser", 100);
            Cart cart3 = new Cart();
            cart.addItem(Mobile, 2); // not shippable
            Checkout.checkout(cart, digital);

        } catch (Exception e) {
            System.out.println("Failed " + e.getMessage());
        }


    }


}