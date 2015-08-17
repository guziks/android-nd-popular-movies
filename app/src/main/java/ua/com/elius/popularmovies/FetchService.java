package ua.com.elius.popularmovies;

import android.app.IntentService;
import android.content.Intent;

import ua.com.elius.popularmovies.data.movie.MovieCursor;
import ua.com.elius.popularmovies.data.movie.MovieSelection;

public class FetchService extends IntentService {

    private final String LOG_TAG = FetchService.class.getSimpleName();

    public static final String ACTION_POPULAR = "intent.action.POPULAR";
    public static final String ACTION_TOP_RATED = "intent.action.HIGHEST_RATED";

    public FetchService() {
        super("FetchService");
    }

    public FetchService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        TMDB api = new TMDB(TMDB.API_KEY);
        Movies movies;

        String action = intent.getAction();
        switch (action) {
            case ACTION_TOP_RATED:
                movies = api.getTopRatedMovies();
                break;
            default:
                movies = api.getPopularMovies();
                break;
        }

        MovieSelection where;
        MovieCursor cursor;
        for (Movie movie : movies) {
            where = new MovieSelection();
            where.tmdbMovieId(movie.getID());
            cursor = where.query(getContentResolver());
            if (cursor.getCount() == 1) {
                movie.getValues().update(getContentResolver(), where);
            } else {
                movie.getValues().insert(getContentResolver());
            }
            cursor.close();
        }

    }
}
