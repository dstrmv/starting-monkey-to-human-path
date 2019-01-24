package PO61.Bulychev.wdad.data.managers;

import PO61.Bulychev.wdad.data.storage.DataSourceFactory;
import PO61.Bulychev.wdad.learn.xml.Item;
import PO61.Bulychev.wdad.learn.xml.Officiant;
import PO61.Bulychev.wdad.learn.xml.Order;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class JdbcDataManager implements DataManager {

    private DataSource source;
    private Connection con;

    public JdbcDataManager() throws SQLException {
        source = DataSourceFactory.createDataSource();
        con = source.getConnection();
        //con.setSchema("restaurant");
    }

    @Override
    public double earningsTotal(Officiant officiant, LocalDate date) {

        double result = 0;

        String dateStr = date.format(DateTimeFormatter.ISO_DATE);
        Statement stmt = null;
        String query = "SELECT sum(cost*quantity) as total " +
                "FROM items_orders " +
                "INNER JOIN items i on items_orders.items_id = i.id " +
                "INNER JOIN orders o on items_orders.orders_id = o.id " +
                "INNER JOIN officiants o2 on o.officiant_id = o2.id " +
                "WHERE first_name = '" + officiant.getFirstName() + "' && second_name = '" + officiant.getSecondName() + "'" +
                "&& date = '" + dateStr + "'" +
                "GROUP BY first_name";
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                result = rs.getDouble(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public void removeDay(LocalDate date) {
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.addBatch(String.format("DELETE FROM orders WHERE date = '%s'", date.format(DateTimeFormatter.ISO_DATE)));
            stmt.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void changeOfficiantName(Officiant oldOfficiant, Officiant newOfficiant) {
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.addBatch(String.format("UPDATE officiants SET first_name = '%s', second_name = '%s' WHERE first_name = '%s' && second_name = '%s'",
                    newOfficiant.getFirstName(), newOfficiant.getSecondName(), oldOfficiant.getFirstName(), oldOfficiant.getSecondName()
            ));
            stmt.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public List<Order> getOrders(LocalDate date) {

        Map<Integer, Order> map = new TreeMap<>();

        String dateStr = "'" + date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth() + "'";
        Statement stmt = null;
        String query = "SELECT items_orders.orders_id, quantity, name, cost, date, first_name, second_name " +
                "FROM restaurant.items_orders " +
                "INNER JOIN items i on items_orders.items_id = i.id " +
                "INNER JOIN orders o on items_orders.orders_id = o.id " +
                "INNER JOIN officiants o2 on o.officiant_id = o2.id " +
                "WHERE date = " + dateStr;

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            Order order;
            Officiant officiant;
            Item item;

            while (rs.next()) {

                int order_id = rs.getInt(1);

                if (!map.containsKey(order_id)) {
                    map.put(order_id, new Order());
                }

                order = map.get(order_id);
                String itemName = rs.getString("name");
                double itemCost = rs.getDouble("cost");
                officiant = new Officiant(rs.getString("first_name"), rs.getString("second_name"));
                item = new Item(itemName, itemCost);
                int itemCount = rs.getInt("quantity");
                for (int i = 0; i < itemCount; i++) {
                    order.addItem(item);
                }
                order.setOfficiant(officiant);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        List<Order> result = new ArrayList<>(map.values());
        return result;
    }


    @Override
    public LocalDate lastOfficiantWorkDate(Officiant officiant) {
        LocalDate result = null;

        Statement stmt = null;
        String query = "SELECT MAX(date) as last " +
                "FROM items_orders " +
                "INNER JOIN items i on items_orders.items_id = i.id " +
                "INNER JOIN orders o on items_orders.orders_id = o.id " +
                "INNER JOIN officiants o2 on o.officiant_id = o2.id " +
                "WHERE first_name = '" + officiant.getFirstName() + "' && second_name = '" + officiant.getSecondName() + "' " +
                "GROUP BY first_name ";

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                result = LocalDate.parse(rs.getString(1), DateTimeFormatter.ISO_DATE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (result == null) {
            throw new RuntimeException("officiant dont have work date");
        }

        return result;
    }


}


