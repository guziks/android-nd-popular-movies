package ua.com.elius.popularmovies;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import ua.com.elius.popularmovies.data.movie.MovieContentValues;
import ua.com.elius.popularmovies.data.movie.MovieCursor;
import ua.com.elius.popularmovies.data.movie.MovieSelection;
import ua.com.elius.popularmovies.data.review.ReviewColumns;
import ua.com.elius.popularmovies.data.review.ReviewCursor;
import ua.com.elius.popularmovies.data.video.VideoColumns;
import ua.com.elius.popularmovies.data.video.VideoCursor;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<Cursor>,
                   ImageButton.OnClickListener {

    private final String LOG_TAG = DetailFragment.class.getSimpleName();

    public static final String KEY_TMDB_MOVIE_ID = "tmdbMovieId";

    private final int IS_FAVOURITE_ICON = R.drawable.ic_favorite_black_48dp;
    private final int IS_NOT_FAVOURITE_ICON = R.drawable.ic_favorite_border_black_48dp;

    private final int VIDEO_LOADER = 0;
    private final int REVIEW_LOADER = 1;

    private int mMovieId;
    private boolean mLike;
    private MovieSelection mWhere;

    public static DetailFragment newInstance(int id) {
        DetailFragment f = new DetailFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt(KEY_TMDB_MOVIE_ID, id);
        f.setArguments(args);

        return f;
    }

    public int getShownTmdbMovieId() {
        return getArguments().getInt(KEY_TMDB_MOVIE_ID, 0);
    }

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) return null;

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        ImageButton likeButton = (ImageButton) rootView.findViewById(R.id.like_button);
        likeButton.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        int tmdbMovieId = getArguments().getInt(KEY_TMDB_MOVIE_ID, 0);

        Activity activity = getActivity();

        MovieCursor cursor;
        Movie movie;

        mWhere = new MovieSelection();
        mWhere.tmdbMovieId(tmdbMovieId);
        cursor = mWhere.query(activity.getContentResolver());
        cursor.moveToFirst();
        movie = new Movie(cursor);

        mLike = cursor.getLike();
        mMovieId = (int) cursor.getId();

        ImageView   backdrop    = (ImageView)   activity.findViewById(R.id.backdrop);
        TextView    title       = (TextView)    activity.findViewById(R.id.title);
        TextView    overview    = (TextView)    activity.findViewById(R.id.overview);
        TextView    releaseDate = (TextView)    activity.findViewById(R.id.release_date);
        TextView    rating      = (TextView)    activity.findViewById(R.id.rating);
        ImageButton like        = (ImageButton) activity.findViewById(R.id.like_button);

        if (backdrop == null) return; // workaround on activity recreation on up/back

        Glide.with(this)
                .load(movie.getBackdropURL())
                .into(backdrop);
        title.setText(movie.getTitle());
        overview.setText(movie.getOverview());
        releaseDate.setText(movie.getReleaseDate());
        rating.setText(Double.toString(movie.getVoteAverage()));

        int likeImage;
        if (mLike) {
            likeImage = IS_FAVOURITE_ICON;
        } else {
            likeImage = IS_NOT_FAVOURITE_ICON;
        }
        like.setImageResource(likeImage);

        getLoaderManager().initLoader(VIDEO_LOADER, null, this);
        getLoaderManager().initLoader(REVIEW_LOADER, null, this);

        startFetch(tmdbMovieId, FetchService.ACTION_VIDEO);
        startFetch(tmdbMovieId, FetchService.ACTION_REVIEW);
    }

    private void startFetch(int tmdbMovieId, String action) {
        Intent fetchIntent = new Intent(getActivity(), FetchService.class);
        fetchIntent.setAction(action);
        fetchIntent.putExtra(
                FetchService.EXTRA_TMDB_MOVIE_ID,
                tmdbMovieId
        );
        getActivity().startService(fetchIntent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.like_button:
                swapLike(v);
                break;
        }
    }

    public void swapLike(View view) {
        ImageButton like = (ImageButton) view;
        MovieContentValues values;
        values = new MovieContentValues();
        if (mLike) {
            mLike = false;
            values.putLike(false);
            like.setImageResource(IS_NOT_FAVOURITE_ICON);
        } else {
            mLike = true;
            values.putLike(true);
            like.setImageResource(IS_FAVOURITE_ICON);
        }
        values.update(getActivity().getContentResolver(), mWhere);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String movieId = Integer.toString(mMovieId);
        CursorLoader loader = null;

        Log.d(LOG_TAG, "onCreateLoader id = " + id);

        if (id == VIDEO_LOADER) {
            loader = new CursorLoader (
                    getActivity(),
                    VideoColumns.CONTENT_URI,
                    null,
                    "movie_id = ? and site = ?",
                    new String[]{movieId, "YouTube"},
                    null
            );
        }

        if (id == REVIEW_LOADER) {
            loader = new CursorLoader (
                    getActivity(),
                    ReviewColumns.CONTENT_URI,
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
        int loaderId = loader.getId();
        Log.d(LOG_TAG, "onLoadFinished id = " + loaderId);

        if (loaderId == VIDEO_LOADER) {
            VideoCursor videoCursor = new VideoCursor(cursor);
            int videoCount = videoCursor.getCount();
            Log.d(LOG_TAG, "Video count = " + videoCount);

            if (videoCount == 0) return;

            LinearLayout layout = (LinearLayout) getActivity().findViewById(R.id.trailers_list_container);
            layout.removeAllViews();

            videoCursor.moveToFirst();
            for (int i = 0; i < videoCount; videoCursor.moveToNext(), i++) {
                Button button = new Button(getActivity());
                button.setText(videoCursor.getName());
                button.setTag(videoCursor.getKey());
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = new Uri.Builder()
                                .scheme("vnd.youtube")
                                .authority((String)v.getTag())
                                .build();
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        boolean safeToStart = getActivity().getPackageManager().queryIntentActivities(intent,
                                PackageManager.MATCH_DEFAULT_ONLY
                        ).size() > 0;
                        if (safeToStart) {
                            startActivity(intent);
                        } else {
                            Toast.makeText(getActivity(),
                                    "Unable to play video, you have no YouTube app installed",
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    }
                });
                layout.addView(button);
            }
        }

        if (loaderId == REVIEW_LOADER) {
            ReviewCursor reviewCursor = new ReviewCursor(cursor);
            int reviewCount = reviewCursor.getCount();
            Log.d(LOG_TAG, "Review count = " + reviewCount);

            if (reviewCount == 0) return;

            LinearLayout layout = (LinearLayout) getActivity().findViewById(R.id.reviews_list_container);
            layout.removeAllViews();

            reviewCursor.moveToFirst();
            for (int i = 0; i < reviewCount; reviewCursor.moveToNext(), i++) {
                TextView text = new TextView(getActivity());
                text.setText(reviewCursor.getContent());

                layout.addView(text);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
