package com.swtstylez.flicks.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.swtstylez.flicks.domain.Movie;

import java.util.List;

/**
 * Created by jndukuba on 12/13/17.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {

    public MovieAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

}
