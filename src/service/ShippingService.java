package service;
import products.Shippable;
import java.util.List;

public class ShippingService {

    public static double calculateShippingFees(List<Shippable> items, List<Integer> qty) {
        double totalWeight = 0;

        for (int i = 0; i < items.size(); i++) {
            totalWeight += items.get(i).getWeight() * qty.get(i);
        }

        return totalWeight * 10; // e.g., $10 per kg
    }

    public static void ship(List<Shippable> items , List<Integer> qty) {
        System.out.println("\n** Shipment notice **");
        double totalweight =0;
        for (int i = 0; i < items.size(); i++) {
            Shippable item = items.get(i);
            int quantity = qty.get(i).intValue();
            double weight = item.getWeight()* quantity;
            totalweight += weight;

            System.out.printf("%dx %-15s %dg%n", quantity, item.getName() ,(int)(weight * 1000) );
        }
        System.out.printf("Total package weight %.1fkg%n", totalweight);
    }

}
