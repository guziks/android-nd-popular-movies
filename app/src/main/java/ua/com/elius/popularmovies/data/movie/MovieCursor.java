package ua.com.elius.popularmovies.data.movie;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ua.com.elius.popularmovies.data.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code movie} table.
 */
public class MovieCursor extends AbstractCursor implements MovieModel {
    public MovieCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(MovieColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code tmdb_movie_id} value.
     */
    public int getTmdbMovieId() {
        Integer res = getIntegerOrNull(MovieColumns.TMDB_MOVIE_ID);
        if (res == null)
            throw new NullPointerException("The value of 'tmdb_movie_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code popularity} value.
     */
    public double getPopularity() {
        Double res = getDoubleOrNull(MovieColumns.POPULARITY);
        if (res == null)
            throw new NullPointerException("The value of 'popularity' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code vote_average} value.
     */
    public double getVoteAverage() {
        Double res = getDoubleOrNull(MovieColumns.VOTE_AVERAGE);
        if (res == null)
            throw new NullPointerException("The value of 'vote_average' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code vote_count} value.
     */
    public int getVoteCount() {
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
    public String getTitle() {
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
    public String getOverview() {
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
    public String getPosterPath() {
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
    public String getBackdropPath() {
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
    public String getReleaseDate() {
        String res = getStringOrNull(MovieColumns.RELEASE_DATE);
        if (res == null)
            throw new NullPointerException("The value of 'release_date' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code like} value.
     */
    public boolean getLike() {
        Boolean res = getBooleanOrNull(MovieColumns.LIKE);
        if (res == null)
            throw new NullPointerException("The value of 'like' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
