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
    String originalTitle;
    String overview;

    public Movie(JSONObject movieObject) throws JSONException {
        this.posterPath = movieObject.getString("poster_path");
        this.originalTitle = movieObject.getString("original_title");
        this.overview = movieObject.getString("overview");
    }

    public String getPosterPath() {
        return String.format("%s/%s", "https://image.tmdb.org/t/p/w342", posterPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public static List<Movie> fromJSONArray(JSONArray jsonArray) throws JSONException {

        LinkedList<Movie> movies = new LinkedList<>();

        for( int movieIndex = 0; movieIndex < jsonArray.length(); movieIndex++ ) {
            movies.add( new Movie(jsonArray.getJSONObject(movieIndex)));
        }

        return movies;

    }

}
