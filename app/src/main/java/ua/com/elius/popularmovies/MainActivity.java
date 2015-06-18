package ua.com.elius.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PosterAdapter<String> adapter = new PosterAdapter<>(this, R.layout.poster, R.id.poster_image_view);
        adapter.add("http://ia.media-imdb.com/images/M/MV5BMTUwNDU4NjE1N15BMl5BanBnXkFtZTgwOTc0MzA5NDE@._V1_UY209_CR0,0,140,209_AL_.jpg");
        adapter.add("http://ia.media-imdb.com/images/M/MV5BMTk0Mzg4NDYyOF5BMl5BanBnXkFtZTgwOTM2ODg2NTE@._V1_UX140_CR0,0,140,209_AL_.jpg");
        adapter.add("http://ia.media-imdb.com/images/M/MV5BMTQ3NjYxMDE1OV5BMl5BanBnXkFtZTgwMjgzOTE4MzE@._V1_UY209_CR1,0,140,209_AL_.jpg");
        adapter.add("http://ia.media-imdb.com/images/M/MV5BMTUyMTE0ODcxNF5BMl5BanBnXkFtZTgwODE4NDQzNTE@._V1_UY209_CR2,0,140,209_AL_.jpg");
        adapter.add("http://ia.media-imdb.com/images/M/MV5BMTU4MDU3NDQ5Ml5BMl5BanBnXkFtZTgwOTU5MDUxNTE@._V1_UY209_CR1,0,140,209_AL_.jpg");
        adapter.add("http://ia.media-imdb.com/images/M/MV5BMTQ4OTgzNTkwNF5BMl5BanBnXkFtZTgwMzI3MDE3NDE@._V1_UY209_CR0,0,140,209_AL_.jpg");
        adapter.add("http://ia.media-imdb.com/images/M/MV5BMTk1MTkwMzU4Nl5BMl5BanBnXkFtZTgwNjY0MDE1NTE@._V1_UY209_CR0,0,140,209_AL_.jpg");
        adapter.add("http://ia.media-imdb.com/images/M/MV5BMTQ5MTE0MTk3Nl5BMl5BanBnXkFtZTgwMjczMzk2NTE@._V1_UX140_CR0,0,140,209_AL_.jpg");
        adapter.add("http://ia.media-imdb.com/images/M/MV5BNjI5OTQ0MDQxM15BMl5BanBnXkFtZTgwMzcwNjMyNTE@._V1_UY209_CR0,0,140,209_AL_.jpg");
        adapter.add("http://st.kp.yandex.net/images/film_iphone/iphone360_594554.jpg");

        GridView gridview = (GridView) findViewById(R.id.poster_grid);
        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
