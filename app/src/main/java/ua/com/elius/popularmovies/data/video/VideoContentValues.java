package ua.com.elius.popularmovies.data.video;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ua.com.elius.popularmovies.data.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code video} table.
 */
public class VideoContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return VideoColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable VideoSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable VideoSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public VideoContentValues putMovieId(long value) {
        mContentValues.put(VideoColumns.MOVIE_ID, value);
        return this;
    }


    public VideoContentValues putTmdbVideoId(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("tmdbVideoId must not be null");
        mContentValues.put(VideoColumns.TMDB_VIDEO_ID, value);
        return this;
    }


    public VideoContentValues putKey(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("key must not be null");
        mContentValues.put(VideoColumns.KEY, value);
        return this;
    }


    public VideoContentValues putName(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("name must not be null");
        mContentValues.put(VideoColumns.NAME, value);
        return this;
    }


    public VideoContentValues putSite(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("site must not be null");
        mContentValues.put(VideoColumns.SITE, value);
        return this;
    }


    public VideoContentValues putSize(int value) {
        mContentValues.put(VideoColumns.SIZE, value);
        return this;
    }


    public VideoContentValues putType(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("type must not be null");
        mContentValues.put(VideoColumns.TYPE, value);
        return this;
    }

}
