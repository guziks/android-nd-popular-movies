package ua.com.elius.popularmovies;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import ua.com.elius.popularmovies.data.movie.MovieContentValues;
import ua.com.elius.popularmovies.data.movie.MovieCursor;
import ua.com.elius.popularmovies.data.movie.MovieSelection;
import ua.com.elius.popularmovies.data.video.VideoColumns;
import ua.com.elius.popularmovies.data.video.VideoCursor;


public class DetailActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor>{

    private final String LOG_TAG = DetailActivity.class.getSimpleName();

    private final int IS_FAVOURITE_ICON = R.drawable.ic_favorite_black_48dp;
    private final int IS_NOT_FAVOURITE_ICON = R.drawable.ic_favorite_border_black_48dp;

    private final int VIDEO_LOADER = 0;
    private final int REVIEWS_LOADER = 1;

    int mMovieId;
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
        mMovieId = (int) cursor.getId();

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

        getSupportLoaderManager().initLoader(VIDEO_LOADER, null, this);

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

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String movieId = Integer.toString(mMovieId);
        CursorLoader loader = null;

        loader = new CursorLoader (
                this,
                VideoColumns.CONTENT_URI,
                null,
                "movie_id = ? and site = ?",
                new String[]{movieId, "YouTube"},
                null
        );

        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        final VideoCursor videoCursor = new VideoCursor(cursor);
        int videoCount = videoCursor.getCount();
        Log.d(LOG_TAG, "Video count = " + videoCount);

        LinearLayout layout = (LinearLayout) findViewById(R.id.trailers_list_container);
        layout.removeAllViews();

        videoCursor.moveToFirst();
        for (int i = 0; i < videoCount; videoCursor.moveToNext(), i++) {
            Button button = new Button(this);
            button.setText(videoCursor.getName());
            button.setTag(videoCursor.getKey());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = new Uri.Builder()
                            .scheme("vnd.youtube")
                            .authority((String)v.getTag())
                            .build();
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    boolean safeToStart = getPackageManager().queryIntentActivities(intent,
                            PackageManager.MATCH_DEFAULT_ONLY
                    ).size() > 0;
                    if (safeToStart) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(DetailActivity.this,
                                "Unable to play video, you have no YouTube app installed",
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
            });
            layout.addView(button);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
