package ua.com.elius.popularmovies.data.movie;

import android.net.Uri;
import android.provider.BaseColumns;

import ua.com.elius.popularmovies.data.MovieProvider;
import ua.com.elius.popularmovies.data.listpopular.ListPopularColumns;
import ua.com.elius.popularmovies.data.listtoprated.ListTopRatedColumns;
import ua.com.elius.popularmovies.data.movie.MovieColumns;
import ua.com.elius.popularmovies.data.review.ReviewColumns;
import ua.com.elius.popularmovies.data.video.VideoColumns;

/**
 * Columns for the {@code movie} table.
 */
public class MovieColumns implements BaseColumns {
    public static final String TABLE_NAME = "movie";
    public static final Uri CONTENT_URI = Uri.parse(MovieProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String TMDB_MOVIE_ID = "tmdb_movie_id";

    public static final String POPULARITY = "popularity";

    public static final String VOTE_AVERAGE = "vote_average";

    public static final String VOTE_COUNT = "vote_count";

    public static final String TITLE = "title";

    public static final String OVERVIEW = "overview";

    public static final String POSTER_PATH = "poster_path";

    public static final String BACKDROP_PATH = "backdrop_path";

    public static final String RELEASE_DATE = "release_date";

    public static final String LIKE = "like";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            TMDB_MOVIE_ID,
            POPULARITY,
            VOTE_AVERAGE,
            VOTE_COUNT,
            TITLE,
            OVERVIEW,
            POSTER_PATH,
            BACKDROP_PATH,
            RELEASE_DATE,
            LIKE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(TMDB_MOVIE_ID) || c.contains("." + TMDB_MOVIE_ID)) return true;
            if (c.equals(POPULARITY) || c.contains("." + POPULARITY)) return true;
            if (c.equals(VOTE_AVERAGE) || c.contains("." + VOTE_AVERAGE)) return true;
            if (c.equals(VOTE_COUNT) || c.contains("." + VOTE_COUNT)) return true;
            if (c.equals(TITLE) || c.contains("." + TITLE)) return true;
            if (c.equals(OVERVIEW) || c.contains("." + OVERVIEW)) return true;
            if (c.equals(POSTER_PATH) || c.contains("." + POSTER_PATH)) return true;
            if (c.equals(BACKDROP_PATH) || c.contains("." + BACKDROP_PATH)) return true;
            if (c.equals(RELEASE_DATE) || c.contains("." + RELEASE_DATE)) return true;
            if (c.equals(LIKE) || c.contains("." + LIKE)) return true;
        }
        return false;
    }

}
