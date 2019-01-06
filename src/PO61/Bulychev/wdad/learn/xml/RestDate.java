package PO61.Bulychev.wdad.learn.xml;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class RestDate implements Serializable {
    @XmlTransient
    private LocalDate date;
    @XmlElement(name = "order")
    private List<Order> orders;

    public RestDate() {
        this(LocalDate.now(), new ArrayList<>());
    }

    public RestDate(LocalDate date, List<Order> orders) {

        this.date = date;
        this.orders = orders;
    }

    public RestDate(LocalDate date) {
        this(date, new ArrayList<>());
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

    public void setDay(int day) {
        this.date = this.date.withDayOfMonth(day);
    }

    public void setMonth(int month) {
        this.date = this.date.withMonth(month);
    }

    public void setYear(int year) {
        this.date = this.date.withYear(year);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RestDate)) {
            return false;
        }

        RestDate rd = (RestDate) obj;
        if (this.getDate().equals(rd.date)) {
            return true;
        }
        return false;
    }

}
