package ua.com.elius.popularmovies.data.video;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import ua.com.elius.popularmovies.data.base.AbstractSelection;
import ua.com.elius.popularmovies.data.movie.*;

/**
 * Selection for the {@code video} table.
 */
public class VideoSelection extends AbstractSelection<VideoSelection> {
    @Override
    protected Uri baseUri() {
        return VideoColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code VideoCursor} object, which is positioned before the first entry, or null.
     */
    public VideoCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new VideoCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public VideoCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code VideoCursor} object, which is positioned before the first entry, or null.
     */
    public VideoCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new VideoCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public VideoCursor query(Context context) {
        return query(context, null);
    }


    public VideoSelection id(long... value) {
        addEquals("video." + VideoColumns._ID, toObjectArray(value));
        return this;
    }

    public VideoSelection idNot(long... value) {
        addNotEquals("video." + VideoColumns._ID, toObjectArray(value));
        return this;
    }

    public VideoSelection orderById(boolean desc) {
        orderBy("video." + VideoColumns._ID, desc);
        return this;
    }

    public VideoSelection orderById() {
        return orderById(false);
    }

    public VideoSelection movieId(long... value) {
        addEquals(VideoColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public VideoSelection movieIdNot(long... value) {
        addNotEquals(VideoColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public VideoSelection movieIdGt(long value) {
        addGreaterThan(VideoColumns.MOVIE_ID, value);
        return this;
    }

    public VideoSelection movieIdGtEq(long value) {
        addGreaterThanOrEquals(VideoColumns.MOVIE_ID, value);
        return this;
    }

    public VideoSelection movieIdLt(long value) {
        addLessThan(VideoColumns.MOVIE_ID, value);
        return this;
    }

    public VideoSelection movieIdLtEq(long value) {
        addLessThanOrEquals(VideoColumns.MOVIE_ID, value);
        return this;
    }

    public VideoSelection orderByMovieId(boolean desc) {
        orderBy(VideoColumns.MOVIE_ID, desc);
        return this;
    }

    public VideoSelection orderByMovieId() {
        orderBy(VideoColumns.MOVIE_ID, false);
        return this;
    }

    public VideoSelection movieTmdbMovieId(int... value) {
        addEquals(MovieColumns.TMDB_MOVIE_ID, toObjectArray(value));
        return this;
    }

    public VideoSelection movieTmdbMovieIdNot(int... value) {
        addNotEquals(MovieColumns.TMDB_MOVIE_ID, toObjectArray(value));
        return this;
    }

    public VideoSelection movieTmdbMovieIdGt(int value) {
        addGreaterThan(MovieColumns.TMDB_MOVIE_ID, value);
        return this;
    }

    public VideoSelection movieTmdbMovieIdGtEq(int value) {
        addGreaterThanOrEquals(MovieColumns.TMDB_MOVIE_ID, value);
        return this;
    }

    public VideoSelection movieTmdbMovieIdLt(int value) {
        addLessThan(MovieColumns.TMDB_MOVIE_ID, value);
        return this;
    }

    public VideoSelection movieTmdbMovieIdLtEq(int value) {
        addLessThanOrEquals(MovieColumns.TMDB_MOVIE_ID, value);
        return this;
    }

    public VideoSelection orderByMovieTmdbMovieId(boolean desc) {
        orderBy(MovieColumns.TMDB_MOVIE_ID, desc);
        return this;
    }

    public VideoSelection orderByMovieTmdbMovieId() {
        orderBy(MovieColumns.TMDB_MOVIE_ID, false);
        return this;
    }

    public VideoSelection moviePopularity(double... value) {
        addEquals(MovieColumns.POPULARITY, toObjectArray(value));
        return this;
    }

    public VideoSelection moviePopularityNot(double... value) {
        addNotEquals(MovieColumns.POPULARITY, toObjectArray(value));
        return this;
    }

    public VideoSelection moviePopularityGt(double value) {
        addGreaterThan(MovieColumns.POPULARITY, value);
        return this;
    }

    public VideoSelection moviePopularityGtEq(double value) {
        addGreaterThanOrEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public VideoSelection moviePopularityLt(double value) {
        addLessThan(MovieColumns.POPULARITY, value);
        return this;
    }

    public VideoSelection moviePopularityLtEq(double value) {
        addLessThanOrEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public VideoSelection orderByMoviePopularity(boolean desc) {
        orderBy(MovieColumns.POPULARITY, desc);
        return this;
    }

    public VideoSelection orderByMoviePopularity() {
        orderBy(MovieColumns.POPULARITY, false);
        return this;
    }

    public VideoSelection movieVoteAverage(double... value) {
        addEquals(MovieColumns.VOTE_AVERAGE, toObjectArray(value));
        return this;
    }

    public VideoSelection movieVoteAverageNot(double... value) {
        addNotEquals(MovieColumns.VOTE_AVERAGE, toObjectArray(value));
        return this;
    }

    public VideoSelection movieVoteAverageGt(double value) {
        addGreaterThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public VideoSelection movieVoteAverageGtEq(double value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public VideoSelection movieVoteAverageLt(double value) {
        addLessThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public VideoSelection movieVoteAverageLtEq(double value) {
        addLessThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public VideoSelection orderByMovieVoteAverage(boolean desc) {
        orderBy(MovieColumns.VOTE_AVERAGE, desc);
        return this;
    }

    public VideoSelection orderByMovieVoteAverage() {
        orderBy(MovieColumns.VOTE_AVERAGE, false);
        return this;
    }

    public VideoSelection movieVoteCount(int... value) {
        addEquals(MovieColumns.VOTE_COUNT, toObjectArray(value));
        return this;
    }

    public VideoSelection movieVoteCountNot(int... value) {
        addNotEquals(MovieColumns.VOTE_COUNT, toObjectArray(value));
        return this;
    }

    public VideoSelection movieVoteCountGt(int value) {
        addGreaterThan(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public VideoSelection movieVoteCountGtEq(int value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public VideoSelection movieVoteCountLt(int value) {
        addLessThan(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public VideoSelection movieVoteCountLtEq(int value) {
        addLessThanOrEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public VideoSelection orderByMovieVoteCount(boolean desc) {
        orderBy(MovieColumns.VOTE_COUNT, desc);
        return this;
    }

    public VideoSelection orderByMovieVoteCount() {
        orderBy(MovieColumns.VOTE_COUNT, false);
        return this;
    }

    public VideoSelection movieTitle(String... value) {
        addEquals(MovieColumns.TITLE, value);
        return this;
    }

    public VideoSelection movieTitleNot(String... value) {
        addNotEquals(MovieColumns.TITLE, value);
        return this;
    }

    public VideoSelection movieTitleLike(String... value) {
        addLike(MovieColumns.TITLE, value);
        return this;
    }

    public VideoSelection movieTitleContains(String... value) {
        addContains(MovieColumns.TITLE, value);
        return this;
    }

    public VideoSelection movieTitleStartsWith(String... value) {
        addStartsWith(MovieColumns.TITLE, value);
        return this;
    }

    public VideoSelection movieTitleEndsWith(String... value) {
        addEndsWith(MovieColumns.TITLE, value);
        return this;
    }

    public VideoSelection orderByMovieTitle(boolean desc) {
        orderBy(MovieColumns.TITLE, desc);
        return this;
    }

    public VideoSelection orderByMovieTitle() {
        orderBy(MovieColumns.TITLE, false);
        return this;
    }

    public VideoSelection movieOverview(String... value) {
        addEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public VideoSelection movieOverviewNot(String... value) {
        addNotEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public VideoSelection movieOverviewLike(String... value) {
        addLike(MovieColumns.OVERVIEW, value);
        return this;
    }

    public VideoSelection movieOverviewContains(String... value) {
        addContains(MovieColumns.OVERVIEW, value);
        return this;
    }

    public VideoSelection movieOverviewStartsWith(String... value) {
        addStartsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public VideoSelection movieOverviewEndsWith(String... value) {
        addEndsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public VideoSelection orderByMovieOverview(boolean desc) {
        orderBy(MovieColumns.OVERVIEW, desc);
        return this;
    }

    public VideoSelection orderByMovieOverview() {
        orderBy(MovieColumns.OVERVIEW, false);
        return this;
    }

    public VideoSelection moviePosterPath(String... value) {
        addEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public VideoSelection moviePosterPathNot(String... value) {
        addNotEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public VideoSelection moviePosterPathLike(String... value) {
        addLike(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public VideoSelection moviePosterPathContains(String... value) {
        addContains(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public VideoSelection moviePosterPathStartsWith(String... value) {
        addStartsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public VideoSelection moviePosterPathEndsWith(String... value) {
        addEndsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public VideoSelection orderByMoviePosterPath(boolean desc) {
        orderBy(MovieColumns.POSTER_PATH, desc);
        return this;
    }

    public VideoSelection orderByMoviePosterPath() {
        orderBy(MovieColumns.POSTER_PATH, false);
        return this;
    }

    public VideoSelection movieBackdropPath(String... value) {
        addEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public VideoSelection movieBackdropPathNot(String... value) {
        addNotEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public VideoSelection movieBackdropPathLike(String... value) {
        addLike(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public VideoSelection movieBackdropPathContains(String... value) {
        addContains(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public VideoSelection movieBackdropPathStartsWith(String... value) {
        addStartsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public VideoSelection movieBackdropPathEndsWith(String... value) {
        addEndsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public VideoSelection orderByMovieBackdropPath(boolean desc) {
        orderBy(MovieColumns.BACKDROP_PATH, desc);
        return this;
    }

    public VideoSelection orderByMovieBackdropPath() {
        orderBy(MovieColumns.BACKDROP_PATH, false);
        return this;
    }

    public VideoSelection movieReleaseDate(String... value) {
        addEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public VideoSelection movieReleaseDateNot(String... value) {
        addNotEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public VideoSelection movieReleaseDateLike(String... value) {
        addLike(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public VideoSelection movieReleaseDateContains(String... value) {
        addContains(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public VideoSelection movieReleaseDateStartsWith(String... value) {
        addStartsWith(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public VideoSelection movieReleaseDateEndsWith(String... value) {
        addEndsWith(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public VideoSelection orderByMovieReleaseDate(boolean desc) {
        orderBy(MovieColumns.RELEASE_DATE, desc);
        return this;
    }

    public VideoSelection orderByMovieReleaseDate() {
        orderBy(MovieColumns.RELEASE_DATE, false);
        return this;
    }

    public VideoSelection movieLike(boolean value) {
        addEquals(MovieColumns.LIKE, toObjectArray(value));
        return this;
    }

    public VideoSelection orderByMovieLike(boolean desc) {
        orderBy(MovieColumns.LIKE, desc);
        return this;
    }

    public VideoSelection orderByMovieLike() {
        orderBy(MovieColumns.LIKE, false);
        return this;
    }

    public VideoSelection tmdbVideoId(String... value) {
        addEquals(VideoColumns.TMDB_VIDEO_ID, value);
        return this;
    }

    public VideoSelection tmdbVideoIdNot(String... value) {
        addNotEquals(VideoColumns.TMDB_VIDEO_ID, value);
        return this;
    }

    public VideoSelection tmdbVideoIdLike(String... value) {
        addLike(VideoColumns.TMDB_VIDEO_ID, value);
        return this;
    }

    public VideoSelection tmdbVideoIdContains(String... value) {
        addContains(VideoColumns.TMDB_VIDEO_ID, value);
        return this;
    }

    public VideoSelection tmdbVideoIdStartsWith(String... value) {
        addStartsWith(VideoColumns.TMDB_VIDEO_ID, value);
        return this;
    }

    public VideoSelection tmdbVideoIdEndsWith(String... value) {
        addEndsWith(VideoColumns.TMDB_VIDEO_ID, value);
        return this;
    }

    public VideoSelection orderByTmdbVideoId(boolean desc) {
        orderBy(VideoColumns.TMDB_VIDEO_ID, desc);
        return this;
    }

    public VideoSelection orderByTmdbVideoId() {
        orderBy(VideoColumns.TMDB_VIDEO_ID, false);
        return this;
    }

    public VideoSelection key(String... value) {
        addEquals(VideoColumns.KEY, value);
        return this;
    }

    public VideoSelection keyNot(String... value) {
        addNotEquals(VideoColumns.KEY, value);
        return this;
    }

    public VideoSelection keyLike(String... value) {
        addLike(VideoColumns.KEY, value);
        return this;
    }

    public VideoSelection keyContains(String... value) {
        addContains(VideoColumns.KEY, value);
        return this;
    }

    public VideoSelection keyStartsWith(String... value) {
        addStartsWith(VideoColumns.KEY, value);
        return this;
    }

    public VideoSelection keyEndsWith(String... value) {
        addEndsWith(VideoColumns.KEY, value);
        return this;
    }

    public VideoSelection orderByKey(boolean desc) {
        orderBy(VideoColumns.KEY, desc);
        return this;
    }

    public VideoSelection orderByKey() {
        orderBy(VideoColumns.KEY, false);
        return this;
    }

    public VideoSelection name(String... value) {
        addEquals(VideoColumns.NAME, value);
        return this;
    }

    public VideoSelection nameNot(String... value) {
        addNotEquals(VideoColumns.NAME, value);
        return this;
    }

    public VideoSelection nameLike(String... value) {
        addLike(VideoColumns.NAME, value);
        return this;
    }

    public VideoSelection nameContains(String... value) {
        addContains(VideoColumns.NAME, value);
        return this;
    }

    public VideoSelection nameStartsWith(String... value) {
        addStartsWith(VideoColumns.NAME, value);
        return this;
    }

    public VideoSelection nameEndsWith(String... value) {
        addEndsWith(VideoColumns.NAME, value);
        return this;
    }

    public VideoSelection orderByName(boolean desc) {
        orderBy(VideoColumns.NAME, desc);
        return this;
    }

    public VideoSelection orderByName() {
        orderBy(VideoColumns.NAME, false);
        return this;
    }

    public VideoSelection site(String... value) {
        addEquals(VideoColumns.SITE, value);
        return this;
    }

    public VideoSelection siteNot(String... value) {
        addNotEquals(VideoColumns.SITE, value);
        return this;
    }

    public VideoSelection siteLike(String... value) {
        addLike(VideoColumns.SITE, value);
        return this;
    }

    public VideoSelection siteContains(String... value) {
        addContains(VideoColumns.SITE, value);
        return this;
    }

    public VideoSelection siteStartsWith(String... value) {
        addStartsWith(VideoColumns.SITE, value);
        return this;
    }

    public VideoSelection siteEndsWith(String... value) {
        addEndsWith(VideoColumns.SITE, value);
        return this;
    }

    public VideoSelection orderBySite(boolean desc) {
        orderBy(VideoColumns.SITE, desc);
        return this;
    }

    public VideoSelection orderBySite() {
        orderBy(VideoColumns.SITE, false);
        return this;
    }

    public VideoSelection size(int... value) {
        addEquals(VideoColumns.SIZE, toObjectArray(value));
        return this;
    }

    public VideoSelection sizeNot(int... value) {
        addNotEquals(VideoColumns.SIZE, toObjectArray(value));
        return this;
    }

    public VideoSelection sizeGt(int value) {
        addGreaterThan(VideoColumns.SIZE, value);
        return this;
    }

    public VideoSelection sizeGtEq(int value) {
        addGreaterThanOrEquals(VideoColumns.SIZE, value);
        return this;
    }

    public VideoSelection sizeLt(int value) {
        addLessThan(VideoColumns.SIZE, value);
        return this;
    }

    public VideoSelection sizeLtEq(int value) {
        addLessThanOrEquals(VideoColumns.SIZE, value);
        return this;
    }

    public VideoSelection orderBySize(boolean desc) {
        orderBy(VideoColumns.SIZE, desc);
        return this;
    }

    public VideoSelection orderBySize() {
        orderBy(VideoColumns.SIZE, false);
        return this;
    }

    public VideoSelection type(String... value) {
        addEquals(VideoColumns.TYPE, value);
        return this;
    }

    public VideoSelection typeNot(String... value) {
        addNotEquals(VideoColumns.TYPE, value);
        return this;
    }

    public VideoSelection typeLike(String... value) {
        addLike(VideoColumns.TYPE, value);
        return this;
    }

    public VideoSelection typeContains(String... value) {
        addContains(VideoColumns.TYPE, value);
        return this;
    }

    public VideoSelection typeStartsWith(String... value) {
        addStartsWith(VideoColumns.TYPE, value);
        return this;
    }

    public VideoSelection typeEndsWith(String... value) {
        addEndsWith(VideoColumns.TYPE, value);
        return this;
    }

    public VideoSelection orderByType(boolean desc) {
        orderBy(VideoColumns.TYPE, desc);
        return this;
    }

    public VideoSelection orderByType() {
        orderBy(VideoColumns.TYPE, false);
        return this;
    }
}
