package ru.feib.popov.databaseTester;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
*
* @author cgg
*/
public class DbEngine {
    public static void main(String[] args) {
        try(Connection conn = DbEngine.getConnection()) {
        } catch (Exception ex) {
            
        }
        
    }
    
    public static Connection getConnection()throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String myDB ="jdbc:oracle:oci:@TREASURY";
        return DriverManager.getConnection(myDB, "test", "Aa123456");
    }
}