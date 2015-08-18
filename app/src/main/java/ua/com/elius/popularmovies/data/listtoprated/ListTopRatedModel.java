package ua.com.elius.popularmovies.data.listtoprated;

import ua.com.elius.popularmovies.data.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Data model for the {@code list_top_rated} table.
 */
public interface ListTopRatedModel extends BaseModel {

    /**
     * Get the {@code tmdb_movie_id} value.
     */
    int getTmdbMovieId();
}
