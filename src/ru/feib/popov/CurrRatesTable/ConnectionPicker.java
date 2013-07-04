package ru.feib.popov.CurrRatesTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public final class ConnectionPicker {
    public static Properties createPropsShort(
            String database, String user, String password) {
        Properties props = new Properties();
        props.put("database", database);
        props.put("user", user);
        props.put("password", password);
        return props;
    }
    
    public static Connection getConnection(
            String driverFullName, 
            String db, 
            Properties props) 
            throws SQLException, ClassNotFoundException {
        
        loadDriver(driverFullName);
        Connection conn = DriverManager.getConnection(db, props);
        return conn;
    }
    
    private static Class loadDriver(String driverFullName) 
            throws ClassNotFoundException {
        Class c = Class.forName(driverFullName);
        return c;
    }
}