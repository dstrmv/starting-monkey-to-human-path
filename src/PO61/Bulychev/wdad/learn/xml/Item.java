package PO61.Bulychev.wdad.learn.xml;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.PROPERTY)
public class Item implements Serializable {
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
        setName(name);
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
