package br.com.eraf.whichmovie;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    private List<Genre> previsoes;
    private GenreAdapter adapter;
    private ListView weatherListView;
    private ProgressBar progressBar;
    private TextView genreNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);

        previsoes = new ArrayList<>();
        weatherListView = findViewById(R.id.weatherListView);

        String end = getString(R.string.web_service_url_moviedb);
        new ObtemTemperaturas().execute(end);

        weatherListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, previsoes.get(position).getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class ObtemTemperaturas extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... enderecos) {
            try{
                URL url = new URL(enderecos[0]);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
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
            previsoes.clear();
            try {
                JSONObject json = new JSONObject(jsonS);
                JSONArray list = json.getJSONArray("genres");
                for (int i = 0; i < list.length(); i++) {
                    JSONObject previsao = list.getJSONObject(i);
                    int genreId = previsao.getInt("id");
                    String genreName = previsao.getString("name");
                    Genre forecast =
                            new Genre(genreId, genreName);
                    previsoes.add(forecast);
                }
                //Toast.makeText(MainActivity.this, previsoes.toString(), Toast.LENGTH_SHORT).show();
                adapter = new GenreAdapter(getApplicationContext(), previsoes);
                weatherListView.setAdapter(adapter);
                progressBar.setVisibility(View.INVISIBLE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
