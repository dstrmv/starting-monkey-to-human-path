package PO61.Bulychev.wdad.learn.xml;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

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
}
