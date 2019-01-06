package PO61.Bulychev.wdad.learn.xml;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Restaurant {

    @XmlElement(name = "date")
    private List<RestDate> dates;

    public Restaurant() {
        this.dates = new ArrayList<>();
    }

    public Restaurant(List<RestDate> dates) {
        this.dates = dates;
    }

    public List<RestDate> getDates() {
        return dates;
    }

    public void setDates(List<RestDate> dates) {
        this.dates = dates;
    }

    public void addDate(RestDate restDate) {
        dates.add(restDate);
    }

    public void removeDate(LocalDate localDate) {
        dates.remove(new RestDate(localDate));
    }

    public void changeOfficiantName(String oldFirstName, String oldSecondName,
                                    String newFirstName, String newSecondName) {
        for (RestDate date: dates) {
            for (Order o: date.getOrders()) {
                Officiant of = o.getOfficiant();
                if (of.getFirstName().equals(oldFirstName) && of.getSecondName().equals(oldSecondName)) {
                    of.setFirstName(newFirstName);
                    of.setSecondName(newSecondName);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (RestDate date : dates) {
            sb.append(date).append("\n");
        }
        return sb.toString();
    }

    public void checkTotalCost() {
        for (RestDate date: dates) {
            for (Order order: date.getOrders()) {
                order.checkTotalCost();
            }
        }
    }
}
