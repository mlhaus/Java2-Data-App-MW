package edu.kirkwood.dao.impl;

import edu.kirkwood.dao.MovieDAO;
import edu.kirkwood.model.Movie;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static edu.kirkwood.dao.MySQLConnection.getConnection;

public class MySQLMovieDAO implements MovieDAO {

    @Override
    public List<Movie> search(String title) {
        try(Connection connection = getConnection()) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return List.of();
    }
}
