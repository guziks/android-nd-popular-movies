package ua.com.elius.popularmovies.data.review;

import ua.com.elius.popularmovies.data.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Data model for the {@code review} table.
 */
public interface ReviewModel extends BaseModel {

    /**
     * Get the {@code movie_id} value.
     */
    long getMovieId();

    /**
     * Get the {@code tmdb_review_id} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getTmdbReviewId();

    /**
     * Get the {@code author} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getAuthor();

    /**
     * Get the {@code content} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getContent();

    /**
     * Get the {@code url} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getUrl();
}
