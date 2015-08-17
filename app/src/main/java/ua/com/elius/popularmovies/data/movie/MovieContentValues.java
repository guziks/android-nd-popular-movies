package ua.com.elius.popularmovies.data.movie;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ua.com.elius.popularmovies.data.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code movie} table.
 */
public class MovieContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return MovieColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable MovieSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable MovieSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public MovieContentValues putTmdbMovieId(int value) {
        mContentValues.put(MovieColumns.TMDB_MOVIE_ID, value);
        return this;
    }


    public MovieContentValues putPopularity(double value) {
        mContentValues.put(MovieColumns.POPULARITY, value);
        return this;
    }


    public MovieContentValues putVoteAverage(double value) {
        mContentValues.put(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }


    public MovieContentValues putVoteCount(int value) {
        mContentValues.put(MovieColumns.VOTE_COUNT, value);
        return this;
    }


    public MovieContentValues putTitle(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("title must not be null");
        mContentValues.put(MovieColumns.TITLE, value);
        return this;
    }


    public MovieContentValues putOverview(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("overview must not be null");
        mContentValues.put(MovieColumns.OVERVIEW, value);
        return this;
    }


    public MovieContentValues putPosterPath(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("posterPath must not be null");
        mContentValues.put(MovieColumns.POSTER_PATH, value);
        return this;
    }


    public MovieContentValues putBackdropPath(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("backdropPath must not be null");
        mContentValues.put(MovieColumns.BACKDROP_PATH, value);
        return this;
    }


    public MovieContentValues putReleaseDate(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("releaseDate must not be null");
        mContentValues.put(MovieColumns.RELEASE_DATE, value);
        return this;
    }


    public MovieContentValues putLike(boolean value) {
        mContentValues.put(MovieColumns.LIKE, value);
        return this;
    }

}
