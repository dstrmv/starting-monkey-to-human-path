package PO61.Bulychev.wdad.learn.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Item {
    private static final String NO_NAME = "";
    @XmlAttribute
    private String name;
    @XmlAttribute
    private double cost;

    public Item() {
        this.name = NO_NAME;
        this.cost = 0;
    }

    public Item(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("item ");
        if (name.equals(NO_NAME)) {
            sb.append("unknown");
        } else {
            sb.append(name);
        }
        sb.append(", ").append("cost ").append(cost);
        return sb.toString();
    }
}
