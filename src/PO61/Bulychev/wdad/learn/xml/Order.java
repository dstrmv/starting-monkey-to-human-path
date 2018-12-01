package PO61.Bulychev.wdad.learn.xml;


import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"officiant", "items", "totalCost"})
public class Order {

    private Officiant officiant;
    private List<Item> items;

    private double totalCost;

    public Order() {
        this(null, new ArrayList<>());
    }

    public Order(Officiant officiant, List<Item> items) {
        this.officiant = officiant;
        this.items = items;
        totalCost = items.stream().mapToDouble(Item::getCost).sum();
    }

    @XmlElement(name = "officiant")
    public Officiant getOfficiant() {
        return officiant;
    }

    public void setOfficiant(Officiant officiant) {
        this.officiant = officiant;
    }

    @XmlElement(name = "item")
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
        this.totalCost = items.stream()
                .mapToDouble(Item::getCost)
                .sum();
    }

    @XmlElement(name = "totalcost")
    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;

    }

    public void addItem(Item item) {
        this.items.add(item);
        totalCost += item.getCost();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("order: ").append("\n");
        sb.append(officiant).append("\n");
        for (Item item : items) {
            sb.append(item).append("\n");
        }
        sb.append("totalcost: ").append(getTotalCost()).append("\n");
        return sb.toString();
    }
}
