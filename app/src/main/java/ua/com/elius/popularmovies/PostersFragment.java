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
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private final String LOG_TAG = PostersFragment.class.getSimpleName();

    private PosterAdapter mPosterAdapter;
    private String mSortBy;

    private TmdbMovieIdReceiver mTmdbMovieIdReceiver;

    public PostersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSortBy = PreferenceManager
                .getDefaultSharedPreferences(getActivity()).getString(SettingsActivity.KEY_PREF_SORT_BY, "");

        mPosterAdapter = new PosterAdapter(getActivity(), R.layout.poster, null, 0);

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_posters, container, false);

        GridView gridview = (GridView) rootView.findViewById(R.id.poster_grid);
        gridview.setAdapter(mPosterAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Cursor cursor;
                MovieCursor movieCursor;

                cursor = mPosterAdapter.getCursor();
                movieCursor = new MovieCursor(cursor);

                if (mTmdbMovieIdReceiver != null) {
                    mTmdbMovieIdReceiver.setTmdbMovieId(movieCursor.getTmdbMovieId());
                }
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mTmdbMovieIdReceiver = (TmdbMovieIdReceiver) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement TmdbMovieIdReceiver");
        }
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
    public void onDetach() {
        super.onDetach();
        mTmdbMovieIdReceiver = null;
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
    }

    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        mPosterAdapter.swapCursor(null);
    }

    public interface TmdbMovieIdReceiver {
        void setTmdbMovieId(int id);
    }
}
