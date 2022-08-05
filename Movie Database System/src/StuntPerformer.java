import java.util.ArrayList;

public class StuntPerformer extends Performer{
    private int height;
    ArrayList<Integer> realActors = new ArrayList<Integer>();

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public StuntPerformer(int id, String name, String surname, String country, int height,String realActorId){
        super(id,name,surname,country);
        this.height = height;
        for (int i = 0; i < (realActorId.split(",").length); i++) {
             realActors.add(Integer.parseInt(realActorId.split(",")[i]));
        }
    }
}
