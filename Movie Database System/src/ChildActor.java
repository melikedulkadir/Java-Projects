public class ChildActor extends Performer {
    private int age;
    public ChildActor(int id,String name, String surname, String country,int age){
        super(id,name,surname,country);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
