package ua.com.elius.popularmovies;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import java.util.Date;

import ua.com.elius.popularmovies.data.listpopular.ListPopularContentValues;
import ua.com.elius.popularmovies.data.listpopular.ListPopularSelection;
import ua.com.elius.popularmovies.data.listtoprated.ListTopRatedContentValues;
import ua.com.elius.popularmovies.data.listtoprated.ListTopRatedSelection;
import ua.com.elius.popularmovies.data.movie.MovieCursor;
import ua.com.elius.popularmovies.data.movie.MovieSelection;
import ua.com.elius.popularmovies.data.review.ReviewContentValues;
import ua.com.elius.popularmovies.data.review.ReviewCursor;
import ua.com.elius.popularmovies.data.review.ReviewSelection;
import ua.com.elius.popularmovies.data.video.VideoContentValues;
import ua.com.elius.popularmovies.data.video.VideoCursor;
import ua.com.elius.popularmovies.data.video.VideoSelection;

public class FetchService extends IntentService {

    private final String LOG_TAG = FetchService.class.getSimpleName();

    private final long DATA_RELEVANCE_DURATION = 1800000; // 30 minutes;

    public static final String EXTRA_TMDB_MOVIE_ID = "intent.extra.TMDB_MOVIE_ID";
    public static final String ACTION_POPULAR = "action.POPULAR";
    public static final String ACTION_TOP_RATED = "action.TOP_RATED";
    public static final String ACTION_REVIEW = "action.REVIEW";
    public static final String ACTION_VIDEO = "action.VIDEO";
    public static final String FILE_TIMESTAMPS = "ua.com.elius.popularmovies.fetch_timestamps";

    public FetchService() {
        super("FetchService");
    }

    public FetchService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        TMDB api = new TMDB();
        Movies movies;
        int tmdbMovieId;
        Videos videos;
        Reviews reviews;

        String action = intent.getAction();

        if (action == null) {
            return;
        } else {
            switch (action) {
                case ACTION_POPULAR:
                    if (!isFetchRequired(action)) break;
                    saveTimeStamp(action);
                    movies = api.getPopularMovies();
                    saveMovies(movies);
                    saveListOfPopular(movies);
                    break;
                case ACTION_TOP_RATED:
                    if (!isFetchRequired(action)) break;
                    saveTimeStamp(action);
                    movies = api.getTopRatedMovies();
                    saveMovies(movies);
                    saveListOfTopRated(movies);
                    break;
                case ACTION_REVIEW:
                    tmdbMovieId = intent.getIntExtra(EXTRA_TMDB_MOVIE_ID, 0);
                    reviews = api.getReviews(tmdbMovieId);
                    saveReviews(reviews, tmdbMovieId);
                    break;
                case ACTION_VIDEO:
                    tmdbMovieId = intent.getIntExtra(EXTRA_TMDB_MOVIE_ID, 0);
                    videos = api.getVideos(tmdbMovieId);
                    saveVideos(videos, tmdbMovieId);
                    break;
            }
        }
    }

    private boolean isFetchRequired(String action) {
        boolean required = true;

        long latestStamp = getSharedPreferences(FILE_TIMESTAMPS, Context.MODE_PRIVATE)
                .getLong(action, 0);
        long currentStamp = new Date().getTime();
        if (currentStamp - latestStamp < DATA_RELEVANCE_DURATION) required = false;

        return required;
    }

    private void saveTimeStamp(String action) {
        getSharedPreferences(FILE_TIMESTAMPS, Context.MODE_PRIVATE)
                .edit()
                .putLong(action, new Date().getTime())
                .commit();
    }

    private void saveMovies(Movies movies) {
        MovieSelection where;
        MovieCursor cursor;
        for (Movie movie : movies) {
            where = new MovieSelection();
            where.tmdbMovieId(movie.getId());
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
            values.putTmdbMovieId(movie.getId());
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
            values.putTmdbMovieId(movie.getId());
            values.insert(getContentResolver());
        }
    }

    private void saveVideos(Videos videos, int tmdbMovieId) {
        VideoSelection whereVideo;
        VideoCursor VideoCursor;
        MovieSelection whereMovie;
        MovieCursor movieCursor;

        VideoContentValues values;
        for (Video video : videos) {
            values = video.getValues();
            whereVideo = new VideoSelection();
            // select this video in video table
            whereVideo.tmdbVideoId(video.getTmdbVideoId());
            VideoCursor = whereVideo.query(getContentResolver());
            if (VideoCursor.getCount() == 1) {
                values.update(getContentResolver(), whereVideo);
            } else {
                whereMovie = new MovieSelection();
                whereMovie.tmdbMovieId(tmdbMovieId);
                movieCursor = whereMovie.query(getContentResolver());
                movieCursor.moveToFirst();
                // put foreign key
                values.putMovieId(movieCursor.getId());
                values.insert(getContentResolver());
            }
        }
    }

    private void saveReviews(Reviews reviews, int tmdbMovieId) {
        ReviewSelection whereReview;
        ReviewCursor ReviewCursor;
        MovieSelection whereMovie;
        MovieCursor movieCursor;

        ReviewContentValues values;
        for (Review review : reviews) {
            values = review.getValues();
            whereReview = new ReviewSelection();
            // select this review in review table
            whereReview.tmdbReviewId(review.getTmdbReviewId());
            ReviewCursor = whereReview.query(getContentResolver());
            if (ReviewCursor.getCount() == 1) {
                values.update(getContentResolver(), whereReview);
            } else {
                whereMovie = new MovieSelection();
                whereMovie.tmdbMovieId(tmdbMovieId);
                movieCursor = whereMovie.query(getContentResolver());
                movieCursor.moveToFirst();
                // put foreign key
                values.putMovieId(movieCursor.getId());
                values.insert(getContentResolver());
            }
        }
    }
}
