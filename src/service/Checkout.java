package service;

import customer.Customer;
import products.Product;
import products.Shippable;

import java.util.ArrayList;
import java.util.List;


public class Checkout {

    public static void checkout(Cart cart, Customer customer) {
        if (cart.isEmpty()) throw new IllegalStateException("Cart is empty");

        double subtotal = cart.calculateSubtotal();

        List<Shippable> shippableItems = new ArrayList<>();
        List<Integer> QtyOfItems = new ArrayList<>();
        for (Cart.Item item : cart.getItems()) {
            Product product = item.product;

            if (product.isExpired()) throw new IllegalStateException(product.getName() + " is expired");
            if (!product.isAvailable(item.quantity)) throw new IllegalStateException(product.getName() + " out of stock");

            product.reduceQuantity(item.quantity);
            if (product instanceof Shippable) {
                shippableItems.add((Shippable) product);
                QtyOfItems.add(item.getQuantity());
            }
        }

        double shippingFee = ShippingService.calculateShippingFees(shippableItems , QtyOfItems);
        double total = subtotal + shippingFee;

        if (!customer.canAfford(total)) throw new IllegalStateException("Insufficient balance");

        customer.deduct(total);


        if (!shippableItems.isEmpty()) {
            ShippingService.ship(shippableItems , QtyOfItems);
        }

        System.out.println("\n** Checkout receipt **");
        for (Cart.Item item : cart.getItems()) {
            System.out.printf("%dx %-15s", item.quantity, item.product.getName());
            System.out.println((int) (item.product.getPrice() * item.quantity));
        }


        System.out.println("----------------------");
        System.out.printf("%-17s %.0f%n", "Subtotal", subtotal);
        System.out.printf("%-17s %.0f%n", "Shipping", shippingFee);
        System.out.printf("%-17s %.0f%n", "Amount", total);;
        System.out.println("Remaining Balance: $" + customer.getBalance());

        cart.clear();
    }

}
