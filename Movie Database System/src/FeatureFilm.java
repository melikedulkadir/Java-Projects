import java.util.ArrayList;

public class FeatureFilm extends Film{
    private String genre;
    private String releaseDate;
    private int budget;

    ArrayList<Integer> writers = new ArrayList<Integer>();
    public FeatureFilm(int id,String title, String language,String directorIds,int runtime,String country,String performerIds,String genre,String releaseDate, String writerIds, int budget){
        super(id,title,language,directorIds,runtime,country,performerIds);
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.budget = budget;
        for (int i = 0; i < (writerIds.split(",").length); i++) {
            writers.add(Integer.parseInt(writerIds.split(",")[i]));
        }
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String  releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
