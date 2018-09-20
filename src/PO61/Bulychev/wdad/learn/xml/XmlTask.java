package PO61.Bulychev.wdad.learn.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class XmlTask {

    private class RestDate {

        private int day;
        private int month;
        private int year;

        public RestDate(int day, int month, int year) {
            this.day = day;
            this.month = month;
            this.year = year;
        }

        public int getDay() {
            return day;
        }

        public int getMonth() {
            return month;
        }

        public int getYear() {
            return year;
        }

        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append(day).append('.').append(month).append('.').append(year);
            return sb.toString();
        }
    }

    List<RestDate> restDates;

    public XmlTask(String path) throws ParserConfigurationException, IOException, SAXException {

        restDates = new ArrayList<>();

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(path);

        NodeList dateElems = document.getDocumentElement().getElementsByTagName("date");
        for (int i = 0; i < dateElems.getLength(); i++) {
            Node date = dateElems.item(i);
            NamedNodeMap attributes = date.getAttributes();
            int day = Integer.parseInt(attributes.getNamedItem("day").getNodeValue());
            int month = Integer.parseInt(attributes.getNamedItem("month").getNodeValue());
            int year = Integer.parseInt(attributes.getNamedItem("year").getNodeValue());

            restDates.add(new RestDate(day, month, year));
        }

        System.out.println(restDates.size());

    }

    public int earningsTotal(String officiantSecondName, Calendar calendar) {

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);



        return 0;
    }

    public void removeDay(Calendar calendar) {

    }

    public void changeOfficiantName(String oldFirstName, String oldSecondName,
                                    String newFirstName, String newSecondName) {

    }

}
