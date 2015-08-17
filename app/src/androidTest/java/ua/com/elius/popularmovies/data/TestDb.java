package ua.com.elius.popularmovies.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;

import ua.com.elius.popularmovies.data.movie.MovieContentValues;
import ua.com.elius.popularmovies.data.movie.MovieCursor;
import ua.com.elius.popularmovies.data.movie.MovieSelection;
import ua.com.elius.popularmovies.data.review.ReviewContentValues;
import ua.com.elius.popularmovies.data.review.ReviewCursor;
import ua.com.elius.popularmovies.data.review.ReviewSelection;
import ua.com.elius.popularmovies.data.video.VideoContentValues;
import ua.com.elius.popularmovies.data.video.VideoCursor;
import ua.com.elius.popularmovies.data.video.VideoSelection;

public class TestDb extends AndroidTestCase {

    public final String LOG_TAG = TestDb.class.getSimpleName();

    public static final String JURASSIC_TITLE = "Jurassic World";
    public static final String JURASSIC_VIDEO_1_NAME = "Official Trailer 3";

    public static MovieContentValues sJurassicMovie;
    public static VideoContentValues sJurassicVideo1;
    public static VideoContentValues sJurassicVideo2;
    public static VideoContentValues sJurassicVideo3;
    public static ReviewContentValues sJurassicReview1;
    public static ReviewContentValues sJurassicReview2;

    static {
        sJurassicMovie = new MovieContentValues();
        sJurassicMovie.putBackdropPath("/t5KONotASgVKq4N19RyhIthWOPG.jpg");
        sJurassicMovie.putLike(false);
        sJurassicMovie.putOverview("Twenty-two years after the events of Jurassic Park, Isla Nublar now features a fully functioning dinosaur theme park, Jurassic World, as originally envisioned by John Hammond.");
        sJurassicMovie.putPopularity(33.807316);
        sJurassicMovie.putPosterPath("/uXZYawqUsChGSj54wcuBtEdUJbh.jpg");
        sJurassicMovie.putReleaseDate("2015-06-12");
        sJurassicMovie.putTitle(JURASSIC_TITLE);
        sJurassicMovie.putTmdbMovieId(135397);
        sJurassicMovie.putVoteAverage(7.0);
        sJurassicMovie.putVoteCount(1919);

        sJurassicVideo1 = new VideoContentValues();
        sJurassicVideo1.putKey("lP-sUUUfamw");
        sJurassicVideo1.putName(JURASSIC_VIDEO_1_NAME);
        sJurassicVideo1.putSite("YouTube");
        sJurassicVideo1.putSize(720);
        sJurassicVideo1.putTmdbVideoId("5576eac192514111e4001b03");
        sJurassicVideo1.putType("Trailer");

        sJurassicVideo2 = new VideoContentValues();
        sJurassicVideo2.putKey("bvu-zlR5A8Q");
        sJurassicVideo2.putName("Teaser");
        sJurassicVideo2.putSite("YouTube");
        sJurassicVideo2.putSize(1080);
        sJurassicVideo2.putTmdbVideoId("54749bea9251414f41001b58");
        sJurassicVideo2.putType("Teaser");

        sJurassicVideo3 = new VideoContentValues();
        sJurassicVideo3.putKey("RFinNxS5KN4");
        sJurassicVideo3.putName("Official Trailer");
        sJurassicVideo3.putSite("YouTube");
        sJurassicVideo3.putSize(1080);
        sJurassicVideo3.putTmdbVideoId("5474d2339251416e58002ae1");
        sJurassicVideo3.putType("Trailer");

        sJurassicReview1 = new ReviewContentValues();
        sJurassicReview1.putAuthor("jonlikesmoviesthatdontsuck");
        sJurassicReview1.putContent("I was a huge fan of the original 3 movies, they were out when I was younger, and I grew up loving dinosaurs because of them. This movie was awesome, and I think it can stand as a testimonial piece towards the capabilities that Christopher Pratt has. He nailed it. The graphics were awesome, the supporting cast did great and the t rex saved the child in me. 10\5 stars, four thumbs up, and I hope that star wars episode VII doesn't disappoint,");
        sJurassicReview1.putTmdbReviewId("55910381c3a36807f900065d");
        sJurassicReview1.putUrl("http://j.mp/1GHgSxi");

        sJurassicReview2 = new ReviewContentValues();
        sJurassicReview2.putAuthor("Ganesan");
        sJurassicReview2.putContent("Overall action packed movie... But there should be more puzzles in the climax... But I really love the movie.... Excellent...");
        sJurassicReview2.putTmdbReviewId("559238f89251415df80000aa");
        sJurassicReview2.putUrl("http://j.mp/1FMD5JI");
    }

    void deleteTheDatabase() {
        mContext.deleteDatabase(MovieDBHelper.DATABASE_FILE_NAME);
    }

    public void testGeneral() {
        ContentResolver resolver = getContext().getContentResolver();

        deleteTheDatabase();

        Uri jurassicMovieUri;
        jurassicMovieUri = resolver.insert(
                sJurassicMovie.uri(),
                sJurassicMovie.values()
        );

        long jurassicMovieId = ContentUris.parseId(jurassicMovieUri);

        sJurassicVideo1.putMovieId(jurassicMovieId);
        Uri jurassicVideo1Uri = resolver.insert(
                sJurassicVideo1.uri(),
                sJurassicVideo1.values()
        );

        sJurassicVideo2.putMovieId(jurassicMovieId);
        Uri jurassicVideo2Uri = resolver.insert(
                sJurassicVideo2.uri(),
                sJurassicVideo2.values()
        );

        sJurassicVideo3.putMovieId(jurassicMovieId);
        Uri jurassicVideo3Uri = resolver.insert(
                sJurassicVideo3.uri(),
                sJurassicVideo3.values()
        );

        sJurassicReview1.putMovieId(jurassicMovieId);
        Uri sJurassicReview1Uri = resolver.insert(
                sJurassicReview1.uri(),
                sJurassicReview1.values()
        );

        sJurassicReview2.putMovieId(jurassicMovieId);
        Uri sJurassicReview2Uri = resolver.insert(
                sJurassicReview2.uri(),
                sJurassicReview2.values()
        );

        Cursor c;
        MovieCursor mc;
        VideoCursor vc;
        ReviewCursor rc;
        ReviewSelection rs;
        MovieSelection ms;
        VideoSelection vs;

        ms = new MovieSelection();
        mc = ms.query(resolver);
        mc.moveToFirst();
        assertEquals(JURASSIC_TITLE, mc.getTitle());
        assertFalse(mc.getLike());
        mc.close();

        vs = new VideoSelection();
        vc = vs.query(resolver);
        vc.moveToFirst();
        assertEquals(JURASSIC_VIDEO_1_NAME, vc.getName());
        vc.close();

        rs = new ReviewSelection();
        rs.movieTitle(JURASSIC_TITLE);
        rc = rs.query(getContext().getContentResolver());
        assertTrue(rc.getCount() == 2); // previously we've put two reviews

        // put a like to jurassic world
        MovieContentValues movieLike = new MovieContentValues();
        movieLike.putLike(true);
        ms = new MovieSelection();
        ms.title(JURASSIC_TITLE);
        movieLike.update(getContext(), ms);

        mc = ms.query(getContext());
        mc.moveToFirst();
        assertTrue(mc.getLike());
        mc.close();

//        int rowsDeleted;
//        rowsDeleted = ms.delete(getContext());
//        assertEquals(1, rowsDeleted);

    }

}
