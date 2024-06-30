package generics.cart;

import java.util.*;

public class ShoppingCart<T extends CartItem> {

    private final List<T> items = new ArrayList<>();
    private final Map<String, Integer> itemQuantities = new HashMap<>();
    private final List<Double> discounts = new ArrayList<>();

    public void add(T item) {
        items.add(item);
        itemQuantities.put(item.getId(),
                itemQuantities.getOrDefault(item.getId(), 0) + 1);
    }

    public void removeById(String id) {
        for (T item : items) {
            if (item.getId().equals(id)) {
                items.remove(item);
                itemQuantities.put(id, itemQuantities.get(id) - 1);
                break;
            }
        }
    }

    public Double getTotal() {
        Double total = 0.0;
        for (T item : items) {
            total += item.getPrice();
        }

        for (Double discount : discounts) {
            total -= total * discount / 100;
        }

        return total;
    }

    public void increaseQuantity(String id) {
        for (T item : items) {
            if (item.getId().equals(id)) {
                itemQuantities.put(id, itemQuantities.get(id) + 1);
                items.add(item);
                break;
            }
        }
    }

    public void applyDiscountPercentage(Double discount) {
        discounts.add(discount);
    }

    public void removeLastDiscount() {
        discounts.removeLast();
    }

    public void addAll(List<? extends T> items) {
        for (T item : items) {
            add(item);
        }
    }

    @Override
    public String toString() {
        List<String> itemStrings = new ArrayList<>();
        Set<String> addedItems = new HashSet<>();
        for (T item : items) {
            String id = item.getId();
            if (!addedItems.contains(id)) {
                itemStrings.add("(" + id + ", " +
                        item.getPrice() + ", " +
                        itemQuantities.get(id) + ")");
                addedItems.add(id);
            }
        }

        return String.join(", ", itemStrings);
    }
}
