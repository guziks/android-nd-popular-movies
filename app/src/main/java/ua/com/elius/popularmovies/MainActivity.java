package ua.com.elius.popularmovies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity
        implements PostersFragment.OnFragmentInteractionListener,
                   PostersFragment.TmdbMovieIdReceiver,
                   DetailFragment.TmdbMovieIdProvider  {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    private int mTmdbMovieId;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(this, R.xml.pref_general, false);

        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_detail_container) != null) mTwoPane = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void setTmdbMovieId(int id) {
        mTmdbMovieId = id;
        if (mTwoPane) {
            DetailFragment detailFragment = new DetailFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_detail_container, detailFragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("id", mTmdbMovieId);
            startActivity(intent);
        }
    }

    @Override
    public int getTmdbMovieId() {
        return mTmdbMovieId;
    }
}
