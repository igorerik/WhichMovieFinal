package br.com.eraf.whichmovie;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MovieDescriptionActivity extends AppCompatActivity {
    private TextView overviewDescriptionTextView;
    private TextView titleDescriptionTextView;
    private ImageView backdropPathImageView;



    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_description);

        getSupportActionBar().setTitle(R.string.now_movie_description);
        progressBar = findViewById(R.id.progressBar);

        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString("title");
        String overview = bundle.getString("overview");
        String poster_path = bundle.getString("poster_path");
        String backdrop_path = bundle.getString("backdrop_path");

        overviewDescriptionTextView = findViewById(R.id.overviewDescriptionTextView);
        overviewDescriptionTextView.setText(overview);

        titleDescriptionTextView = findViewById(R.id.titleDescriptionTextView);
        titleDescriptionTextView.setText(title);

        backdropPathImageView = findViewById(R.id.backdropPathImageView);
        new BaixaImagem(backdrop_path).execute(this.getString(R.string.web_service_url_poster, backdrop_path));

        progressBar.setVisibility(View.INVISIBLE);

    }

    private class BaixaImagem extends AsyncTask<String, Void, Bitmap> {

        private String iconName;
        BaixaImagem (String iconName){

            this.iconName = iconName;
        }
        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection =
                        (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                Bitmap figura = BitmapFactory.decodeStream(inputStream);
                return figura;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap figura) {
            backdropPathImageView.setImageBitmap (figura);
        }
    }
}
