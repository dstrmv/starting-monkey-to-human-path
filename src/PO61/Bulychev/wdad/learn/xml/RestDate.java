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
    //private int year;
    //private int month;
    //private int day;

    public RestDate() {
        this(LocalDate.now(), new ArrayList<>());
    }

    public RestDate(LocalDate date, List<Order> orders) {

        this.date = date;
        this.orders = orders;
        //  this.year = date.getYear();
        //this.month = date.getMonthValue();
        // this.day = date.getDayOfMonth();
    }

    public RestDate(LocalDate date) {
        this(date, new ArrayList<>());
    }

    //todo в следующих двух методах должны быть логика либо создания LocalDate из дня, месяца, года, либо изменение дня месяца и года по значению LocalDate
    // todo запилил сеттеры
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDate(int year, int month, int day) {
        this.date = LocalDate.of(year, month, day);
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

    // todo этb 3 метода делаются приватными
    @XmlAttribute(name = "day")
    private int getDay() {
        return date.getDayOfMonth();
    }

    private void setDay(int day) {
        this.date = this.date.withDayOfMonth(day);
        //this.day = day;
    }

    @XmlAttribute(name = "month")
    private int getMonth() {
        return date.getMonthValue();
    }

    private void setMonth(int month) {
        this.date = this.date.withMonth(month);
        //this.month = month;
    }

    @XmlAttribute(name = "year")
    private int getYear() {
        return date.getYear();
    }

    private void setYear(int year) {
        this.date = this.date.withYear(year);
        //this.year = year;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RestDate)) {
            return false;
        }

        RestDate rd = (RestDate) obj;
        return this.getDate().equals(rd.date);
    }
}
