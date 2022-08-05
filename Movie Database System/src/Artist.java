public class Artist extends Person{
    public Artist(int id,String name,String surname,String country){
        super(id,name,surname,country);
}
    public String toString() {
        return getClass().getName();
    }
}
