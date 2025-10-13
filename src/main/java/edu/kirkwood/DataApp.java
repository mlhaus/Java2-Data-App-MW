package edu.kirkwood;

import edu.kirkwood.dao.MovieDAO;
import edu.kirkwood.dao.MovieDAOFactory;
import edu.kirkwood.dao.impl.MySQLMovieDAO;
import edu.kirkwood.dao.impl.XmlMovieDAO;
import edu.kirkwood.model.Movie;
import edu.kirkwood.model.xml.MovieSearchResult;

import java.util.ArrayList;
import java.util.List;

public class DataApp {
    public static void main(String[] args) {
        try {
            MovieDAO movieDAO = MovieDAOFactory.getMovieDAO();
            // Prompt user for a movie title
            String search = "batman";
            List<Movie> movies = new ArrayList<>();
            if(movieDAO instanceof XmlMovieDAO) {
                movies.addAll(((XmlMovieDAO)movieDAO).search(search));

            } else if(movieDAO instanceof MySQLMovieDAO) {
                movies.addAll(((MySQLMovieDAO)movieDAO).search(search));
            }
            movies.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
