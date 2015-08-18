package ua.com.elius.popularmovies.data.listpopular;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ua.com.elius.popularmovies.data.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code list_popular} table.
 */
public class ListPopularContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return ListPopularColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable ListPopularSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable ListPopularSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public ListPopularContentValues putTmdbMovieId(int value) {
        mContentValues.put(ListPopularColumns.TMDB_MOVIE_ID, value);
        return this;
    }

}
