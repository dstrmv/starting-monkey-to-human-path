package PO61.Bulychev.wdad.data.storage;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TestDataStorage {
    public static void main(String[] args) throws SQLException {
        DataSource source = DataSourceFactory.createDataSource();
        Connection connection = source.getConnection();


    }
}
