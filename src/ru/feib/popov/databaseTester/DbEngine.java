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
    /**
    * Connect to database
    * @return Connection to database
    * @throws java.lang.Exception
    */
    public Connection dbConnection()throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String myDB ="jdbc:oracle:oci:@TREASURY";
        return DriverManager.getConnection(myDB, "test", "Aa123456");
    }

    /**
    * This method will load vector of vector of string and load all the data in
    * the vector
    * @return vector of vector of string
    * @throws java.lang.Exception
    */
    public ArrayList getEmployee()throws Exception {
        ArrayList<ArrayList<String>> employeeVector = 
                new ArrayList<>();

        Connection conn = dbConnection();
        PreparedStatement pre = conn.prepareStatement("select * from employee");

        ResultSet rs = pre.executeQuery();

        while(rs.next()) {
            ArrayList<String> employee = new ArrayList<>();
            employee.add(rs.getString(1)); //Empid
            employee.add(rs.getString(2)); //name
            employee.add(rs.getString(3)); //position
            employee.add(rs.getString(4)); //department
            employeeVector.add(employee);
        }

        /*Close the connection after use (MUST)*/
        assert conn == null;
        conn.close();

        return employeeVector;
    }
}