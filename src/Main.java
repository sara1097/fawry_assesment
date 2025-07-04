import customer.*;
import factory.ProductFactory;
import products.Product;
import service.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Product cheese = ProductFactory.createProduct(
                "Cheese", 50, 5, true, true, 1.5, LocalDate.now().plusDays(5));

        Product tv = ProductFactory.createProduct(
                "TV", 300, 2, true, false, 10.0, null);

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

        Customer customer1 = new Customer("tara", 2000);
        Cart cart1 = new Cart();
        cart1.addItem(cheese, 3);
        cart1.addItem(tv, 1);
        cart1.addItem(Mobile, 3);


        try {
            Checkout.checkout(cart, customer);
        } catch (Exception e) {
            System.out.println("Checkout failed: " + e.getMessage());
        }


    }


}