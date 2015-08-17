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

    int    mID;           // 135397
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

    public int getID() {
        return mID;
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

    public Movie(JSONObject json) {
        try {
            this.mID           = json.getInt("id");
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
