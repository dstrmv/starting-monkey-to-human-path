package PO61.Bulychev.wdad.learn.xml;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Order implements Serializable {
    @XmlElement
    private Officiant officiant;
    @XmlElement(name = "item")
    private List<Item> items;
    @XmlElement(name = "totalcost")
    private double totalCost;

    public Order() {
        this.officiant = null;
        items = new ArrayList<>();
        totalCost = 0;
    }

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
        totalCost = items.stream().mapToDouble(Item::getCost).sum();
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

    public void checkTotalCost() {
        totalCost = items.stream().mapToDouble(Item::getCost).sum();
    }

}
