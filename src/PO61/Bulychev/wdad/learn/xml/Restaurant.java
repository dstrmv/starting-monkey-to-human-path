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
        for (RestDate restDate : dates) {
            if (restDate.getDate().equals(localDate)) {
                dates.remove(restDate);
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
}
