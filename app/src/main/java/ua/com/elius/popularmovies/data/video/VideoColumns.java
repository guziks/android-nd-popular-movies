package ua.com.elius.popularmovies.data.video;

import android.net.Uri;
import android.provider.BaseColumns;

import ua.com.elius.popularmovies.data.MovieProvider;
import ua.com.elius.popularmovies.data.listpopular.ListPopularColumns;
import ua.com.elius.popularmovies.data.listtoprated.ListTopRatedColumns;
import ua.com.elius.popularmovies.data.movie.MovieColumns;
import ua.com.elius.popularmovies.data.review.ReviewColumns;
import ua.com.elius.popularmovies.data.video.VideoColumns;

/**
 * Columns for the {@code video} table.
 */
public class VideoColumns implements BaseColumns {
    public static final String TABLE_NAME = "video";
    public static final Uri CONTENT_URI = Uri.parse(MovieProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String MOVIE_ID = "movie_id";

    public static final String TMDB_VIDEO_ID = "tmdb_video_id";

    public static final String KEY = "key";

    public static final String NAME = "name";

    public static final String SITE = "site";

    public static final String SIZE = "size";

    public static final String TYPE = "type";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            MOVIE_ID,
            TMDB_VIDEO_ID,
            KEY,
            NAME,
            SITE,
            SIZE,
            TYPE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(MOVIE_ID) || c.contains("." + MOVIE_ID)) return true;
            if (c.equals(TMDB_VIDEO_ID) || c.contains("." + TMDB_VIDEO_ID)) return true;
            if (c.equals(KEY) || c.contains("." + KEY)) return true;
            if (c.equals(NAME) || c.contains("." + NAME)) return true;
            if (c.equals(SITE) || c.contains("." + SITE)) return true;
            if (c.equals(SIZE) || c.contains("." + SIZE)) return true;
            if (c.equals(TYPE) || c.contains("." + TYPE)) return true;
        }
        return false;
    }

    public static final String PREFIX_MOVIE = TABLE_NAME + "__" + MovieColumns.TABLE_NAME;
}
