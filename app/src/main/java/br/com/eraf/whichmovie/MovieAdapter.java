package br.com.eraf.whichmovie;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class MovieAdapter extends ArrayAdapter<Movie> {
    private static Map<String, Bitmap> figuras = new HashMap<>();
    private static Map<String, Bitmap> backdrops = new HashMap<>();

    public static Map<String, Bitmap> getFiguras() {
        return figuras;
    }

    public MovieAdapter(@NonNull Context context, List<Movie> movieList) {
        super(context, -1, movieList);
    }

    private class ViewHolder {
        public ImageView posterPathImageView;
        public TextView titleTextView;
        public TextView releaseDateTextView;
        public TextView voteAverageTextView;
        public TextView overview;
        public ImageView backdropPathImageView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Context context = getContext();
        Movie caraDaVez = getItem(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_movie, parent, false);
            viewHolder = new ViewHolder();
            convertView.setTag(viewHolder);
            viewHolder.titleTextView= convertView.findViewById(R.id.titleTextView);
            viewHolder.releaseDateTextView = convertView.findViewById(R.id.releaseDateTextView);
            viewHolder.voteAverageTextView = convertView.findViewById(R.id.voteAverageTextView);
            viewHolder.overview = convertView.findViewById(R.id.overview);
            viewHolder.posterPathImageView = convertView.findViewById(R.id.posterPathImageView);
            viewHolder.backdropPathImageView = convertView.findViewById(R.id.backdropPathImageView);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        if (figuras.containsKey(caraDaVez.poster_path)){
            Bitmap figura = figuras.get(caraDaVez.poster_path);
            viewHolder.posterPathImageView.setImageBitmap(figura);
        } else {
            new BaixaImagem(viewHolder.posterPathImageView, caraDaVez.poster_path).
                    execute(context.getString(R.string.web_service_url_poster, caraDaVez.poster_path));

        }




        viewHolder.titleTextView.setText(context.getString(R.string.title_movie, caraDaVez.title));
        viewHolder.releaseDateTextView.setText(context.getString(R.string.lancamento, caraDaVez.release_date));
        viewHolder.voteAverageTextView.setText(context.getString(R.string.voto, caraDaVez.vote_average));
        viewHolder.overview.setText(caraDaVez.overview);
        return convertView;
    }
    private class BaixaImagem extends AsyncTask<String, Void, Bitmap> {
        private ImageView filmeImageView;
        private String iconName;
        BaixaImagem (ImageView filmeImageView, String iconName){
            this.filmeImageView = filmeImageView;
            this.iconName = iconName;
        }
        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
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
            filmeImageView.setImageBitmap (figura);
            figuras.put(iconName,  figura);
        }
    }
}
