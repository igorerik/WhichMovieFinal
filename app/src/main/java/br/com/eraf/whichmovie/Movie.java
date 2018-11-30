package br.com.eraf.whichmovie;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    public final String posterPath;
    public final boolean adult;
    public final String overview;
    public final String releaseDate;
    public final List<Integer> genreIds = new ArrayList<Integer>();
    public final Integer id;
    public final String originalTitle;
    public final String originalLanguage;
    public final String title;
    public final String backdropPath;
    public final Double popularity;
    public final Integer voteCount;
    public final Boolean video;
    public final Double voteAverage;


    public Movie (String posterPath, boolean adult, String overview, String releaseDate, List<Integer> genreIds, Integer id, String originalTitle, String originalLanguage, String title, String backdropPath, Double popularity, Integer voteCount, Boolean video, Double voteAverage) {
        this.posterPath = posterPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
        //this.genreIds = genreIds;
        this.id = id;
        this.originalTitle = originalTitle;
        this.originalLanguage = originalLanguage;
        this.title = title;
        this.backdropPath = backdropPath;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.video = video;
        this.voteAverage = voteAverage;
    }

    public Integer getId() {
        return id;
    }

    /*
    @Override
    public String toString() {
        return dayOfWeek + ", min: " + minTemp + ", max: " + maxTemp;
    }*/
}
