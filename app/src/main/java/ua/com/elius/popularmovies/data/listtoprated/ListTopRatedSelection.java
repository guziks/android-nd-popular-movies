package ua.com.elius.popularmovies.data.listtoprated;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import ua.com.elius.popularmovies.data.base.AbstractSelection;

/**
 * Selection for the {@code list_top_rated} table.
 */
public class ListTopRatedSelection extends AbstractSelection<ListTopRatedSelection> {
    @Override
    protected Uri baseUri() {
        return ListTopRatedColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ListTopRatedCursor} object, which is positioned before the first entry, or null.
     */
    public ListTopRatedCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ListTopRatedCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public ListTopRatedCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ListTopRatedCursor} object, which is positioned before the first entry, or null.
     */
    public ListTopRatedCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ListTopRatedCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public ListTopRatedCursor query(Context context) {
        return query(context, null);
    }


    public ListTopRatedSelection id(long... value) {
        addEquals("list_top_rated." + ListTopRatedColumns._ID, toObjectArray(value));
        return this;
    }

    public ListTopRatedSelection idNot(long... value) {
        addNotEquals("list_top_rated." + ListTopRatedColumns._ID, toObjectArray(value));
        return this;
    }

    public ListTopRatedSelection orderById(boolean desc) {
        orderBy("list_top_rated." + ListTopRatedColumns._ID, desc);
        return this;
    }

    public ListTopRatedSelection orderById() {
        return orderById(false);
    }

    public ListTopRatedSelection tmdbMovieId(int... value) {
        addEquals(ListTopRatedColumns.TMDB_MOVIE_ID, toObjectArray(value));
        return this;
    }

    public ListTopRatedSelection tmdbMovieIdNot(int... value) {
        addNotEquals(ListTopRatedColumns.TMDB_MOVIE_ID, toObjectArray(value));
        return this;
    }

    public ListTopRatedSelection tmdbMovieIdGt(int value) {
        addGreaterThan(ListTopRatedColumns.TMDB_MOVIE_ID, value);
        return this;
    }

    public ListTopRatedSelection tmdbMovieIdGtEq(int value) {
        addGreaterThanOrEquals(ListTopRatedColumns.TMDB_MOVIE_ID, value);
        return this;
    }

    public ListTopRatedSelection tmdbMovieIdLt(int value) {
        addLessThan(ListTopRatedColumns.TMDB_MOVIE_ID, value);
        return this;
    }

    public ListTopRatedSelection tmdbMovieIdLtEq(int value) {
        addLessThanOrEquals(ListTopRatedColumns.TMDB_MOVIE_ID, value);
        return this;
    }

    public ListTopRatedSelection orderByTmdbMovieId(boolean desc) {
        orderBy(ListTopRatedColumns.TMDB_MOVIE_ID, desc);
        return this;
    }

    public ListTopRatedSelection orderByTmdbMovieId() {
        orderBy(ListTopRatedColumns.TMDB_MOVIE_ID, false);
        return this;
    }
}
