public class Actor extends Performer{
    private int height;
    public Actor(int id,String name,String surname,String country,int height){
        super(id,name,surname,country);
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
