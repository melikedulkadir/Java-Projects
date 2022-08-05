import java.util.ArrayList;

public class TVSeries extends Film{
    private String genre;
    private String startDate;
    private String endDate;
    private int seasons;
    private int episodes;

    ArrayList<Integer> writers = new ArrayList<Integer>();

    public TVSeries(int id,String title, String language,String directorIds,int runtime,String country,String performerIds,String genre, String writerIds,String startDate, String endDate, int seasons,int episodes){
        super(id,title,language,directorIds,runtime,country,performerIds);
        this.genre = genre;
        this.startDate = startDate;
        this.endDate = endDate;
        this.seasons = seasons;
        this.episodes = episodes;
        for (int i = 0; i < (writerIds.split(",").length); i++) {
            writers.add(Integer.parseInt(writerIds.split(",")[i]));
        }
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


}
