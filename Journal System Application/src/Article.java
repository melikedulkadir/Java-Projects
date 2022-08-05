public class Article implements Comparable<Article>{

    private int paperid;
    private String name;
    private String publisherName;
    private int publishYear;

    public Article(int paperid){
        this.paperid = paperid;
    }

    public int getPaperid() {
        return paperid;
    }

    public void setPaperid(int paperid) {
        this.paperid = paperid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public int compareTo(Article compareArticle) {
        return Integer.compare(this.getPaperid(), compareArticle.getPaperid());
    }
}
