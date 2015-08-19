package ua.com.elius.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ua.com.elius.popularmovies.data.movie.MovieCursor;
import ua.com.elius.popularmovies.data.movie.MovieSelection;


public class DetailActivity extends AppCompatActivity {

    private final String LOG_TAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        int tmdbMovieId = intent.getIntExtra("id", 0);

        MovieSelection where;
        MovieCursor cursor;
        Movie movie;

        where = new MovieSelection();
        where.tmdbMovieId(tmdbMovieId);
        cursor = where.query(getContentResolver());
        cursor.moveToFirst();
        movie = new Movie(cursor);

        ImageView backdrop    = (ImageView) findViewById(R.id.backdrop);
        TextView  title       = (TextView)  findViewById(R.id.title);
        TextView  overview    = (TextView)  findViewById(R.id.overview);
        TextView  releaseDate = (TextView)  findViewById(R.id.release_date);
        TextView  rating      = (TextView)  findViewById(R.id.rating);


        Glide.with(this)
                .load(movie.getBackdropURL())
                .into(backdrop);
        title.setText(movie.getTitle());
        overview.setText(movie.getOverview());
        releaseDate.setText(movie.getReleaseDate());
        rating.setText(Double.toString(movie.getVoteAverage()));

        startFetch(tmdbMovieId, FetchService.ACTION_VIDEO);
        startFetch(tmdbMovieId, FetchService.ACTION_REVIEW);
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
