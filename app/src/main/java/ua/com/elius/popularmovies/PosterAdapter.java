package ua.com.elius.popularmovies;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;

import com.bumptech.glide.Glide;

import ua.com.elius.popularmovies.data.movie.MovieCursor;

public class PosterAdapter extends ResourceCursorAdapter {

    private final String LOG_TAG = PosterAdapter.class.getSimpleName();

    public PosterAdapter(Context context, int layout, Cursor c, int flags) {
        super(context, layout, c, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ImageView image;
        image = (ImageView) view.findViewById(R.id.poster_image_view);

        MovieCursor movieCursor = (MovieCursor) cursor;
        Movie movie = new Movie(movieCursor);

        Glide.with(context)
                .load(movie.getPosterURL())
                .into(image);
    }
}
