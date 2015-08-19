package ua.com.elius.popularmovies;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import ua.com.elius.popularmovies.data.movie.MovieColumns;
import ua.com.elius.popularmovies.data.movie.MovieCursor;
import ua.com.elius.popularmovies.data.movie.MovieSelection;


public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    PosterAdapter mPosterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(this, R.xml.pref_general, false);

        setContentView(R.layout.activity_main);

        getSupportLoaderManager().initLoader(0, null, this);

        MovieSelection where;
        MovieCursor cursor;
        where = new MovieSelection();
        where.title("Jurassic World");
        cursor = where.query(getContentResolver());

        mPosterAdapter = new PosterAdapter(this, R.layout.poster, cursor, 0);

        GridView gridview = (GridView) findViewById(R.id.poster_grid);
        gridview.setAdapter(mPosterAdapter);

//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//                Movie movie = mPosterAdapter.getMovie(position);
//                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//                intent.putExtra("id", movie.getId());
//                intent.putExtra("backdropURL", movie.getBackdropURL());
//                intent.putExtra("title", movie.getTitle());
//                intent.putExtra("overview", movie.getOverview());
//                intent.putExtra("releaseDate", movie.getReleaseDate());
//                intent.putExtra("rating", String.valueOf(movie.getVoteAverage()));
//                startActivity(intent);
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        String action;
        String sortBy = PreferenceManager
                .getDefaultSharedPreferences(this).getString(SettingsActivity.KEY_PREF_SORT_BY, "");
        switch (sortBy) {
            case "2":
                action = FetchService.ACTION_TOP_RATED;
                break;
            default:
                action = FetchService.ACTION_POPULAR;
                break;
        }
        Intent fetchIntent = new Intent(this, FetchService.class);
        fetchIntent.setAction(action);
        startService(fetchIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        CursorLoader loader = new CursorLoader(
                this,
                MovieColumns.CONTENT_URI,
                null, null, null, null
        );

        return loader;
    }

    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        mPosterAdapter.swapCursor(cursor);
    }

    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        mPosterAdapter.swapCursor(null);
    }
}
