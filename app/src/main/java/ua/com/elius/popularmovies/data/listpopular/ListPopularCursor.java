package ua.com.elius.popularmovies.data.listpopular;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ua.com.elius.popularmovies.data.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code list_popular} table.
 */
public class ListPopularCursor extends AbstractCursor implements ListPopularModel {
    public ListPopularCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(ListPopularColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code tmdb_movie_id} value.
     */
    public int getTmdbMovieId() {
        Integer res = getIntegerOrNull(ListPopularColumns.TMDB_MOVIE_ID);
        if (res == null)
            throw new NullPointerException("The value of 'tmdb_movie_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
