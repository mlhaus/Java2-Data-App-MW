package edu.kirkwood.dao.impl;

import com.google.gson.*;
import edu.kirkwood.dao.MovieDAO;
import edu.kirkwood.model.Movie;
import edu.kirkwood.model.json.TmdbMovieResponse;
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
     * Outputs an unformatted json string into a human-readable format
     * @param json A valid json string
     */
    public void prettyPrint(String json) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        JsonElement jsonElement = new JsonParser().parse(json);
        String formattedJson = gson.toJson(jsonElement);
        System.out.println(formattedJson);
    }

    /**
     * Retrieves all movies from the data source that matches the title
     * @param title The movie title a user is searching for
     * @return A List<Movie> movies that matches the search title
     */
    @Override
    public List<Movie> search(String title) {
        String rawData = fetchRawData(title);
        Gson gson = new GsonBuilder().create();
        TmdbMovieResponse movieResponse = null;
        try {
            movieResponse = gson.fromJson(rawData, TmdbMovieResponse.class);
        } catch(JsonSyntaxException e) {
            throw new RuntimeException(e);
        }
        movieResponse.getResults().forEach(System.out::println);
        return List.of();
    }
}
