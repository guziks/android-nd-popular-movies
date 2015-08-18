package ua.com.elius.popularmovies.data.listpopular;

import android.net.Uri;
import android.provider.BaseColumns;

import ua.com.elius.popularmovies.data.MovieProvider;
import ua.com.elius.popularmovies.data.listpopular.ListPopularColumns;
import ua.com.elius.popularmovies.data.listtoprated.ListTopRatedColumns;
import ua.com.elius.popularmovies.data.movie.MovieColumns;
import ua.com.elius.popularmovies.data.review.ReviewColumns;
import ua.com.elius.popularmovies.data.video.VideoColumns;

/**
 * Columns for the {@code list_popular} table.
 */
public class ListPopularColumns implements BaseColumns {
    public static final String TABLE_NAME = "list_popular";
    public static final Uri CONTENT_URI = Uri.parse(MovieProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String TMDB_MOVIE_ID = "tmdb_movie_id";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            TMDB_MOVIE_ID
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(TMDB_MOVIE_ID) || c.contains("." + TMDB_MOVIE_ID)) return true;
        }
        return false;
    }

}
