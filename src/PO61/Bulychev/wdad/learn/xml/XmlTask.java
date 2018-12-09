package PO61.Bulychev.wdad.learn.xml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class XmlTask {

    private Restaurant restaurant;
    private String path;

    public XmlTask(String path) {

        this.path = path;
        //todo уж коли сделал - юзай unMarshal() для создания ресторана
        //todo done
        File file = new File(path);
        System.setProperty("javax.xml.accessExternalDTD", "all");
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Restaurant.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            restaurant = (Restaurant) unmarshaller.unmarshal(file);
            System.out.println(restaurant);
            System.out.println();
            System.out.println();
            System.out.println();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public double earningsTotal(String officiantSecondName, Calendar calendar) {

        double earningsTotal = 0;

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        LocalDate date = LocalDate.of(year, month, day);

        for (RestDate restDate : restaurant.getDates()) {
            if (restDate.getDate().equals(date)) {
                for (Order order : restDate.getOrders()) {
                    if (order.getOfficiant().getSecondName().equals(officiantSecondName)) {
                        earningsTotal += order.getTotalCost();
                    }
                }
            }
        }

        return earningsTotal;
    }

    public void removeDay(Calendar calendar) {

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        LocalDate localDate = LocalDate.of(year, month, day);

        restaurant.removeDate(localDate);
        File file = new File(path);

        marshal(file);
    }

    public void changeOfficiantName(String oldFirstName, String oldSecondName,
                                    String newFirstName, String newSecondName) {

        restaurant.changeOfficiantName(oldFirstName, oldSecondName, newFirstName, newSecondName);
        File file = new File(path);

        marshal(file);

    }

    private void marshal(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(
                    Restaurant.class,
                    RestDate.class,
                    Officiant.class,
                    Order.class,
                    Item.class
            );
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            marshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders",
                    "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<!DOCTYPE restaurant SYSTEM \".\\restaurant.dtd\">");
            marshaller.marshal(restaurant, file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}
