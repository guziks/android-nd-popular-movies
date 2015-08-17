package ua.com.elius.popularmovies.data.movie;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import ua.com.elius.popularmovies.data.base.AbstractSelection;

/**
 * Selection for the {@code movie} table.
 */
public class MovieSelection extends AbstractSelection<MovieSelection> {
    @Override
    protected Uri baseUri() {
        return MovieColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code MovieCursor} object, which is positioned before the first entry, or null.
     */
    public MovieCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new MovieCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public MovieCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code MovieCursor} object, which is positioned before the first entry, or null.
     */
    public MovieCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new MovieCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public MovieCursor query(Context context) {
        return query(context, null);
    }


    public MovieSelection id(long... value) {
        addEquals("movie." + MovieColumns._ID, toObjectArray(value));
        return this;
    }

    public MovieSelection idNot(long... value) {
        addNotEquals("movie." + MovieColumns._ID, toObjectArray(value));
        return this;
    }

    public MovieSelection orderById(boolean desc) {
        orderBy("movie." + MovieColumns._ID, desc);
        return this;
    }

    public MovieSelection orderById() {
        return orderById(false);
    }

    public MovieSelection tmdbMovieId(int... value) {
        addEquals(MovieColumns.TMDB_MOVIE_ID, toObjectArray(value));
        return this;
    }

    public MovieSelection tmdbMovieIdNot(int... value) {
        addNotEquals(MovieColumns.TMDB_MOVIE_ID, toObjectArray(value));
        return this;
    }

    public MovieSelection tmdbMovieIdGt(int value) {
        addGreaterThan(MovieColumns.TMDB_MOVIE_ID, value);
        return this;
    }

    public MovieSelection tmdbMovieIdGtEq(int value) {
        addGreaterThanOrEquals(MovieColumns.TMDB_MOVIE_ID, value);
        return this;
    }

    public MovieSelection tmdbMovieIdLt(int value) {
        addLessThan(MovieColumns.TMDB_MOVIE_ID, value);
        return this;
    }

    public MovieSelection tmdbMovieIdLtEq(int value) {
        addLessThanOrEquals(MovieColumns.TMDB_MOVIE_ID, value);
        return this;
    }

    public MovieSelection orderByTmdbMovieId(boolean desc) {
        orderBy(MovieColumns.TMDB_MOVIE_ID, desc);
        return this;
    }

    public MovieSelection orderByTmdbMovieId() {
        orderBy(MovieColumns.TMDB_MOVIE_ID, false);
        return this;
    }

    public MovieSelection popularity(double... value) {
        addEquals(MovieColumns.POPULARITY, toObjectArray(value));
        return this;
    }

    public MovieSelection popularityNot(double... value) {
        addNotEquals(MovieColumns.POPULARITY, toObjectArray(value));
        return this;
    }

    public MovieSelection popularityGt(double value) {
        addGreaterThan(MovieColumns.POPULARITY, value);
        return this;
    }

    public MovieSelection popularityGtEq(double value) {
        addGreaterThanOrEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public MovieSelection popularityLt(double value) {
        addLessThan(MovieColumns.POPULARITY, value);
        return this;
    }

    public MovieSelection popularityLtEq(double value) {
        addLessThanOrEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public MovieSelection orderByPopularity(boolean desc) {
        orderBy(MovieColumns.POPULARITY, desc);
        return this;
    }

    public MovieSelection orderByPopularity() {
        orderBy(MovieColumns.POPULARITY, false);
        return this;
    }

    public MovieSelection voteAverage(double... value) {
        addEquals(MovieColumns.VOTE_AVERAGE, toObjectArray(value));
        return this;
    }

    public MovieSelection voteAverageNot(double... value) {
        addNotEquals(MovieColumns.VOTE_AVERAGE, toObjectArray(value));
        return this;
    }

    public MovieSelection voteAverageGt(double value) {
        addGreaterThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieSelection voteAverageGtEq(double value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieSelection voteAverageLt(double value) {
        addLessThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieSelection voteAverageLtEq(double value) {
        addLessThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public MovieSelection orderByVoteAverage(boolean desc) {
        orderBy(MovieColumns.VOTE_AVERAGE, desc);
        return this;
    }

    public MovieSelection orderByVoteAverage() {
        orderBy(MovieColumns.VOTE_AVERAGE, false);
        return this;
    }

    public MovieSelection voteCount(int... value) {
        addEquals(MovieColumns.VOTE_COUNT, toObjectArray(value));
        return this;
    }

    public MovieSelection voteCountNot(int... value) {
        addNotEquals(MovieColumns.VOTE_COUNT, toObjectArray(value));
        return this;
    }

    public MovieSelection voteCountGt(int value) {
        addGreaterThan(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public MovieSelection voteCountGtEq(int value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public MovieSelection voteCountLt(int value) {
        addLessThan(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public MovieSelection voteCountLtEq(int value) {
        addLessThanOrEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public MovieSelection orderByVoteCount(boolean desc) {
        orderBy(MovieColumns.VOTE_COUNT, desc);
        return this;
    }

    public MovieSelection orderByVoteCount() {
        orderBy(MovieColumns.VOTE_COUNT, false);
        return this;
    }

    public MovieSelection title(String... value) {
        addEquals(MovieColumns.TITLE, value);
        return this;
    }

    public MovieSelection titleNot(String... value) {
        addNotEquals(MovieColumns.TITLE, value);
        return this;
    }

    public MovieSelection titleLike(String... value) {
        addLike(MovieColumns.TITLE, value);
        return this;
    }

    public MovieSelection titleContains(String... value) {
        addContains(MovieColumns.TITLE, value);
        return this;
    }

    public MovieSelection titleStartsWith(String... value) {
        addStartsWith(MovieColumns.TITLE, value);
        return this;
    }

    public MovieSelection titleEndsWith(String... value) {
        addEndsWith(MovieColumns.TITLE, value);
        return this;
    }

    public MovieSelection orderByTitle(boolean desc) {
        orderBy(MovieColumns.TITLE, desc);
        return this;
    }

    public MovieSelection orderByTitle() {
        orderBy(MovieColumns.TITLE, false);
        return this;
    }

    public MovieSelection overview(String... value) {
        addEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieSelection overviewNot(String... value) {
        addNotEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieSelection overviewLike(String... value) {
        addLike(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieSelection overviewContains(String... value) {
        addContains(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieSelection overviewStartsWith(String... value) {
        addStartsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieSelection overviewEndsWith(String... value) {
        addEndsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieSelection orderByOverview(boolean desc) {
        orderBy(MovieColumns.OVERVIEW, desc);
        return this;
    }

    public MovieSelection orderByOverview() {
        orderBy(MovieColumns.OVERVIEW, false);
        return this;
    }

    public MovieSelection posterPath(String... value) {
        addEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public MovieSelection posterPathNot(String... value) {
        addNotEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public MovieSelection posterPathLike(String... value) {
        addLike(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public MovieSelection posterPathContains(String... value) {
        addContains(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public MovieSelection posterPathStartsWith(String... value) {
        addStartsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public MovieSelection posterPathEndsWith(String... value) {
        addEndsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public MovieSelection orderByPosterPath(boolean desc) {
        orderBy(MovieColumns.POSTER_PATH, desc);
        return this;
    }

    public MovieSelection orderByPosterPath() {
        orderBy(MovieColumns.POSTER_PATH, false);
        return this;
    }

    public MovieSelection backdropPath(String... value) {
        addEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public MovieSelection backdropPathNot(String... value) {
        addNotEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public MovieSelection backdropPathLike(String... value) {
        addLike(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public MovieSelection backdropPathContains(String... value) {
        addContains(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public MovieSelection backdropPathStartsWith(String... value) {
        addStartsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public MovieSelection backdropPathEndsWith(String... value) {
        addEndsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public MovieSelection orderByBackdropPath(boolean desc) {
        orderBy(MovieColumns.BACKDROP_PATH, desc);
        return this;
    }

    public MovieSelection orderByBackdropPath() {
        orderBy(MovieColumns.BACKDROP_PATH, false);
        return this;
    }

    public MovieSelection releaseDate(String... value) {
        addEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieSelection releaseDateNot(String... value) {
        addNotEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieSelection releaseDateLike(String... value) {
        addLike(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieSelection releaseDateContains(String... value) {
        addContains(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieSelection releaseDateStartsWith(String... value) {
        addStartsWith(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieSelection releaseDateEndsWith(String... value) {
        addEndsWith(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieSelection orderByReleaseDate(boolean desc) {
        orderBy(MovieColumns.RELEASE_DATE, desc);
        return this;
    }

    public MovieSelection orderByReleaseDate() {
        orderBy(MovieColumns.RELEASE_DATE, false);
        return this;
    }

    public MovieSelection like(boolean value) {
        addEquals(MovieColumns.LIKE, toObjectArray(value));
        return this;
    }

    public MovieSelection orderByLike(boolean desc) {
        orderBy(MovieColumns.LIKE, desc);
        return this;
    }

    public MovieSelection orderByLike() {
        orderBy(MovieColumns.LIKE, false);
        return this;
    }
}
