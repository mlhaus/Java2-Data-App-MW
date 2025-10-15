package edu.kirkwood.dao.impl;

import edu.kirkwood.dao.MovieDAO;
import edu.kirkwood.model.Movie;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class JsonMovieDAO implements MovieDAO {
    private String apiURL;
    private String apiKey;
    public JsonMovieDAO(String apiURL, String apiKey) {
        this.apiURL = apiURL;
        this.apiKey = apiKey;
    }

    public String fetchRawData(String title) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(apiURL + "query=" + title) // Todo: add pagination
                .get()
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("accept", "application/json")
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String responseBody = "";
        try {
            responseBody = response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseBody;
    }

    /**
     * Retrieves all movies from the data source that matches the title
     * @param title The movie title a user is searching for
     * @return A List<Movie> movies that matches the search title
     */
    @Override
    public List<Movie> search(String title) {
        System.out.println(fetchRawData(title));
        return List.of();
    }
}
