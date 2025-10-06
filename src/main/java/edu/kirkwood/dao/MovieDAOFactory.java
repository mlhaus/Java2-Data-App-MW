package edu.kirkwood.dao;

import edu.kirkwood.dao.impl.XmlMovieDAO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MovieDAOFactory {
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

    /**
     * To get the data access object specified in the application.properties file
     * @return a MovieDAO (XMLMovieDAO, MySQLMovieDAO, etc.)
     */
    public static MovieDAO getMovieDAO() {
        String sourceType =  properties.getProperty("datasource.type");
        if(sourceType == null || sourceType.isEmpty()) {
            throw new RuntimeException("Unknown datasource type");
        }
        switch (sourceType.toUpperCase()) {
            case "XML":
                String apiURL =  properties.getProperty("xml.apiurl");
                if(apiURL == null || apiURL.isEmpty()) {
                    throw new  RuntimeException("xml.apiurl is empty");
                }
                return new XmlMovieDAO(apiURL);
//            case "MYSQL":
//                break;
//            case "JSON":
//                break;
//            case "MONGODB":
//                break;
        }
        return null;
    }
}
