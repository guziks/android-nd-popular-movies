package ua.com.elius.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


public class DetailActivity extends AppCompatActivity
        implements DetailFragment.TmbdMovieIdHolder {

    private final String LOG_TAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                Intent up = new Intent(this, MainActivity.class);
                up.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                up.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(up);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getTmdbMovieId() {
        return getIntent().getIntExtra("id", 0);
    }
}
