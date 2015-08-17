package ua.com.elius.popularmovies.data.movie;

import ua.com.elius.popularmovies.data.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Data model for the {@code movie} table.
 */
public interface MovieModel extends BaseModel {

    /**
     * Get the {@code tmdb_movie_id} value.
     */
    int getTmdbMovieId();

    /**
     * Get the {@code popularity} value.
     */
    double getPopularity();

    /**
     * Get the {@code vote_average} value.
     */
    double getVoteAverage();

    /**
     * Get the {@code vote_count} value.
     */
    int getVoteCount();

    /**
     * Get the {@code title} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getTitle();

    /**
     * Get the {@code overview} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getOverview();

    /**
     * Get the {@code poster_path} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getPosterPath();

    /**
     * Get the {@code backdrop_path} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getBackdropPath();

    /**
     * Get the {@code release_date} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getReleaseDate();

    /**
     * Get the {@code like} value.
     */
    boolean getLike();
}
