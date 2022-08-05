import java.util.ArrayList;

public class User extends Person{
     ArrayList<String> ratedFilms = new ArrayList<String>();
     public User(int id,String name,String surname,String country){
          super(id,name,surname,country);
     }
}
