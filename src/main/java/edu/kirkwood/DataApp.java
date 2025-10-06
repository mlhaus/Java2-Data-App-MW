package edu.kirkwood;

import edu.kirkwood.dao.MovieDAO;
import edu.kirkwood.dao.MovieDAOFactory;
import edu.kirkwood.dao.impl.XmlMovieDAO;
import edu.kirkwood.model.xml.MovieSearchResult;

import java.util.List;

public class DataApp {
    public static void main(String[] args) {
        try {
            MovieDAO movieDAO = MovieDAOFactory.getMovieDAO();
            // Prompt user for a movie title
            String search = "Scream";
            if(movieDAO instanceof XmlMovieDAO) {
                List<MovieSearchResult> movies = ((XmlMovieDAO)movieDAO).search(search);
                movies.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
