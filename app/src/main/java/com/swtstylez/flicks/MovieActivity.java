package com.swtstylez.flicks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.swtstylez.flicks.adapters.MovieAdapter;
import com.swtstylez.flicks.domain.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieActivity extends AppCompatActivity {

    MovieAdapter movieAdapter;
    ListView movieListView;
    List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = getResources().getString(R.string.movieListUrl);
        OkHttpClient httpClient = new OkHttpClient.Builder().build();
        Request movieRequest = new Request.Builder().url(url).build();

        movies = new LinkedList<>();
        movieListView = (ListView) findViewById(R.id.moviesListView);
        movieAdapter = new MovieAdapter(this, movies);

        movieListView.setAdapter(movieAdapter);

        httpClient.newCall(movieRequest).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if(!response.isSuccessful()) {
                    throw new IOException("Unexpected code: " + response);
                }

                final String data = response.body().string();

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        try {

                            JSONObject resultsJSONObject = new JSONObject(data);
                            JSONArray movieResults = null;

                            movieResults = resultsJSONObject.getJSONArray("results");
                            movies.addAll(Movie.fromJSONArray(movieResults));
                            movieAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                });

            }

        });

    }

}
