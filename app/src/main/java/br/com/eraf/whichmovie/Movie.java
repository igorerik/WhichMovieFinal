package br.com.eraf.whichmovie;

public class Movie {

    public final int id;
    public final String title;
    public final String poster_path;
    public final String release_date;
    public final String vote_average;
    public final String overview;
    public final String backdrop_path;


    public String getIconName() {
        return poster_path;
    }

    public Movie(int id, String title, String release_date, String vote_average, String iconName, String overview, String backdrop_path) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.vote_average = vote_average;
        this.poster_path = iconName;
        this.overview = overview;
        this.backdrop_path = backdrop_path;
    }

    @Override
    public String toString() {
        return title;
    }
}
