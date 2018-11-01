package PO61.Bulychev.wdad.learn.xml;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class RestDate {

    private LocalDate date;
    private List<Order> orders;

    public RestDate(LocalDate date, List<Order> orders) {
        this.date = date;
        this.orders = orders;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("date: ").append(date.toString()).append("\n");
        for (Order order : orders) {
            sb.append(order).append("\n");
        }
        return sb.toString();
    }
}
