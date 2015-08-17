package ua.com.elius.popularmovies.data.review;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ua.com.elius.popularmovies.data.base.AbstractCursor;
import ua.com.elius.popularmovies.data.movie.*;

/**
 * Cursor wrapper for the {@code review} table.
 */
public class ReviewCursor extends AbstractCursor implements ReviewModel {
    public ReviewCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(ReviewColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code movie_id} value.
     */
    public long getMovieId() {
        Long res = getLongOrNull(ReviewColumns.MOVIE_ID);
        if (res == null)
            throw new NullPointerException("The value of 'movie_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code tmdb_movie_id} value.
     */
    public int getMovieTmdbMovieId() {
        Integer res = getIntegerOrNull(MovieColumns.TMDB_MOVIE_ID);
        if (res == null)
            throw new NullPointerException("The value of 'tmdb_movie_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code popularity} value.
     */
    public double getMoviePopularity() {
        Double res = getDoubleOrNull(MovieColumns.POPULARITY);
        if (res == null)
            throw new NullPointerException("The value of 'popularity' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code vote_average} value.
     */
    public double getMovieVoteAverage() {
        Double res = getDoubleOrNull(MovieColumns.VOTE_AVERAGE);
        if (res == null)
            throw new NullPointerException("The value of 'vote_average' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code vote_count} value.
     */
    public int getMovieVoteCount() {
        Integer res = getIntegerOrNull(MovieColumns.VOTE_COUNT);
        if (res == null)
            throw new NullPointerException("The value of 'vote_count' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code title} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getMovieTitle() {
        String res = getStringOrNull(MovieColumns.TITLE);
        if (res == null)
            throw new NullPointerException("The value of 'title' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code overview} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getMovieOverview() {
        String res = getStringOrNull(MovieColumns.OVERVIEW);
        if (res == null)
            throw new NullPointerException("The value of 'overview' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code poster_path} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getMoviePosterPath() {
        String res = getStringOrNull(MovieColumns.POSTER_PATH);
        if (res == null)
            throw new NullPointerException("The value of 'poster_path' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code backdrop_path} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getMovieBackdropPath() {
        String res = getStringOrNull(MovieColumns.BACKDROP_PATH);
        if (res == null)
            throw new NullPointerException("The value of 'backdrop_path' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code release_date} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getMovieReleaseDate() {
        String res = getStringOrNull(MovieColumns.RELEASE_DATE);
        if (res == null)
            throw new NullPointerException("The value of 'release_date' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code like} value.
     */
    public boolean getMovieLike() {
        Boolean res = getBooleanOrNull(MovieColumns.LIKE);
        if (res == null)
            throw new NullPointerException("The value of 'like' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code tmdb_review_id} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getTmdbReviewId() {
        String res = getStringOrNull(ReviewColumns.TMDB_REVIEW_ID);
        if (res == null)
            throw new NullPointerException("The value of 'tmdb_review_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code author} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getAuthor() {
        String res = getStringOrNull(ReviewColumns.AUTHOR);
        if (res == null)
            throw new NullPointerException("The value of 'author' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code content} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getContent() {
        String res = getStringOrNull(ReviewColumns.CONTENT);
        if (res == null)
            throw new NullPointerException("The value of 'content' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code url} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getUrl() {
        String res = getStringOrNull(ReviewColumns.URL);
        if (res == null)
            throw new NullPointerException("The value of 'url' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
