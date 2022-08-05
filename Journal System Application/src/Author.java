public class Author {
    private int id;
    private String name;
    private String university;
    private String department;
    private String email;
    private int article1;
    private int article2;
    private int article3;
    private int article4;
    private int article5;
    private int[] articleArray;

    public Author(int id, String[] data, int[] articleArray){

        this.id = id;
        this.name = data[0];
        this.university = data[1];
        this.department = data[2];
        this.email = data[3];
        this.article1 = articleArray[0];
        this.article2 = articleArray[1];
        this.article3 = articleArray[2];
        this.article4 = articleArray[3];
        this.article5 = articleArray[4];
        this.articleArray = articleArray;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getArticle1() {
        return article1;
    }

    public void setArticle1(int article1) {
        this.article1 = article1;
    }

    public int getArticle2() {
        return article2;
    }

    public void setArticle2(int article2) {
        this.article2 = article2;
    }

    public int getArticle3() {
        return article3;
    }

    public void setArticle3(int article3) {
        this.article3 = article3;
    }

    public int getArticle4() {
        return article4;
    }

    public void setArticle4(int article4) {
        this.article4 = article4;
    }

    public int getArticle5() {
        return article5;
    }

    public void setArticle5(int article5) {
        this.article5 = article5;
    }

    public int[] getArticleArray() { return articleArray; }

    public void setArticleArray(int[] articleArray) { this.articleArray = articleArray; }
}
