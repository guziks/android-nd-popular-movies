package ua.com.elius.popularmovies.data.listtoprated;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ua.com.elius.popularmovies.data.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code list_top_rated} table.
 */
public class ListTopRatedContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return ListTopRatedColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable ListTopRatedSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable ListTopRatedSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public ListTopRatedContentValues putTmdbMovieId(int value) {
        mContentValues.put(ListTopRatedColumns.TMDB_MOVIE_ID, value);
        return this;
    }

}
