package products;
import java.time.LocalDate;
//lik cheese
public class ProductShippingAndExpiry extends Product implements Shippable, Expirable{
    private double weight;
    private LocalDate expiryDate;

    public ProductShippingAndExpiry(String name, double price, int quantity, double weight, LocalDate expiryDate) {
        super(name, price, quantity);
        this.weight = weight;
        this.expiryDate = expiryDate;
    }

    @Override
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public double getWeight() {
        return weight;
    }

}
