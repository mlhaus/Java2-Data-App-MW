package edu.kirkwood;

import edu.kirkwood.dao.MovieDAO;
import edu.kirkwood.dao.MovieDAOFactory;
import edu.kirkwood.dao.impl.JsonMovieDAO;
import edu.kirkwood.dao.impl.MySQLMovieDAO;
import edu.kirkwood.dao.impl.XmlMovieDAO;
import edu.kirkwood.model.Movie;
import edu.kirkwood.model.xml.MovieSearchResult;
import edu.kirkwood.view.Animator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static edu.kirkwood.view.Helpers.printList;
import static edu.kirkwood.view.UIUtility.pressEnterToContinue;
import static edu.kirkwood.view.UIUtility.printMenu;
import static edu.kirkwood.view.UserInput.getInt;
import static edu.kirkwood.view.UserInput.getString;

public class DataApp {
    public static void main(String[] args) {
        // Prompt user for a movie title
        String search = getString("Enter a movie title", true);
        List<Movie> movies = getResults(search);
        sortByMenu(movies, search);
    }

    public static void sortByMenu(List<Movie> movies, String title) {
        String[] menuItems = {
                "No sort",
                "Sort by ID (Low to High)",
                "Sort by ID (High to Low)",
                "Sort by Title (A to Z)",
                "Sort by Title (Z to A)",
                "Sort by Year (Old to New)",
                "Sort by Year (New to Old)",
                "Quit"
        };
        while(true) {
            printMenu("Sort By Menu", menuItems);
            int choice = getInt("Choose an option", true,1, menuItems.length);
            switch(choice) {
                case 1:
                    break;
                case 2:
                    Collections.sort(movies);
                    break;
                case 3:
                    Collections.sort(movies.reversed());
                    break;
                case 4:
                    Collections.sort(movies, Movie.compareTitle);
                    break;
                case 5:
                    Collections.sort(movies, Movie.compareTitle.reversed());
                    break;
                case 6:
                    Collections.sort(movies, Movie.compareYear);
                    break;
                case 7:
                    Collections.sort(movies, Movie.compareYear.reversed());
                    break;
                default:
                    return;
            } // end switch
            printList(menuItems[choice - 1], movies, 10, 0);
            pressEnterToContinue();
        } // end loop
    } // end show method

    public static List<Movie> getResults(String search) {
        try {
            MovieDAO movieDAO = MovieDAOFactory.getMovieDAO();
            List<Movie> movies = new ArrayList<>();
            Animator animator = new Animator("Loading movies, please wait");
            Thread animatorThread = new Thread(animator);
            animatorThread.start();

            if(movieDAO instanceof XmlMovieDAO) {
                movies.addAll(((XmlMovieDAO)movieDAO).search(search));
            } else if(movieDAO instanceof MySQLMovieDAO) {
                movies.addAll(((MySQLMovieDAO)movieDAO).search(search));
            } else if(movieDAO instanceof JsonMovieDAO) {
                movies.addAll(((JsonMovieDAO)movieDAO).search(search));
            }

            animatorThread.interrupt();

            return movies;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

}
