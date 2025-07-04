package products;

import java.time.LocalDate;
// simple product that not have expire date and not shippable like Mobile

public class Product {
    protected String name;
    protected double price;
    protected int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public boolean isAvailable(int requestedQty) {
        return quantity >= requestedQty;
    }

    public void reduceQuantity(int qty) {
        this.quantity -= qty;
    }

    //public abstract boolean isExpired();
    public boolean isExpired() {
        if (this instanceof Expirable) {
            LocalDate exp = ((Expirable) this).getExpiryDate();
            return exp != null && exp.isBefore(LocalDate.now());
        }
        return false;
    }
}
