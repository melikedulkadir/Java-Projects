public class Documentary extends Film{
    private String releaseDate;
    public Documentary(int id,String title, String language,String directorIds,int runtime,String country,String performerIds,String releaseDate){
        super(id,title,language,directorIds,runtime,country,performerIds);
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
