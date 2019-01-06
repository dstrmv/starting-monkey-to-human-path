package PO61.Bulychev.wdad.learn.xml;

import java.util.Calendar;

public class TestXmlTask {
    public static void main(String[] args) throws Exception {

        XmlTask task = new XmlTask("src/PO61/Bulychev/wdad/learn/xml/restFirstCorrect.xml");

        Calendar calendar =  Calendar.getInstance();
        calendar.set(2010, 1, 2);

        task.removeDay(calendar);
        task.changeOfficiantName("ivan", "ivanov", "sidor", "sidorov");

        //try {
        //     XmlTask task2 = new XmlTask("src/PO61/Bulychev/wdad/learn/xml/restSecondIncorrect.xml");
        //  } catch (Exception e) {
        //      System.out.println("EXCEPTION: ERRORS IN XML");
        //  }

    }
}
