package br.com.eraf.whichmovie;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MovieActivity  extends TemplateActivity {

    private List<Movie> movieList;
    private MovieAdapter movieAdapter;
    private ListView movieListView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        getSupportActionBar().setTitle(R.string.now_movie);

        progressBar = findViewById(R.id.progressBar);
        movieList = new ArrayList<>();
        movieListView = findViewById(R.id.movieListView);
        movieAdapter = new MovieAdapter(this, movieList);
        movieListView.setAdapter(movieAdapter);

        int idGenero = getIntent().getIntExtra("idGenre", 0);
        String idGenre = Integer.toString(idGenero);

        String end = getString(R.string.web_service_url_movie, getString(R.string.api_key), getString(R.string.lang), idGenre);
        new ObtemFilmes().execute(end);

        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = (Movie) movieListView.getItemAtPosition(position);
                Intent intent = new Intent(MovieActivity.this, MovieDescriptionActivity.class);
                intent.putExtra("title", movie.title);
                intent.putExtra("overview",  movie.overview);
                intent.putExtra("poster_path", movie.poster_path);
                intent.putExtra("backdrop_path", movie.backdrop_path);
                intent.putExtra("idFilme", movie.id);
                startActivity(intent);
            }
        });
    }

    private class ObtemFilmes extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... enderecos) {
            try{
                URL url = new URL(enderecos[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String linha = null;
                final StringBuilder stringBuilder = new StringBuilder("");
                while ((linha = reader.readLine()) != null)
                    stringBuilder.append(linha);
                reader.close();
                return stringBuilder.toString();
            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String jsonS) {
            super.onPostExecute(jsonS);
            try{
                movieList.clear();
                JSONObject json = new JSONObject(jsonS);
                JSONArray list = json.getJSONArray("results");
                for (int i = 0; i < list.length(); i++){
                    JSONObject filme = list.getJSONObject(i);
                    int id = filme.getInt("id");
                    String title = filme.getString("title");
                    String release_date = filme.getString("release_date");
                    String vote_average = filme.getString("vote_average");
                    String poster_path = filme.getString("poster_path");
                    String overview = filme.getString("overview");
                    String backdrop_path = filme.getString("backdrop_path");
                    Movie f = new Movie(id, title, release_date, vote_average, poster_path, overview, backdrop_path);
                    movieList.add(f);
                }
                // Toast.makeText(MovieActivity.this, movieList.toString(), Toast.LENGTH_SHORT).show();
                movieAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);
            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }
    }
}
