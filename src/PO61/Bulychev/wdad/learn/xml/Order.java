package PO61.Bulychev.wdad.learn.xml;

import java.util.List;

public class Order {

    private Officiant officiant;
    private List<Item> items;
    private double totalCost;

    public Order(Officiant officiant, List<Item> items, double totalCost) {
        this.officiant = officiant;
        this.items = items;
        this.totalCost = totalCost;
    }

    public Order(Officiant officiant, List<Item> items) {
        this(officiant, items, items.stream().mapToDouble(Item::getCost).sum());
    }

    public Officiant getOfficiant() {
        return officiant;
    }

    public void setOfficiant(Officiant officiant) {
        this.officiant = officiant;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("order: ").append("\n");
        sb.append(officiant).append("\n");
        for (Item item : items) {
            sb.append(item).append("\n");
        }
        sb.append("totalcost: ").append(totalCost).append("\n");
        return sb.toString();
    }
}
