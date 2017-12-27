package com.swtstylez.flicks.domain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jndukuba on 12/13/17.
 */

public class Movie {

    String posterPath;
    String backdropPath;
    String originalTitle;
    String overview;
    double voteAverage;

    public Movie(JSONObject movieObject) throws JSONException {
        this.posterPath = movieObject.getString("poster_path");
        this.backdropPath = movieObject.getString("backdrop_path");
        this.originalTitle = movieObject.getString("original_title");
        this.overview = movieObject.getString("overview");
        this.voteAverage = movieObject.getDouble("vote_average");
    }

    public static List<Movie> fromJSONArray(JSONArray jsonArray) throws JSONException {

        LinkedList<Movie> movies = new LinkedList<>();

        for (int movieIndex = 0; movieIndex < jsonArray.length(); movieIndex++) {
            movies.add(new Movie(jsonArray.getJSONObject(movieIndex)));
        }

        return movies;

    }

    public String getPosterPath() {
        return String.format("%s/%s", "https://image.tmdb.org/t/p/w500", posterPath);
    }

    public String getBackdropPath() {
        return String.format("%s/%s", "https://image.tmdb.org/t/p/w780", backdropPath);
    }

    public String getOriginalBackdropPath() {
        return String.format("%s/%s", "https://image.tmdb.org/t/p/original", backdropPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

}
