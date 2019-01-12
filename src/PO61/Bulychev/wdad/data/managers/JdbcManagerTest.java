package PO61.Bulychev.wdad.data.managers;

import PO61.Bulychev.wdad.learn.xml.Order;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class JdbcManagerTest {
    public static void main(String[] args) throws SQLException {
        JdbcDataManager manager = new JdbcDataManager();
        LocalDate date = LocalDate.of(2019, 1, 9);
        List<Order> orders = manager.getOrders(date);
        orders.forEach(System.out::println);
        //manager.test();

    }
}
