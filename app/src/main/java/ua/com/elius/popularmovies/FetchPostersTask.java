package ua.com.elius.popularmovies;

import android.content.Context;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class FetchPostersTask extends AsyncTask<PosterAdapter<String>, Void, List<String>> {

    private final String LOG_TAG = FetchPostersTask.class.getSimpleName();

    private Context mContext;
    private PosterAdapter<String> mAdapter;

    public FetchPostersTask(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected List<String> doInBackground(PosterAdapter<String>... adapter) {
        mAdapter = adapter[0];
        TMDB api = new TMDB(TMDB.API_KEY);
        String sortBy = PreferenceManager
                .getDefaultSharedPreferences(mContext).getString(SettingsActivity.KEY_PREF_SORT_BY, "");
        List<String> posterURLs = new ArrayList<>();
        switch (sortBy) {
            case "1": posterURLs = api.getPopularMovies().getPosterURLs(); break;
            case "2": posterURLs = api.getTopRatedMovies().getPosterURLs(); break;
        }
        return posterURLs;
    }

    @Override
    protected void onPostExecute(List<String> posterURLs) {
        super.onPostExecute(posterURLs);
        mAdapter.addAll(posterURLs);
        mAdapter.notifyDataSetChanged();
    }
}
