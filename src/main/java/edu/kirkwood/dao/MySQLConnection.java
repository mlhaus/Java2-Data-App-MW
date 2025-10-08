package edu.kirkwood.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnection {
    private static Properties properties = new Properties();

    static {
        try(InputStream input = MovieDAOFactory.class.getClassLoader()
                .getResourceAsStream("application.properties");) {
            if(input == null) {
                throw new RuntimeException("application.properties file not found");
            }
            properties.load(input);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String connectionString =  properties.getProperty("mysql.connectionString");
        if(connectionString == null || connectionString.isEmpty()) {
            throw new RuntimeException("MySQL Connection String cannot be found.");
        }

        return null;
    }
}
