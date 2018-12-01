package PO61.Bulychev.wdad.learn.xml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
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

    public XmlTask(String path) throws ParserConfigurationException, IOException, SAXException {

        this.path = path;
        //todo уж коли сделал - юзай unMarshal() для создания ресторана
        restaurant = new Restaurant();
        List<Item> itemList;
        List<Order> orderList;
        List<RestDate> dateList;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(path);

        NodeList dateElems = document.getDocumentElement().getElementsByTagName("date");

        //dates cycle
        for (int i = 0; i < dateElems.getLength(); i++) {
            Element date = (Element) dateElems.item(i);

            int day = Integer.parseInt(date.getAttribute("day"));
            int month = Integer.parseInt(date.getAttribute("month"));
            int year = Integer.parseInt(date.getAttribute("year"));

            LocalDate restLocalDate = LocalDate.of(year, month, day);
            NodeList orders = date.getElementsByTagName("order");

            orderList = new ArrayList<>();

            // orders cycle
            for (int j = 0; j < orders.getLength(); j++) {
                Element orderElement = (Element) orders.item(j);

                NodeList items = orderElement.getElementsByTagName("item");
                Element officiantElement = (Element) orderElement.getElementsByTagName("officiant").item(0);

                String officiantFirstName = officiantElement.getAttribute("firstname");
                String officiantSecondName = officiantElement.getAttribute("secondname");
                Officiant officiant = new Officiant(officiantFirstName, officiantSecondName);

                itemList = new ArrayList<>();

                // items cycle
                for (int k = 0; k < items.getLength(); k++) {
                    Element item = (Element) items.item(k);
                    String itemName = item.getAttribute("name");
                    double itemCost = Double.parseDouble(item.getAttribute("cost"));
                    itemList.add(new Item(itemName, itemCost));
                }

                //NodeList totalCostList = orderElement.getElementsByTagName("totalcost");
                Order order;

                //if (totalCostList.getLength() != 0) {
                //    Element totalCostElement = (Element) totalCostList.item(0);
                //    double totalCost = Double.parseDouble(totalCostElement.getTextContent());
                //    order = new Order(officiant, itemList, totalCost);
               // } else {
                    order = new Order(officiant, itemList);
               // }
                orderList.add(order);
            }

            RestDate restDate = new RestDate(restLocalDate, orderList);
            restaurant.addDate(restDate);

        }
    }

    public double earningsTotal(String officiantSecondName, Calendar calendar) {

        double earningsTotal = 0;

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        LocalDate date = LocalDate.of(year, month, day);

        for (RestDate restDate: restaurant.getDates()) {
            if (restDate.getDate().equals(date)) {
                for (Order order: restDate.getOrders()) {
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
