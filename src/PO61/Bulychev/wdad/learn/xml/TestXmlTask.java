package PO61.Bulychev.wdad.learn.xml;

import java.util.Calendar;

public class TestXmlTask {
    public static void main(String[] args) throws Exception {

        XmlTask task = new XmlTask("src/PO61/Bulychev/wdad/learn/xml/restFirstCorrect.xml");

        Calendar calendar =  Calendar.getInstance();
        calendar.set(2010, 01, 02);
        double earn = task.earningsTotal("sidorov", calendar);
        System.out.println(earn);
        //try {
       //     XmlTask task2 = new XmlTask("src/PO61/Bulychev/wdad/learn/xml/restSecondIncorrect.xml");
      //  } catch (Exception e) {
      //      System.out.println("EXCEPTION: ERRORS IN XML");
      //  }

    }
}
