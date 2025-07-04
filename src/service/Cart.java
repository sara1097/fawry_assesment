package service;

import java.util.*;
import products.Product;

public class Cart {
    public static class Item {
        public Product product;
        public int quantity;

        public Item(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public double getSubtotal() {
            return product.getPrice() * quantity;

        }

        public int getQuantity() {
            return quantity;
        }
    }
    private List<Item> items = new ArrayList<>();

    public void addItem(Product product, int qty) {
        if (!product.isAvailable(qty)) {
            throw new IllegalArgumentException("Requested quantity not available.");
        }
        items.add(new Item(product, qty));
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }

    public double calculateSubtotal() {
        return items.stream().mapToDouble(Item::getSubtotal).sum();
    }

}
