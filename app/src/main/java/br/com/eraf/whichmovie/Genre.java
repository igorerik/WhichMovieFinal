package br.com.eraf.whichmovie;

public class Genre {
    public final int genreId;
    public final String genreName;

    public Genre (int genreId, String genreName) {
        this.genreId = genreId;
        this.genreName = genreName;
    }

    public String getId() {
        return String.valueOf(genreId);
    }

    @Override
    public String toString() {
        return genreName;
    }
}
