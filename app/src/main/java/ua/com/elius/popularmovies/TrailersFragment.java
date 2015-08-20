package ua.com.elius.popularmovies;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import ua.com.elius.popularmovies.data.video.VideoColumns;
import ua.com.elius.popularmovies.data.video.VideoCursor;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TrailersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TrailersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrailersFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String ARG_TMDB_MOVIE_ID = "key.MOVIE_ID";

    private final String LOG_TAG = TrailersFragment.class.getSimpleName();

    private int mMovieId;

    private OnFragmentInteractionListener mListener;

    public static TrailersFragment newInstance(int tmdbMovieId) {
        TrailersFragment fragment = new TrailersFragment();
        Bundle args = new Bundle();

        args.putInt(TrailersFragment.ARG_TMDB_MOVIE_ID, tmdbMovieId);
        fragment.setArguments(args);

        return fragment;
    }

    public TrailersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMovieId = getArguments().getInt(ARG_TMDB_MOVIE_ID);
        }
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_trailers, container, false);

//        LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.trailers_list_container);
//
//        TextView text = new TextView(getActivity());
//        text.setText("Hello fragment");
//        layout.addView(text);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String movieId = Integer.toString(mMovieId);
        CursorLoader loader = null;

        if (mListener != null) {
            loader = new CursorLoader (
                    getActivity(),
                    VideoColumns.CONTENT_URI,
                    null,
                    "movie_id = ?",
                    new String[]{movieId},
                    null
            );
        }

        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        VideoCursor videoCursor = new VideoCursor(cursor);
        Log.d(LOG_TAG, "Count = " + videoCursor.getCount());

        LinearLayout layout = (LinearLayout) getActivity().findViewById(R.id.trailers_list_container);
        Log.d(LOG_TAG, layout.getClass().getSimpleName());

        TextView text = new TextView(getActivity());
        text.setText("Hello fragment");
        layout.addView(text);

        Button button = new Button(getActivity());
        button.setText("Hello fragment");
        layout.addView(button);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public int getTmdbMovieId();
    }

}
