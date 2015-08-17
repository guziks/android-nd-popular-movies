package ua.com.elius.popularmovies.data.review;

import android.net.Uri;
import android.provider.BaseColumns;

import ua.com.elius.popularmovies.data.MovieProvider;
import ua.com.elius.popularmovies.data.movie.MovieColumns;
import ua.com.elius.popularmovies.data.review.ReviewColumns;
import ua.com.elius.popularmovies.data.video.VideoColumns;

/**
 * Columns for the {@code review} table.
 */
public class ReviewColumns implements BaseColumns {
    public static final String TABLE_NAME = "review";
    public static final Uri CONTENT_URI = Uri.parse(MovieProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String MOVIE_ID = "movie_id";

    public static final String TMDB_REVIEW_ID = "tmdb_review_id";

    public static final String AUTHOR = "author";

    public static final String CONTENT = "content";

    public static final String URL = "url";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            MOVIE_ID,
            TMDB_REVIEW_ID,
            AUTHOR,
            CONTENT,
            URL
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(MOVIE_ID) || c.contains("." + MOVIE_ID)) return true;
            if (c.equals(TMDB_REVIEW_ID) || c.contains("." + TMDB_REVIEW_ID)) return true;
            if (c.equals(AUTHOR) || c.contains("." + AUTHOR)) return true;
            if (c.equals(CONTENT) || c.contains("." + CONTENT)) return true;
            if (c.equals(URL) || c.contains("." + URL)) return true;
        }
        return false;
    }

    public static final String PREFIX_MOVIE = TABLE_NAME + "__" + MovieColumns.TABLE_NAME;
}
