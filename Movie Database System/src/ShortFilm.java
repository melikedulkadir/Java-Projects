import java.util.ArrayList;

public class ShortFilm extends Film{
    private String releaseDate;
    private String genre;
    ArrayList<Integer> writers = new ArrayList<Integer>();
    public ShortFilm(int id,String title, String language,String directorIds,int runtime,String country,String performerIds,String genre,String releaseDate, String writerIds){
        super(id,title,language,directorIds,runtime,country,performerIds);
        this.genre = genre;
        this.releaseDate = releaseDate;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setRuntime(int runtime) {
       if (runtime<=40)
           super.setRuntime(runtime);
       else System.out.println("ERROR!");
    }
}
