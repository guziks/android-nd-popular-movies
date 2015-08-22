package ua.com.elius.popularmovies;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import ua.com.elius.popularmovies.data.movie.MovieColumns;
import ua.com.elius.popularmovies.data.movie.MovieCursor;


public class PostersFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<Cursor>,
                   GridView.OnItemClickListener {

    private final String LOG_TAG = PostersFragment.class.getSimpleName();

    private static final int MOVIE_LOADER = 0;

    public static final String KEY_SELECTED_TMDB_MOVIE_ID = "selectedTmdbMovieId";

    private PosterAdapter mPosterAdapter;
    private String mSortBy;
    private boolean mTwoPane;
    private int mSelectedTmdbMovieId;

    public PostersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mSortBy = PreferenceManager
                .getDefaultSharedPreferences(getActivity()).getString(SettingsActivity.KEY_PREF_SORT_BY, "");

        mPosterAdapter = new PosterAdapter(getActivity(), R.layout.poster, null, 0);

        getLoaderManager().initLoader(MOVIE_LOADER, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_posters, container, false);

        GridView gridview = (GridView) rootView.findViewById(R.id.poster_grid);
        gridview.setAdapter(mPosterAdapter);

        gridview.setOnItemClickListener(this);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View detailContainer = getActivity().findViewById(R.id.fragment_detail_container);
        mTwoPane = detailContainer != null;

        if (savedInstanceState != null) {
            mSelectedTmdbMovieId = savedInstanceState.getInt(KEY_SELECTED_TMDB_MOVIE_ID, 0);
        }

        if (mTwoPane) {
            showDetails(mSelectedTmdbMovieId);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SELECTED_TMDB_MOVIE_ID, mSelectedTmdbMovieId);
    }

    void showDetails(int id) {
        if (id == 0) return;

        mSelectedTmdbMovieId = id;

        if (mTwoPane) {
            // Check what fragment is currently shown, replace if needed.
            DetailFragment details = (DetailFragment)
                    getFragmentManager().findFragmentById(R.id.fragment_detail_container);
            if (details == null || details.getShownTmdbMovieId() != id) {
                // Make new fragment to show this selection.
                details = DetailFragment.newInstance(id);

                // Execute a transaction, replacing any existing fragment
                // with this one inside the frame.
                getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_detail_container, details)
                    .commitAllowingStateLoss();
            }

        } else {
            // Otherwise we need to launch a new activity to display
            // the dialog fragment with selected text.
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra(DetailFragment.KEY_TMDB_MOVIE_ID, id);
            startActivity(intent);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Cursor cursor;
        MovieCursor movieCursor;

        cursor = mPosterAdapter.getCursor();
        movieCursor = new MovieCursor(cursor);

        showDetails(movieCursor.getTmdbMovieId());
    }

    @Override
    public void onStart() {
        super.onStart();

        String currentSortBy = PreferenceManager
                .getDefaultSharedPreferences(getActivity()).getString(SettingsActivity.KEY_PREF_SORT_BY, "");

        if (!currentSortBy.equals(mSortBy)) {
            mSortBy = currentSortBy;
            getLoaderManager().restartLoader(0, null, this);
        }

        String action;

        switch (mSortBy) {
            case "2":
                action = FetchService.ACTION_TOP_RATED;
                break;
            default:
                action = FetchService.ACTION_POPULAR;
                break;
        }
        Intent fetchIntent = new Intent(getActivity(), FetchService.class);
        fetchIntent.setAction(action);
        getActivity().startService(fetchIntent);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String sortColumn;
        String selection = null;
        String[] selectionArgs = null;
        String limit = "LIMIT 20";
        switch (mSortBy) {
            case "2":
                sortColumn = MovieColumns.VOTE_AVERAGE;
                break;
            case "3":
                sortColumn = MovieColumns.TITLE;
                selection = "like = ?";
                selectionArgs = new String[]{"1"};
                limit = "";
                break;
            default:
                sortColumn = MovieColumns.POPULARITY;
                break;
        }
        CursorLoader loader = new CursorLoader(
                getActivity(),
                MovieColumns.CONTENT_URI,
                null, selection, selectionArgs, sortColumn + " DESC " + limit
        );

        return loader;
    }

    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        mPosterAdapter.swapCursor(cursor);
        if (mSelectedTmdbMovieId == 0 && mTwoPane) {
            MovieCursor movieCursor = new MovieCursor(cursor);
            movieCursor.moveToFirst();
            mSelectedTmdbMovieId = movieCursor.getTmdbMovieId();
            showDetails(mSelectedTmdbMovieId);
        }
    }

    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        mPosterAdapter.swapCursor(null);
    }
}
