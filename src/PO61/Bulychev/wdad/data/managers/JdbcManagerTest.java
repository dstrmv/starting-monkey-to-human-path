package PO61.Bulychev.wdad.data.managers;

import PO61.Bulychev.wdad.learn.xml.Officiant;

import java.sql.SQLException;
import java.time.LocalDate;

public class JdbcManagerTest {
    public static void main(String[] args) throws SQLException {
        JdbcDataManager manager = new JdbcDataManager();
        LocalDate date = LocalDate.of(2019, 1, 9);
        //List<Order> orders = manager.getOrders(date);
        //orders.forEach(System.out::println);
        //manager.test();
        Officiant officiant = new Officiant("ivan", "ivanov");
        Officiant newOfficiant = new Officiant("karl", "karlov");
        manager.changeOfficiantName(officiant, newOfficiant);
    }
}
