package edu.kirkwood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {
    private Movie m1;
    private Movie m2;
    private Movie m3;
    private List<Movie> movies;

    @BeforeEach
    void setUp() {
        m1 = new Movie("1010", "B", 2025, "Undefined");
        m2 = new Movie("11", "a", 2024, "Undefined");
        m3 = new Movie("11", "C", 2023, "Undefined");
        movies = new ArrayList<>();
        movies.add(m1);
        movies.add(m2);
        movies.add(m3);
    }

    @Test
    void compareToNegative() {
        // Act
        int actual =  m2.compareTo(m1);
        // Assert
        assertTrue(actual < 0);
    }

    @Test
    void compareToZero() {
        // Arrange
        int expected = 0;
        // Act
        int actual =  m2.compareTo(m3);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void compareToPositive() {
        // Arrange
        int expected = 1;
        // Act
        int actual =  m1.compareTo(m3);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void sortNaturalOrder() {
        // arrange
        Collections.sort(movies);
        // assert
        assertEquals(movies.get(0), m2);
        assertEquals(movies.get(1), m3);
        assertEquals(movies.get(2), m1);
    }

    @Test
    void sortNaturalOrderReversed() {
        // arrange
        Collections.sort(movies.reversed());
        // assert
        assertEquals(movies.get(0), m1);
        assertEquals(movies.get(1), m2);
        assertEquals(movies.get(2), m3);
    }

    @Test
    void sortByTitleAtoZ() {
        // arrange
        Collections.sort(movies, Movie.compareTitle);
        // assert
        assertEquals(movies.get(0), m2);
        assertEquals(movies.get(1), m1);
        assertEquals(movies.get(2), m3);
    }

    @Test
    void sortByTitleZtoA() {
        // arrange
        Collections.sort(movies, Movie.compareTitle.reversed());
        // assert
        assertEquals(movies.get(0), m3);
        assertEquals(movies.get(1), m1);
        assertEquals(movies.get(2), m2);
    }

    @Test
    void sortByYearAscending() {
        // arrange
        Collections.sort(movies, Movie.compareYear);
        // assert
        assertEquals(movies.get(0), m3);
        assertEquals(movies.get(1), m2);
        assertEquals(movies.get(2), m1);
    }

    @Test
    void sortByYearDescending() {
        // arrange
        Collections.sort(movies, Movie.compareYear.reversed());
        // assert
        assertEquals(movies.get(0), m1);
        assertEquals(movies.get(1), m2);
        assertEquals(movies.get(2), m3);
    }
}