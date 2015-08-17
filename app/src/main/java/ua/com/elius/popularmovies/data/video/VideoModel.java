package ua.com.elius.popularmovies.data.video;

import ua.com.elius.popularmovies.data.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Data model for the {@code video} table.
 */
public interface VideoModel extends BaseModel {

    /**
     * Get the {@code movie_id} value.
     */
    long getMovieId();

    /**
     * Get the {@code tmdb_video_id} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getTmdbVideoId();

    /**
     * Get the {@code key} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getKey();

    /**
     * Get the {@code name} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getName();

    /**
     * Get the {@code site} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getSite();

    /**
     * Get the {@code size} value.
     */
    int getSize();

    /**
     * Get the {@code type} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getType();
}
