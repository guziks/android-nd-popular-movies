package ua.com.elius.popularmovies;

import android.app.IntentService;
import android.content.Intent;

import ua.com.elius.popularmovies.data.listpopular.ListPopularContentValues;
import ua.com.elius.popularmovies.data.listpopular.ListPopularSelection;
import ua.com.elius.popularmovies.data.listtoprated.ListTopRatedContentValues;
import ua.com.elius.popularmovies.data.listtoprated.ListTopRatedSelection;
import ua.com.elius.popularmovies.data.movie.MovieCursor;
import ua.com.elius.popularmovies.data.movie.MovieSelection;

public class FetchService extends IntentService {

    private final String LOG_TAG = FetchService.class.getSimpleName();

    public static final String ACTION_POPULAR = "intent.action.POPULAR";
    public static final String ACTION_TOP_RATED = "intent.action.TOP_RATED";

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

        if (action == null) {
            return;
        } else {
            switch (action) {
                case ACTION_POPULAR:
                    movies = api.getPopularMovies();
                    saveData(movies);
                    saveListOfPopular(movies);
                    break;
                case ACTION_TOP_RATED:
                    movies = api.getTopRatedMovies();
                    saveData(movies);
                    saveListOfTopRated(movies);
                    break;
            }
        }
    }

    private void saveData(Movies movies) {
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

    private void saveListOfPopular(Movies movies) {
        ListPopularSelection where;
        ListPopularContentValues values;

        where = new ListPopularSelection();
        where.delete(getContentResolver());

        for (Movie movie : movies) {
            values = new ListPopularContentValues();
            values.putTmdbMovieId(movie.getID());
            values.insert(getContentResolver());
        }
    }

    private void saveListOfTopRated(Movies movies) {
        ListTopRatedSelection where;
        ListTopRatedContentValues values;

        where = new ListTopRatedSelection();
        where.delete(getContentResolver());

        for (Movie movie : movies) {
            values = new ListTopRatedContentValues();
            values.putTmdbMovieId(movie.getID());
            values.insert(getContentResolver());
        }
    }
}
