package PO61.Bulychev.wdad.learn.xml;

public class Item {
    private static final String NO_NAME = "";

    private String name;
    private double cost;

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
