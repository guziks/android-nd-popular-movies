package ua.com.elius.popularmovies;

import android.os.AsyncTask;

import java.util.List;

public class FetchPostersTask extends AsyncTask<PosterAdapter<String>, Void, List<String>> {

    PosterAdapter<String> mAdapter;

    @Override
    protected List<String> doInBackground(PosterAdapter<String>... adapter) {
        mAdapter = adapter[0];
        TMDB api = new TMDB(TMDB.API_KEY);
        return api.getPopularMovies().getPosterURLs();
    }

    @Override
    protected void onPostExecute(List<String> posterURLs) {
        super.onPostExecute(posterURLs);
        mAdapter.addAll(posterURLs);
        mAdapter.notifyDataSetChanged();
    }
}
