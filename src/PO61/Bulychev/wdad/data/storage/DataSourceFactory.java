package PO61.Bulychev.wdad.data.storage;

import PO61.Bulychev.wdad.data.managers.PreferencesManager;
import PO61.Bulychev.wdad.utils.PreferencesManagerConstants;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public class DataSourceFactory { 

    public static DataSource createDataSource() {

        PreferencesManager pm = PreferencesManager.getInstance();
        return createDataSource(pm.getProperty(PreferencesManagerConstants.DATASOURCE_CLASSNAME),
                pm.getProperty(PreferencesManagerConstants.DATASOURCE_DRIVERTYPE),
                pm.getProperty(PreferencesManagerConstants.DATASOURCE_HOSTNAME),
                pm.getProperty(PreferencesManagerConstants.REGISTRY_PORT),
                pm.getProperty(PreferencesManagerConstants.DATASOURCE_DBNAME),
                pm.getProperty(PreferencesManagerConstants.DATASOURCE_USER),
                pm.getProperty(PreferencesManagerConstants.DATASOURCE_PASS)
        );
    }

    public static DataSource createDataSource(String className, String driverType, String host,
                                              String port, String dbName, String user, String password) {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("ClassNotFoundException", e);
        }

        MysqlDataSource source = new MysqlDataSource();
        //jdbc:mysql://localhost:3306/restaurant
        // с портом не работает
        String url = "jdbc:" + driverType + "://" + host
               // + ":" + port
                + "/" + dbName;
        source.setURL(url);

        source.setPort(3306);
        source.setUser(user);
        source.setPassword(password);
        return source;
    }

}
