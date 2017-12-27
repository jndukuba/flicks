package com.swtstylez.flicks.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.swtstylez.flicks.R;
import com.swtstylez.flicks.domain.Movie;

import java.util.List;

/**
 * Created by jndukuba on 12/13/17.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {

    private static class ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView overviewTextView;
    }

    public MovieAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Movie movie = getItem(position);
        int orientation = getContext().getResources().getConfiguration().orientation;
        String imagePath = (orientation == Configuration.ORIENTATION_PORTRAIT) ? movie.getPosterPath() : movie.getBackdropPath();
        ViewHolder viewHolder;

        if (convertView == null) {

            convertView = getLayoutForMovie(movie.getVoteAverage());
            viewHolder = new ViewHolder();

            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            viewHolder.titleTextView = (TextView) convertView.findViewById(R.id.titleTextView);
            viewHolder.overviewTextView = (TextView) convertView.findViewById(R.id.overviewTextView);

            convertView.setTag(viewHolder);

        }

        viewHolder = (ViewHolder) convertView.getTag();

        if(movie.getVoteAverage() < 5) {
            viewHolder.titleTextView.setText(movie.getOriginalTitle());
            viewHolder.overviewTextView.setText(movie.getOverview());
        } else {
            imagePath = movie.getOriginalBackdropPath();
        }

        Picasso.with(getContext()).load(imagePath).into(viewHolder.imageView);

        return convertView;

    }

    private View getLayoutForMovie(double popularity) {

        if( popularity >= 5 ) {
            return LayoutInflater.from(getContext()).inflate(R.layout.popular_movie, null);
        }

        return LayoutInflater.from(getContext()).inflate(R.layout.item_movie, null);

    }

}
