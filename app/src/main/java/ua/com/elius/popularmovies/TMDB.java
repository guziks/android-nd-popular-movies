package ua.com.elius.popularmovies;

import android.net.Uri;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ua.com.elius.popularmovies.data.movie.MovieContentValues;
import ua.com.elius.popularmovies.data.review.ReviewContentValues;
import ua.com.elius.popularmovies.data.video.VideoContentValues;

public class TMDB {

    private final String LOG_TAG = TMDB.class.getSimpleName();

    public static final String API_KEY = "***REMOVED***";

    public static final String SCHEME = "https";
    public static final String API_AUTHORITY = "api.themoviedb.org";
    public static final String API_VERSION = "3";
    public static final String API_POPULAR_MOVIES_PATH = "movie/popular";
    public static final String API_TOP_RATED_MOVIES_PATH = "movie/top_rated";
    public static final String API_KEY_PARAM = "api_key";
    public static final String IMAGE_AUTHORITY = "image.tmdb.org/t/p";
    public static final String POSTER_WIDTH = "w342";
    public static final String BACKDROP_WIDTH = "w780";

    private String mApiKey;

    public TMDB(String apiKey) {
        mApiKey = apiKey;
    }

    public String getKey() {
        return mApiKey;
    }

    public Movies getPopularMovies() {
        Uri uri = new Uri.Builder()
                .scheme(SCHEME)
                .authority(API_AUTHORITY)
                .path(API_VERSION)
                .appendEncodedPath(API_POPULAR_MOVIES_PATH)
                .appendQueryParameter(API_KEY_PARAM, mApiKey)
                .build();
        return getMovies(uri);
    }

    public Movies getTopRatedMovies() {
        Uri uri = new Uri.Builder()
                .scheme(SCHEME)
                .authority(API_AUTHORITY)
                .path(API_VERSION)
                .appendEncodedPath(API_TOP_RATED_MOVIES_PATH)
                .appendQueryParameter(API_KEY_PARAM, mApiKey)
                .build();
        return getMovies(uri);
    }

    public Movies getMovies(Uri uri) {
        Movies movies = new Movies();

        Log.d(LOG_TAG, uri.toString());
        JSONObject response = null;
        try {
            response = doRequest(uri.toString());
        } catch (IOException e) {
            Log.e(LOG_TAG, "Fail to request movies data", e);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Response is not valid JSON", e);
        }

        if (response != null) {
            JSONArray results;
            try {
                results = response.getJSONArray("results");
                Log.d(LOG_TAG, results.toString());
                for (int i = 0; i < results.length(); i++) {
                    JSONObject movieJSON = results.getJSONObject(i);
                    Log.d(LOG_TAG, movieJSON.toString());
                    movies.add(new Movie(movieJSON));
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Unexpected JSON format", e);
            }
        }
        Log.d(LOG_TAG, String.valueOf(movies.size()));

        return movies;
    }

    private JSONObject doRequest(String url) throws IOException, JSONException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response;
        String responseString;
        JSONObject responseJSON = null;
        response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            responseString = response.body().string();
            Log.d(LOG_TAG + " " + "doRequest", responseString);
            responseJSON = new JSONObject(responseString);
        }

        return responseJSON;
    }
}

class Movie {

    int    mId;           // 135397
    double mPopularity;   // 87.551849
    double mVoteAverage;  // 7.1
    int    mVoteCount;    // 431
    String mTitle;        // "Jurassic World"
    String mOverview;     // "Twenty-two years after the events of Jurassic Park, Isla Nublar now features a fully functioning dinosaur theme park, Jurassic World, as originally envisioned by John Hammond."
    String mPosterPath;   // "/uXZYawqUsChGSj54wcuBtEdUJbh.jpg"
    String mBackdropPath; // "/dkMD5qlogeRMiEixC4YNPUvax2T.jpg"
    String mReleaseDate;  // "2015-06-12"

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public int getId() {
        return mId;
    }

    public double getPopularity() {
        return mPopularity;
    }

    public double getVoteAverage() {
        return mVoteAverage;
    }

    public int getVoteCount() {
        return mVoteCount;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getOverview() {
        return mOverview;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public String getPosterURL() {
        Uri uri = new Uri.Builder()
                .scheme(TMDB.SCHEME)
                .encodedAuthority(TMDB.IMAGE_AUTHORITY)
                .encodedPath(TMDB.POSTER_WIDTH)
                .appendEncodedPath(mPosterPath.replace("/", ""))
                .build();
        return uri.toString();
    }

    public String getBackdropURL() {
        Uri uri = new Uri.Builder()
                .scheme(TMDB.SCHEME)
                .encodedAuthority(TMDB.IMAGE_AUTHORITY)
                .encodedPath(TMDB.BACKDROP_WIDTH)
                .appendEncodedPath(mBackdropPath.replace("/", ""))
                .build();
        return uri.toString();
    }

    public MovieContentValues getValues() {
        MovieContentValues values;
        values = new MovieContentValues();
        values.putBackdropPath(mBackdropPath);
        values.putOverview(mOverview);
        values.putPopularity(mPopularity);
        values.putPosterPath(mPosterPath);
        values.putReleaseDate(mReleaseDate);
        values.putTitle(mTitle);
        values.putTmdbMovieId(mId);
        values.putVoteAverage(mVoteAverage);
        values.putVoteCount(mVoteCount);
        return values;
    }

    public Movie(JSONObject json) {
        try {
            this.mId = json.getInt("id");
            this.mPopularity   = json.getDouble("popularity");
            this.mVoteAverage  = json.getDouble("vote_average");
            this.mVoteCount    = json.getInt("vote_count");
            this.mTitle        = json.getString("title");
            this.mOverview     = json.getString("overview");
            this.mPosterPath   = json.getString("poster_path");
            this.mBackdropPath = json.getString("backdrop_path");
            this.mReleaseDate  = json.getString("release_date");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

class Movies extends ArrayList<Movie> {

    public List<String> getPosterURLs() {
        List<String> posters = new ArrayList<>();

        for (Movie movie : this) {
            posters.add(movie.getPosterURL());
        }

        return posters;
    }

}

class Video {
    int    mSize;
    String mKey;
    String mName;
    String mSite;
    String mTmdbVideoId;
    String mType;

    public int getmSize() {
        return mSize;
    }

    public String getmKey() {
        return mKey;
    }

    public String getmName() {
        return mName;
    }

    public String getmSite() {
        return mSite;
    }

    public String getmTmdbVideoId() {
        return mTmdbVideoId;
    }

    public String getmType() {
        return mType;
    }

    public VideoContentValues getValues() {
        VideoContentValues values;
        values = new VideoContentValues();
        values.putKey(mKey);
        values.putName(mName);
        values.putSite(mSite);
        values.putSize(mSize);
        values.putTmdbVideoId(mTmdbVideoId);
        values.putType(mType);
        return values;
    }

    public Video(JSONObject json) {
        try {
            this.mSize        = json.getInt("size");
            this.mKey         = json.getString("key");
            this.mName        = json.getString("name");
            this.mSite        = json.getString("site");
            this.mTmdbVideoId = json.getString("id");
            this.mType        = json.getString("type");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

class Videos extends ArrayList<Video> {}

class Review {
    String mAuthor;
    String mContent;
    String mTmdbReviewId;
    String mUrl;

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmContent() {
        return mContent;
    }

    public String getmTmdbReviewId() {
        return mTmdbReviewId;
    }

    public String getmUrl() {
        return mUrl;
    }

    public ReviewContentValues getValues() {
        ReviewContentValues values;
        values = new ReviewContentValues();
        values.putAuthor(mAuthor);
        values.putContent(mContent);
        values.putTmdbReviewId(mTmdbReviewId);
        values.putUrl(mUrl);
        return values;
    }

    public Review(JSONObject json) {
        try {
            this.mAuthor       = json.getString("author");
            this.mContent      = json.getString("content");
            this.mTmdbReviewId = json.getString("id");
            this.mUrl          = json.getString("url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

class Reviews extends ArrayList<Review> {}