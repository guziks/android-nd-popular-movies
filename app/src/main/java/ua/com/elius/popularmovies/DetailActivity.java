package ua.com.elius.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ImageView backdrop    = (ImageView) findViewById(R.id.backdrop);
        TextView  title       = (TextView)  findViewById(R.id.title);
        TextView  overview    = (TextView)  findViewById(R.id.overview);
        TextView  releaseDate = (TextView)  findViewById(R.id.release_date);
        TextView  rating      = (TextView)  findViewById(R.id.rating);

        Intent intent = getIntent();

        Glide.with(this)
                .load(intent.getStringExtra("backdropURL"))
                .into(backdrop);
        title.setText(intent.getStringExtra("title"));
        overview.setText(intent.getStringExtra("overview"));
        releaseDate.setText(intent.getStringExtra("releaseDate"));
        rating.setText(intent.getStringExtra("rating"));
    }
}
