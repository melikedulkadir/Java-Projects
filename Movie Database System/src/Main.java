import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<User> users = new ArrayList<User>();               // Arraylist to keep users
        ArrayList<Film> films = new ArrayList<Film>();               // Arraylist to keep films
        ArrayList<FeatureFilm> featureFilms = new ArrayList<FeatureFilm>(); // Arraylist to keep Feature Films
        ArrayList<TVSeries> tvSeries = new ArrayList<TVSeries>();        // Arraylist to keep TV Series
        ArrayList<Artist> directors = new ArrayList<Artist>();         // Arraylist to keep directors
        ArrayList<Artist> writers = new ArrayList<Artist>();           // Arraylist to keep writers
        ArrayList<Artist> performers = new ArrayList<Artist>();        // Arraylist to keep performers

        readPeople(args[0], users, directors, writers, performers); // Reading people.txt file
        readFilm(args[1], films, featureFilms, tvSeries);           // Reading films.txt file
        FileWriter output = new FileWriter("output.txt");   // Creating output.txt file for writing results

        CommandOperations operation = new CommandOperations();      // Creating object to use methods in CommandOpeartions class

        Scanner commandFile = new Scanner(new File(args[2]));       // Reading commands.txt file

        while (commandFile.hasNextLine()) {
            String command = commandFile.nextLine();                // Command.txt lines
            String[] commands = command.split("\t");
            if (commands[0].equals("RATE")) {                       // If the command is RATE
                try {
                    Integer.parseInt(commands[3]);
                    if (Integer.parseInt(commands[3]) > 0 && Integer.parseInt(commands[3]) < 11) {
                        output.write(command + "\n\n");
                        operation.Rate(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]), Integer.parseInt(commands[3]), users, films, output);
                        output.write("\n-----------------------------------------------------------------------------------------------------\n");

                    } else System.out.println("ERROR! RATING SCORE MUST BE BETWEEN 1 AND 10 ");

                } catch (NumberFormatException e) {
                    System.out.println("ERROR! RATING SCORE MUST BE INTEGER NUMBER");
                }
            } else if (commands[0].equals("ADD")) {                // If the command is ADD FEATUREFILM
                output.write(command + "\n\n");
                output.write(operation.Add(featureFilms, films, command, directors, performers, writers));
                output.write("\n-----------------------------------------------------------------------------------------------------\n");

            } else if (commands[0].equals("VIEWFILM")) {          // If the command is VIEWFILM
                output.write(command + "\n\n");
                operation.ViewFilm(Integer.parseInt(commands[1]), films, output, writers, directors, performers, users);
                output.write("\n-----------------------------------------------------------------------------------------------------\n");
            } else if (commands[0].equals("LIST") && commands[1].equals("USER") && commands[3].equals("RATES")) {   // If the command is LIST USER RATES
                output.write(command + "\n\n");
                operation.ListUser(Integer.parseInt(commands[2]), users, films, output);
                output.write("\n-----------------------------------------------------------------------------------------------------\n");

            } else if (commands[0].equals("EDIT")) {            // If the command is EDIT RATE
                output.write(command + "\n\n");
                operation.Edit(Integer.parseInt(commands[2]), Integer.parseInt(commands[3]), Integer.parseInt(commands[4]), users, films, output);
                output.write("\n-----------------------------------------------------------------------------------------------------\n");

            } else if (commands[0].equals("REMOVE")) {          // If the command is REMOVE RATE
                output.write(command + "\n\n");
                operation.Remove(Integer.parseInt(commands[2]), Integer.parseInt(commands[3]), users, films, output);
                output.write("\n-----------------------------------------------------------------------------------------------------\n");

            } else if (commands[0].equals("LIST") && commands[1].equals("FILM")) {  // If the command is LIST FILM SERIES
                output.write(command + "\n\n");
                operation.ListTvSeries(tvSeries, output);
                output.write("-----------------------------------------------------------------------------------------------------\n");

            } else if (commands[0].equals("LIST") && commands[1].equals("FILMS") && commands[3].equals("COUNTRY")) {    // If the command is LIST FILMS BY COUNTRY
                output.write(command + "\n\n");
                operation.ListByCountry(commands[4], films, output);
                output.write("-----------------------------------------------------------------------------------------------------\n");

            } else if (commands[0].equals("LIST") && commands[1].equals("FEATUREFILMS") && commands[2].equals("BEFORE")) {  // If the command is LIST FEATUREFILMS BEFORE YEAR
                output.write(command + "\n\n");
                operation.ListBeforeYear(Integer.parseInt(commands[3]), featureFilms, output);
                output.write("-----------------------------------------------------------------------------------------------------\n");

            } else if (commands[0].equals("LIST") && commands[1].equals("FEATUREFILMS") && commands[2].equals("AFTER")) {   // If the command is LIST FEATUREFILMS AFTER YEAR
                output.write(command + "\n\n");
                operation.ListAfterYear(Integer.parseInt(commands[3]), featureFilms, output);
                output.write("-----------------------------------------------------------------------------------------------------\n");

            } else if (commands[0].equals("LIST") && commands[1].equals("FILMS") && commands[2].equals("BY") && commands[3].equals("RATE")) {   // If the command is LIST FILMS BY RATE DEGREE
                output.write(command + "\n\n");
                operation.ListByRate(films, output);
                output.write("\n-----------------------------------------------------------------------------------------------------\n");

            } else if (commands[0].equals("LIST") && commands[1].equals("ARTISTS")) {       // If the command is LIST ARTISTS FROM COUNTRY
                output.write(command + "\n\n");
                operation.ListArtistByCountry(commands[3], directors, writers, performers, output);
                output.write("\n-----------------------------------------------------------------------------------------------------\n");

            }

        }

        output.close();

    }
    // Function for reading people.txt file and add people to their arraylists
    public static void readPeople(String fileName, ArrayList<User> users, ArrayList<Artist> directors, ArrayList<Artist> writers, ArrayList<Artist> performers) throws FileNotFoundException {
        Scanner readFile = new Scanner(new File(fileName));

        while (readFile.hasNextLine()) {
            String peopleInfo = readFile.nextLine();
            String[] peopleData = peopleInfo.split("\t");

            if (peopleData[0].replace(":", "").equals("Actor")) {                 // A people can be an actor
                performers.add(new Actor(Integer.parseInt(peopleData[1]), peopleData[2], peopleData[3], peopleData[4], Integer.parseInt(peopleData[5])));
            } else if (peopleData[0].replace(":", "").equals("ChildActor")) {     // A people can be a child actor
                performers.add(new ChildActor(Integer.parseInt(peopleData[1]), peopleData[2], peopleData[3], peopleData[4], Integer.parseInt(peopleData[5])));
            } else if (peopleData[0].replace(":", "").equals("StuntPerformer")) { // A people can be a stunt performer
                performers.add(new StuntPerformer(Integer.parseInt(peopleData[1]), peopleData[2], peopleData[3], peopleData[4], Integer.parseInt(peopleData[5]), peopleData[6]));
            } else if (peopleData[0].replace(":", "").equals("Director")) {       // A people can be a director
                directors.add(new Director(Integer.parseInt(peopleData[1]), peopleData[2], peopleData[3], peopleData[4], peopleData[5]));
            } else if (peopleData[0].replace(":", "").equals("Writer")) {         // A people can be a writer
                writers.add(new Writer(Integer.parseInt(peopleData[1]), peopleData[2], peopleData[3], peopleData[4], peopleData[5]));
            } else if (peopleData[0].replace(":", "").equals("User")) {           // A people can be a user
                users.add(new User(Integer.parseInt(peopleData[1]), peopleData[2], peopleData[3], peopleData[4]));

            }
        }
    }
    // Function for reading films.txt file and add films to arraylists
    public static void readFilm(String fileName, ArrayList<Film> films, ArrayList<FeatureFilm> featureFilms, ArrayList<TVSeries> tvSeries) throws FileNotFoundException {
        Scanner readFile = new Scanner(new File(fileName));

        while (readFile.hasNextLine()) {
            String filmInfo = readFile.nextLine();
            String[] filmData = filmInfo.split("\t");
            if (filmData[0].replace(":", "").equals("FeatureFilm")) {           // A film can be a feature film
                featureFilms.add(new FeatureFilm(Integer.parseInt(filmData[1]), filmData[2], filmData[3], filmData[4],
                        Integer.parseInt(filmData[5]), filmData[6], filmData[7], filmData[8], filmData[9], filmData[10], Integer.parseInt(filmData[11])));
                films.add(new FeatureFilm(Integer.parseInt(filmData[1]), filmData[2], filmData[3], filmData[4],
                        Integer.parseInt(filmData[5]), filmData[6], filmData[7], filmData[8], filmData[9], filmData[10], Integer.parseInt(filmData[11])));
            } else if (filmData[0].replace(":", "").equals("ShortFilm")) {      // A film can be a short film
                if (Integer.parseInt(filmData[5])<=40){
                films.add(new ShortFilm(Integer.parseInt(filmData[1]), filmData[2], filmData[3], filmData[4],
                        Integer.parseInt(filmData[5]), filmData[6], filmData[7], filmData[8], filmData[9], filmData[10]));}
                else System.out.println("ERROR!");
            } else if (filmData[0].replace(":", "").equals("Documentary")) {    // A film can be a documentary
                films.add(new Documentary(Integer.parseInt(filmData[1]), filmData[2], filmData[3], filmData[4],
                        Integer.parseInt(filmData[5]), filmData[6], filmData[7], filmData[8]));
            } else if (filmData[0].replace(":", "").equals("TVSeries")) {       // A film can be a TV series
                tvSeries.add(new TVSeries(Integer.parseInt(filmData[1]), filmData[2], filmData[3], filmData[4],
                        Integer.parseInt(filmData[5]), filmData[6], filmData[7], filmData[8], filmData[9], filmData[10], filmData[11], Integer.parseInt(filmData[12]), Integer.parseInt(filmData[13])));
                films.add(new TVSeries(Integer.parseInt(filmData[1]), filmData[2], filmData[3], filmData[4],
                        Integer.parseInt(filmData[5]), filmData[6], filmData[7], filmData[8], filmData[9], filmData[10], filmData[11], Integer.parseInt(filmData[12]), Integer.parseInt(filmData[13])));
            }
        }

    }
}