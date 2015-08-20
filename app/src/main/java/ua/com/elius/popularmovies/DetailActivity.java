package ua.com.elius.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ua.com.elius.popularmovies.data.movie.MovieContentValues;
import ua.com.elius.popularmovies.data.movie.MovieCursor;
import ua.com.elius.popularmovies.data.movie.MovieSelection;


public class DetailActivity extends AppCompatActivity {

    private final String LOG_TAG = DetailActivity.class.getSimpleName();

    private final int IS_FAVOURITE_ICON = R.drawable.ic_favorite_black_48dp;
    private final int IS_NOT_FAVOURITE_ICON = R.drawable.ic_favorite_border_black_48dp;

    int mTmdbMovieId;
    boolean mLike;
    MovieSelection mWhere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        mTmdbMovieId = intent.getIntExtra("id", 0);

        MovieCursor cursor;
        Movie movie;

        mWhere = new MovieSelection();
        mWhere.tmdbMovieId(mTmdbMovieId);
        cursor = mWhere.query(getContentResolver());
        cursor.moveToFirst();
        movie = new Movie(cursor);

        mLike = cursor.getLike();

        ImageView   backdrop    = (ImageView) findViewById(R.id.backdrop);
        TextView    title       = (TextView)  findViewById(R.id.title);
        TextView    overview    = (TextView)  findViewById(R.id.overview);
        TextView    releaseDate = (TextView)  findViewById(R.id.release_date);
        TextView    rating      = (TextView)  findViewById(R.id.rating);
        ImageButton like        = (ImageButton) findViewById(R.id.like_button);

        Glide.with(this)
                .load(movie.getBackdropURL())
                .into(backdrop);
        title.setText(movie.getTitle());
        overview.setText(movie.getOverview());
        releaseDate.setText(movie.getReleaseDate());
        rating.setText(Double.toString(movie.getVoteAverage()));

        int likeImage;
        if (mLike) {
            likeImage = IS_FAVOURITE_ICON;
        } else {
            likeImage = IS_NOT_FAVOURITE_ICON;
        }
        like.setImageResource(likeImage);

        startFetch(mTmdbMovieId, FetchService.ACTION_VIDEO);
        startFetch(mTmdbMovieId, FetchService.ACTION_REVIEW);
    }

    private void startFetch(int tmdbMovieId, String action) {
        Intent fetchIntent = new Intent(this, FetchService.class);
        fetchIntent.setAction(action);
        fetchIntent.putExtra(
                FetchService.EXTRA_TMDB_MOVIE_ID,
                tmdbMovieId
        );
        startService(fetchIntent);
    }

    public void changeLike(View view) {
        ImageButton like = (ImageButton) view;
        MovieContentValues values;
        values = new MovieContentValues();
        if (mLike) {
            mLike = false;
            values.putLike(false);
            like.setImageResource(IS_NOT_FAVOURITE_ICON);
        } else {
            mLike = true;
            values.putLike(true);
            like.setImageResource(IS_FAVOURITE_ICON);
        }
        values.update(getContentResolver(), mWhere);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                Intent up = new Intent(this, MainActivity.class);
                up.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                up.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(up);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
