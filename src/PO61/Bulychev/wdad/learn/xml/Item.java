package PO61.Bulychev.wdad.learn.xml;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.PROPERTY)
public class Item {
    private static final String NO_NAME = "";

    private String name;

    private double cost;

    public Item() {
        this.name = NO_NAME;
        this.cost = 0;
    }

    public Item(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    @XmlTransient
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute(name = "cost")
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @XmlAttribute(name = "name")
    private String getEmptyNameToNull() {
        return name.equals("") ? null : name;
    }

    private void setEmptyNameToNull(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!name.equals("")) {
            sb.append("item ").append(this.name).append(", ");
        }
        sb.append("cost = ").append(cost);
        return sb.toString();
    }
}
