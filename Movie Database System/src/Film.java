import java.util.ArrayList;

public class Film {
    private int filmId;
    private String filmTitle;
    private String language;
    private int runtime;
    private String country;
    private double ratingScore = 0.0;
    private int raterCount = 0;
    ArrayList<Integer> directors = new ArrayList<Integer>();
    ArrayList<Integer> cast = new ArrayList<Integer>();

    public Film(int filmId,String filmTitle,String language, String directorIds,int runtime,String country,String performerIds){
        this.filmId = filmId;
        this.filmTitle = filmTitle;
        this.language = language;
        this.runtime = runtime;
        this.country = country;
        for (int i = 0; i < (directorIds.split(",").length); i++) {
            directors.add(Integer.parseInt(directorIds.split(",")[i]));
        }
        for (int i = 0; i < (performerIds.split(",").length); i++) {
            cast.add(Integer.parseInt(performerIds.split(",")[i]));
        }
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public double getRatingScore() {
        if (ratingScore==0.0)
        return 0;
        else return ratingScore/raterCount;
    }

    public void setRatingScore(double ratingScore) {

        this.ratingScore = this.ratingScore + ratingScore;

    }

    public int getRaterCount() {
        return this.raterCount;
    }

    public void setRaterCount(int raterCount) {
        this.raterCount = this.raterCount + raterCount;
    }

    public String toString() {
        return getClass().getName();
    }

}
