package br.com.eraf.whichmovie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class GenreAdapter extends ArrayAdapter<Genre> {

    private class ViewHolder {
        public TextView genreNameTextView;
    }

    public GenreAdapter (Context context, List<Genre> forecast){
        super (context, R.layout.list_genre, forecast);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Context context = getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_genre, parent, false);
            viewHolder = new ViewHolder();
            convertView.setTag(viewHolder);
            viewHolder.genreNameTextView = convertView.findViewById(R.id.genreNameTextView);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        Genre caraDaVez = getItem(position);
        viewHolder.genreNameTextView.setText(caraDaVez.genreName);
        return convertView;
    }
}