package ua.com.elius.popularmovies;

import android.content.Context;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

public class FetchPostersTask extends AsyncTask<PosterAdapter<String>, Void, Movies> {

    private final String LOG_TAG = FetchPostersTask.class.getSimpleName();

    private Context mContext;
    private PosterAdapter<String> mAdapter;

    public FetchPostersTask(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected Movies doInBackground(PosterAdapter<String>... adapter) {
        mAdapter = adapter[0];
        TMDB api = new TMDB(TMDB.API_KEY);
        String sortBy = PreferenceManager
                .getDefaultSharedPreferences(mContext).getString(SettingsActivity.KEY_PREF_SORT_BY, "");
        Movies movies = null;
        switch (sortBy) {
            case "1": movies = api.getPopularMovies(); break;
            case "2": movies = api.getTopRatedMovies(); break;
        }
        return movies;
    }

    @Override
    protected void onPostExecute(Movies movies) {
        super.onPostExecute(movies);
        mAdapter.setMovies(movies);
        mAdapter.clear();
        mAdapter.addAll(movies.getPosterURLs());
        mAdapter.notifyDataSetChanged();
    }
}
