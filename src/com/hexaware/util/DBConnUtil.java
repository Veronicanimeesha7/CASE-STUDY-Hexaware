package com.hexaware.util;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DBConnUtil {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Load db.properties from classpath
            InputStream input = DBConnUtil.class.getClassLoader().getResourceAsStream("db.properties");

            if (input == null) {
                System.out.println("Sorry, unable to find db.properties");
                return null;
            }

            Properties props = new Properties();
            props.load(input);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.username");
            String pass = props.getProperty("db.password");

            conn = java.sql.DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
