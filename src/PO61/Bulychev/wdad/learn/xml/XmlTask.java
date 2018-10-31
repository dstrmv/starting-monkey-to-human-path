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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class XmlTask {

    Restaurant restaurant;

    public XmlTask(String path) throws ParserConfigurationException, IOException, SAXException {

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

            LocalDate restDate = LocalDate.of(year, month, day);

        }

        //System.out.println(restDates.size());

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
