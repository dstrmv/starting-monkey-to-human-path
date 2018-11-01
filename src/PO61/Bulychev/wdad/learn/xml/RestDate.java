package PO61.Bulychev.wdad.learn.xml;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@XmlAccessorType(XmlAccessType.FIELD)
public class RestDate {
    @XmlTransient
    private LocalDate date;
    @XmlElement(name = "order")
    private List<Order> orders;

    public RestDate() {
        date = LocalDate.now();
        orders = new ArrayList<>();
    }

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

    @XmlAttribute(name = "day")
    public int getDay() {
        return date.getDayOfMonth();
    }

    @XmlAttribute(name = "month")
    public int getMonth() {
        return date.getMonthValue();
    }
    @XmlAttribute(name = "year")
    public int getYear() {
        return date.getYear();
    }

}
