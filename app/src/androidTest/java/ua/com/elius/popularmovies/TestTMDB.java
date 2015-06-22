package ua.com.elius.popularmovies;

import android.test.AndroidTestCase;
import android.util.Log;

public class TestTMDB extends AndroidTestCase{

    public final String LOG_TAG = TestTMDB.class.getSimpleName();

    public void testTMDB() {
        TMDB api = new TMDB(TMDB.API_KEY);
        assertTrue(TMDB.API_KEY.equals(api.getKey()));
        Movies movies = api.getPopularMovies();
        for (String url : movies.getPosterURLs()) {
            Log.d(LOG_TAG, url);
        }
    }

}
