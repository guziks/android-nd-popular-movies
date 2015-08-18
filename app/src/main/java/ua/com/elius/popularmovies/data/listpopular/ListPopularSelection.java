package ua.com.elius.popularmovies.data.listpopular;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import ua.com.elius.popularmovies.data.base.AbstractSelection;

/**
 * Selection for the {@code list_popular} table.
 */
public class ListPopularSelection extends AbstractSelection<ListPopularSelection> {
    @Override
    protected Uri baseUri() {
        return ListPopularColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ListPopularCursor} object, which is positioned before the first entry, or null.
     */
    public ListPopularCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ListPopularCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public ListPopularCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ListPopularCursor} object, which is positioned before the first entry, or null.
     */
    public ListPopularCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ListPopularCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public ListPopularCursor query(Context context) {
        return query(context, null);
    }


    public ListPopularSelection id(long... value) {
        addEquals("list_popular." + ListPopularColumns._ID, toObjectArray(value));
        return this;
    }

    public ListPopularSelection idNot(long... value) {
        addNotEquals("list_popular." + ListPopularColumns._ID, toObjectArray(value));
        return this;
    }

    public ListPopularSelection orderById(boolean desc) {
        orderBy("list_popular." + ListPopularColumns._ID, desc);
        return this;
    }

    public ListPopularSelection orderById() {
        return orderById(false);
    }

    public ListPopularSelection tmdbMovieId(int... value) {
        addEquals(ListPopularColumns.TMDB_MOVIE_ID, toObjectArray(value));
        return this;
    }

    public ListPopularSelection tmdbMovieIdNot(int... value) {
        addNotEquals(ListPopularColumns.TMDB_MOVIE_ID, toObjectArray(value));
        return this;
    }

    public ListPopularSelection tmdbMovieIdGt(int value) {
        addGreaterThan(ListPopularColumns.TMDB_MOVIE_ID, value);
        return this;
    }

    public ListPopularSelection tmdbMovieIdGtEq(int value) {
        addGreaterThanOrEquals(ListPopularColumns.TMDB_MOVIE_ID, value);
        return this;
    }

    public ListPopularSelection tmdbMovieIdLt(int value) {
        addLessThan(ListPopularColumns.TMDB_MOVIE_ID, value);
        return this;
    }

    public ListPopularSelection tmdbMovieIdLtEq(int value) {
        addLessThanOrEquals(ListPopularColumns.TMDB_MOVIE_ID, value);
        return this;
    }

    public ListPopularSelection orderByTmdbMovieId(boolean desc) {
        orderBy(ListPopularColumns.TMDB_MOVIE_ID, desc);
        return this;
    }

    public ListPopularSelection orderByTmdbMovieId() {
        orderBy(ListPopularColumns.TMDB_MOVIE_ID, false);
        return this;
    }
}
