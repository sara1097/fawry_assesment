package factory;
import products.*;
import java.time.LocalDate;

public class ProductFactory {

    public static Product createProduct(String name, double price, int quantity,
                                        boolean isShippable, boolean isExpirable,
                                        Double weight, LocalDate expiryDate) {

        // check validity
        if (isShippable && (weight == null || weight <= 0)) {
            throw new IllegalArgumentException("Weight must be provided for shippable items.");
        }

        if (isExpirable && expiryDate == null) {
            throw new IllegalArgumentException("Expiry date must be provided for expirable items.");
        }

        // Shippable & Expirable => Cheese-like
        if (isShippable && isExpirable) {
            return new ProductShippingAndExpiry(name, price, quantity, weight, expiryDate);
        }

        // Shippable only => TV-like
        if (isShippable) {
            return new ShippableProduct(name, price, quantity, weight);
        }

        // Expirable only => fruits-like
        if (isExpirable) {
            return new ExpirableProduct(name, price, quantity, expiryDate);
        }

        // Neither => Mobile Card
        return new Product(name, price, quantity);
    }
}
