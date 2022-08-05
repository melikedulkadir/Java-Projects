import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class CommandOperations {
    // This function provides a user to rate a film
    public void Rate(int userId, int filmId, int ratingPoint, ArrayList<User> users, ArrayList<Film> films, FileWriter output) throws IOException {
        boolean flag = false;
        for (User user : users) {
            if (user.getId() == userId) {
                for (int i = 0; i < user.ratedFilms.size(); i++) {
                    if ((user.ratedFilms.get(i)).split(":")[0].equals(Integer.toString(filmId))) {
                        output.write("This film was earlier rated\n");      // If the specified film was already rated by the given user
                        return;

                    }
                }
                for (Film film : films) {
                    if (film.getFilmId() == filmId) {
                        user.ratedFilms.add((filmId) + ":" + (ratingPoint));
                        film.setRatingScore(ratingPoint);
                        film.setRaterCount(1);
                        output.write("Film rated successfully\n");
                        output.write("Film type: " + film.toString() + "\n");
                        output.write("Film title: " + film.getFilmTitle() + "\n");
                        flag = true;
                        break;
                    }
                }
            }
        }
        // If there is not any user or film with specified ID
        if (!flag) output.write("Command Failed\n" + "User ID: " + userId + "\n" + "Film ID: " + filmId + "\n");
    }
    // This function checks whether artist does exist or not
    public boolean loopMethod(String info, int num, ArrayList<Artist> artists, boolean flag) {
        for (String artistId : (info.split("\t")[num]).split(",")) {
            for (Artist artist : artists) {
                if (artist.getId() == Integer.parseInt(artistId)) {
                    flag = true;
                    break;
                } else flag = false;
            }
        }
        return flag;
    }
    // This function provides adding a feature film
    public String Add(ArrayList<FeatureFilm> featureFilms, ArrayList<Film> films, String info, ArrayList<Artist> directors, ArrayList<Artist> performers, ArrayList<Artist> writers) throws IOException {
        // Check feature film exists or not
        for (FeatureFilm featureFilm : featureFilms) {
            if (featureFilm.getFilmId() == Integer.parseInt(info.split("\t")[2])) {
                return "Command Failed\n" + "Film ID: " + info.split("\t")[2] + "\nFilm title: " + info.split("\t")[3] + "\n";
            }
        }

        if (!loopMethod(info, 5, directors, true))   // Check directors exist or not
            return "Command Failed\n" + "Film ID: " + info.split("\t")[2] + "\nFilm title: " + info.split("\t")[3] + "\n";
        if (!loopMethod(info, 8, performers, true))  // Check performers exist or not
            return "Command Failed\n" + "Film ID: " + info.split("\t")[2] + "\nFilm title: " + info.split("\t")[3] + "\n";
        if (!loopMethod(info, 11, writers, true))   // Check writers exist or not
            return "Command Failed\n" + "Film ID: " + info.split("\t")[2] + "\nFilm title: " + info.split("\t")[3] + "\n";

        String[] filmData = info.split("\t");

        featureFilms.add(new FeatureFilm(Integer.parseInt(filmData[2]), filmData[3], filmData[4], filmData[5],
                Integer.parseInt(filmData[6]), filmData[7], filmData[8], filmData[9], filmData[10], filmData[11], Integer.parseInt(filmData[12])));
        films.add(new FeatureFilm(Integer.parseInt(filmData[2]), filmData[3], filmData[4], filmData[5],
                Integer.parseInt(filmData[6]), filmData[7], filmData[8], filmData[9], filmData[10], filmData[11], Integer.parseInt(filmData[12])));
        return "FeatureFilm added successfully\n" + "Film ID: " + info.split("\t")[2] + "\nFilm title: " + info.split("\t")[3] + "\n";
    }
    // This function provides details of a film
    public void ViewFilm(int filmId, ArrayList<Film> films, FileWriter output, ArrayList<Artist> writers, ArrayList<Artist> directors, ArrayList<Artist> performers, ArrayList<User> users) throws IOException {
        boolean isFilmExist = films.stream().anyMatch(o -> o.getFilmId() == (filmId));

        if (isFilmExist) {
            for (Film film : films) {
                if (film.getFilmId() == filmId) {
                    if (film.toString().equals("FeatureFilm") || film.toString().equals("ShortFilm")) {
                        if (film.toString().equals("FeatureFilm")) {
                            FeatureFilm featureFilm = (FeatureFilm) film;
                            output.write(film.getFilmTitle() + " (" + featureFilm.getReleaseDate().split("\\.")[2] + ")" + "\n");
                            String genre = featureFilm.getGenre().replace(",", ", ");
                            output.write(genre + "\n");
                            String writerNames = "";
                            for (int writerId : featureFilm.writers) {
                                for (Artist writer : writers) {
                                    if (writerId == writer.getId())
                                        writerNames = writerNames.concat(writer.getName() + " " + writer.getSurname() + ", ");
                                }
                            }
                            writerNames = writerNames.substring(0, writerNames.length() - 2);
                            output.write("Writers: " + writerNames + "\n");

                        }
                        if (film.toString().equals("ShortFilm")) {
                            ShortFilm shortFilm = (ShortFilm) film;
                            output.write(film.getFilmTitle() + " (" + shortFilm.getReleaseDate().split("\\.")[2] + ")" + "\n");
                            String genre = shortFilm.getGenre().replace(",", ", ");
                            output.write(genre + "\n");
                            String writerNames = "";
                            for (int writerId : shortFilm.writers) {
                                for (Artist writer : writers) {
                                    if (writerId == writer.getId())
                                        writerNames = writerNames.concat(writer.getName() + " " + writer.getSurname() + ", ");
                                }
                            }
                            writerNames = writerNames.substring(0, writerNames.length() - 2);
                            output.write("Writers: " + writerNames + "\n");
                        }
                        String directorNames = "";
                        for (int directorId : film.directors) {
                            for (Artist director : directors) {
                                if (directorId == director.getId())
                                    directorNames = directorNames.concat(director.getName() + " " + director.getSurname() + ", ");
                            }
                        }
                        directorNames = directorNames.substring(0, directorNames.length() - 2);
                        output.write("Directors: " + directorNames + "\n");

                        String starNames = "";
                        for (int starId : film.cast) {
                            for (Artist performer : performers) {
                                if (starId == performer.getId())
                                    starNames = starNames.concat(performer.getName() + " " + performer.getSurname() + ", ");
                            }
                        }
                        starNames = starNames.substring(0, starNames.length() - 2);
                        output.write("Stars: " + starNames + "\n");

                        if (film.getRaterCount() == 0) output.write("Awaiting for votes\n");    // If there is not any rating votes for that film
                        else {
                            if ((film.getRatingScore()) % 1 != 0) {
                                double roundedRatingScore = Math.round(film.getRatingScore() * 10) / 10.0;
                                String avgRating = Double.toString(roundedRatingScore);
                                output.write("Ratings: " + avgRating.replace('.', ',') + "/10 from " + film.getRaterCount() + " users\n");
                            } else
                                output.write("Ratings: " + (int) (film.getRatingScore()) + "/10 from " + film.getRaterCount() + " users\n");
                        }
                    } else if (film.toString().equals("Documentary")) {
                        Documentary documentaryFilm = (Documentary) film;
                        output.write(film.getFilmTitle() + " (" + documentaryFilm.getReleaseDate().split("\\.")[2] + ")" + "\n");

                        String directorNames = "";
                        for (int directorId : film.directors) {
                            for (Artist director : directors) {
                                if (directorId == director.getId())
                                    directorNames = directorNames.concat(director.getName() + " " + director.getSurname() + ", ");
                            }
                        }
                        directorNames = directorNames.substring(0, directorNames.length() - 2);
                        output.write("\nDirectors: " + directorNames + "\n");

                        String starNames = "";
                        for (int starId : film.cast) {
                            for (Artist performer : performers) {
                                if (starId == performer.getId())
                                    starNames = starNames.concat(performer.getName() + " " + performer.getSurname() + ", ");
                            }
                        }
                        starNames = starNames.substring(0, starNames.length() - 2);
                        output.write("Stars: " + starNames + "\n");

                        if (film.getRaterCount() == 0) output.write("Awaiting for votes\n"); // If there is not any rating votes for that film
                        else {
                            if ((film.getRatingScore()) % 1 != 0) {
                                double roundedRatingScore = Math.round(film.getRatingScore() * 10) / 10.0;
                                String avgRating = Double.toString(roundedRatingScore);
                                output.write("Ratings: " + avgRating.replace('.', ',') + "/10 from " + film.getRaterCount() + " users\n");
                            } else
                                output.write("Ratings: " + (int) (film.getRatingScore()) + "/10 from " + film.getRaterCount() + " users\n");
                        }
                    } else if (film.toString().equals("TVSeries")) {
                        TVSeries tvSeries = (TVSeries) film;
                        output.write(film.getFilmTitle() + " (" + tvSeries.getStartDate().split("\\.")[2] + "-" + tvSeries.getEndDate().split("\\.")[2] + ")" + "\n");
                        output.write(tvSeries.getSeasons() + " seasons, " + tvSeries.getEpisodes() + " episodes" + "\n");
                        String genre = tvSeries.getGenre().replace(",", ", ");
                        output.write(genre + "\n");

                        String writerNames = "";
                        for (int writerId : tvSeries.writers) {
                            for (Artist writer : writers) {
                                if (writerId == writer.getId())
                                    writerNames = writerNames.concat(writer.getName() + " " + writer.getSurname() + ", ");
                            }
                        }
                        writerNames = writerNames.substring(0, writerNames.length() - 2);
                        output.write("Writers: " + writerNames + "\n");

                        String directorNames = "";
                        for (int directorId : film.directors) {
                            for (Artist director : directors) {
                                if (directorId == director.getId())
                                    directorNames = directorNames.concat(director.getName() + " " + director.getSurname() + ", ");
                            }
                        }
                        directorNames = directorNames.substring(0, directorNames.length() - 2);
                        output.write("Directors: " + directorNames + "\n");

                        String starNames = "";
                        for (int starId : film.cast) {
                            for (Artist performer : performers) {
                                if (starId == performer.getId())
                                    starNames = starNames.concat(performer.getName() + " " + performer.getSurname() + ", ");
                            }
                        }
                        starNames = starNames.substring(0, starNames.length() - 2);
                        output.write("Stars: " + starNames + "\n");

                        if (film.getRaterCount() == 0) output.write("Awaiting for votes\n");    // If there is not any rating votes for that film
                        else {
                            if ((film.getRatingScore()) % 1 != 0) {
                                double roundedRatingScore = Math.round(film.getRatingScore() * 10) / 10.0;
                                String avgRating = Double.toString(roundedRatingScore);
                                output.write("Ratings: " + avgRating.replace('.', ',') + "/10 from " + film.getRaterCount() + " users\n");
                            } else
                                output.write("Ratings: " + (int) (film.getRatingScore()) + "/10 from " + film.getRaterCount() + " users\n");
                        }

                    }

                }
            }
        } else output.write("Command Failed\n" + "Film ID: " + filmId + "\n");
    }
    // This function provides a user to list all films which he/she rated so far
    public void ListUser(int userId, ArrayList<User> users, ArrayList<Film> films, FileWriter output) throws IOException {
        boolean UserExist = users.stream().anyMatch(o -> o.getId() == (userId));
        if (UserExist) {
            for (User user : users) {
                if (user.getId() == userId) {
                    if (user.ratedFilms.size() > 0) {
                        for (int i = 0; i < user.ratedFilms.size(); i++) {
                            for (Film film : films) {
                                if (film.getFilmId() == Integer.parseInt(user.ratedFilms.get(i).split(":")[0]))
                                    output.write(film.getFilmTitle() + ": " + user.ratedFilms.get(i).split(":")[1] + "\n");
                            }
                        }
                    } else output.write("There is not any ratings so far\n");
                }
            }
        } else output.write("Command Failed\n" + "User ID: " + userId + "\n");  // If there is not any user with specified id
    }
    // This function provides a user to edit a film which he/she rated before
    public void Edit(int userId, int filmId, int newRatingPoint, ArrayList<User> users, ArrayList<Film> films, FileWriter output) throws IOException {
        boolean userExist = users.stream().anyMatch(o -> o.getId() == (userId));
        boolean filmExist = films.stream().anyMatch(o -> o.getFilmId() == (filmId));
        boolean flag = false;
        if (userExist && filmExist) {
            for (User user : users) {
                if (user.getId() == userId) {
                    for (int i = 0; i < user.ratedFilms.size(); i++) {
                        if (filmId == Integer.parseInt(user.ratedFilms.get(i).split(":")[0])) {
                            flag = true;
                            for (Film film : films) {
                                if (film.getFilmId() == filmId) {
                                    film.setRatingScore(-Integer.parseInt(user.ratedFilms.get(i).split(":")[1]));
                                    film.setRatingScore(+newRatingPoint);
                                    user.ratedFilms.set(i, user.ratedFilms.get(i).split(":")[0] + ":" + newRatingPoint);
                                    output.write("New ratings done successfully\n");
                                    output.write("Film title: " + film.getFilmTitle() + "\n");
                                    output.write("Your rating: " + newRatingPoint + "\n");
                                    break;

                                }
                            }
                        }

                    }
                }
            }
        }
        if (!userExist || !filmExist || !flag) {    // If there is not any user, film or the user has no rating score for the specified film
            output.write("Command Failed\n" + "User ID: " + userId + "\n" + "Film ID: " + filmId + "\n");
        }

    }
    // This function provides a user to remove one of his/her rated films
    public void Remove(int userId, int filmId, ArrayList<User> users, ArrayList<Film> films, FileWriter output) throws IOException {
        boolean userExist = users.stream().anyMatch(o -> o.getId() == (userId));
        boolean filmExist = films.stream().anyMatch(o -> o.getFilmId() == (filmId));
        boolean flag = false;
        if (userExist && filmExist) {
            for (User user : users) {
                if (user.getId() == userId) {
                    for (int i = 0; i < user.ratedFilms.size(); i++) {
                        if (filmId == Integer.parseInt(user.ratedFilms.get(i).split(":")[0])) {
                            flag = true;
                            for (Film film : films) {
                                if (film.getFilmId() == filmId) {
                                    film.setRatingScore(-(Integer.parseInt(user.ratedFilms.get(i).split(":")[1])));
                                    film.setRaterCount(-1);
                                    user.ratedFilms.remove(i);
                                    output.write("Your film rating was removed successfully\n");
                                    output.write("Film title: " + film.getFilmTitle() + "\n");
                                    break;

                                }
                            }
                        }

                    }
                }
            }
        }
        if (!userExist || !filmExist || !flag) {    // If there is not any user, film or the user has no rating score for the specified film
            output.write("Command Failed\n" + "User ID: " + userId + "\n" + "Film ID: " + filmId + "\n");
        }

    }
    // This function list all the TV Series in the system
    public void ListTvSeries(ArrayList<TVSeries> tvSeries, FileWriter output) throws IOException {
        if (tvSeries.size() != 0) {
            for (TVSeries series : tvSeries) {
                output.write(series.getFilmTitle() + " (" + series.getStartDate().split("\\.")[2] + "-" + series.getEndDate().split("\\.")[2] + ")\n");
                output.write(series.getSeasons() + " seasons and " + series.getEpisodes() + " episodes\n\n");
            }
        } else output.write("No result\n");
    }
    // This function list all the films from a specified country
    public void ListByCountry(String country, ArrayList<Film> films, FileWriter output) throws IOException {
        int counter = 0;
        for (Film film : films) {
            if (film.getCountry().equals(country)) {
                counter++;
                output.write("Film title: " + film.getFilmTitle() + "\n" + film.getRuntime() + " min\n" + "Language: " + film.getLanguage() + "\n\n");
            }
        }
        if (counter == 0) output.write("No result\n\n");

    }
    // This function list all the films released before a specified year
    public void ListBeforeYear(int year, ArrayList<FeatureFilm> featureFilms, FileWriter output) throws IOException {
        int counter = 0;
        for (FeatureFilm film : featureFilms) {
            if (Integer.parseInt(film.getReleaseDate().split("\\.")[2]) < year) {
                counter++;
                output.write("Film title: " + film.getFilmTitle() + " (" + film.getReleaseDate().split("\\.")[2] + ")\n" + film.getRuntime() + " min\n" + "Language: " + film.getLanguage() + "\n\n");
            }
        }
        if (counter == 0) output.write("No result\n\n");
    }
    // This function list all the films released after a specified year
    public void ListAfterYear(int year, ArrayList<FeatureFilm> featureFilms, FileWriter output) throws IOException {
        int counter = 0;
        for (FeatureFilm film : featureFilms) {
            if (Integer.parseInt(film.getReleaseDate().split("\\.")[2]) >= year) {
                counter++;
                output.write("Film title: " + film.getFilmTitle() + " (" + film.getReleaseDate().split("\\.")[2] + ")\n" + film.getRuntime() + " min\n" + "Language: " + film.getLanguage() + "\n\n");
            }
        }
        if (counter == 0) output.write("No result\n\n");
    }
    // This function provides list all the films in descending order and categorized according to film rating degrees.
    public void ListByRate(ArrayList<Film> films, FileWriter output) throws IOException {

        films.sort(Comparator.comparing(Film::getRatingScore).reversed()); // Sorting films according to film rating degrees in descending order

        output.write("FeatureFilm:\n");
        int counter = 0;
        for (Film film : films) {
            if (film.toString().equals("FeatureFilm")) {
                counter++;
                if ((film.getRatingScore()) % 1 != 0) {
                    double roundedRatingScore = Math.round(film.getRatingScore() * 10) / 10.0;
                    String avgRating = Double.toString(roundedRatingScore);
                    output.write(film.getFilmTitle() + " (" + ((FeatureFilm) film).getReleaseDate().split("\\.")[2] + ") Ratings: " + avgRating.replace('.', ',') + "/10 from " + film.getRaterCount() + " users\n");
                } else {
                    output.write(film.getFilmTitle() + " (" + ((FeatureFilm) film).getReleaseDate().split("\\.")[2] + ") Ratings: " + (int) (film.getRatingScore()) + "/10 from " + film.getRaterCount() + " users\n");
                }
            }
        }
        if (counter == 0) output.write("No result\n");  // If there is not any result for a feature film category

        output.write("\nShortFilm:\n");
        counter = 0;
        for (Film film : films) {
            if (film.toString().equals("ShortFilm")) {
                counter++;
                if ((film.getRatingScore()) % 1 != 0) {
                    double roundedRatingScore = Math.round(film.getRatingScore() * 10) / 10.0;
                    String avgRating = Double.toString(roundedRatingScore);
                    output.write(film.getFilmTitle() + " (" + ((ShortFilm) film).getReleaseDate().split("\\.")[2] + ") Ratings: " + avgRating.replace('.', ',') + "/10 from " + film.getRaterCount() + " users\n");
                } else {
                    output.write(film.getFilmTitle() + " (" + ((ShortFilm) film).getReleaseDate().split("\\.")[2] + ") Ratings: " + (int) (film.getRatingScore()) + "/10 from " + film.getRaterCount() + " users\n");
                }
            }
        }
        if (counter == 0) output.write("No result\n");  // If there is not any result for a short film category

        output.write("\nDocumentary:\n");
        counter = 0;
        for (Film film : films) {
            if (film.toString().equals("Documentary")) {
                counter++;
                if ((film.getRatingScore()) % 1 != 0) {
                    double roundedRatingScore = Math.round(film.getRatingScore() * 10) / 10.0;
                    String avgRating = Double.toString(roundedRatingScore);
                    output.write(film.getFilmTitle() + " (" + ((Documentary) film).getReleaseDate().split("\\.")[2] + ") Ratings: " + avgRating.replace('.', ',') + "/10 from " + film.getRaterCount() + " users\n");
                } else {
                    output.write(film.getFilmTitle() + " (" + ((Documentary) film).getReleaseDate().split("\\.")[2] + ") Ratings: " + (int) (film.getRatingScore()) + "/10 from " + film.getRaterCount() + " users\n");
                }
            }
        }
        if (counter == 0) output.write("No result\n");  // If there is not any result for a documentary category

        output.write("\nTVSeries:\n");
        counter = 0;
        for (Film film : films) {
            if (film.toString().equals("TVSeries")) {
                counter++;
                if ((film.getRatingScore()) % 1 != 0) {
                    double roundedRatingScore = Math.round(film.getRatingScore() * 10) / 10.0;
                    String avgRating = Double.toString(roundedRatingScore);
                    output.write(film.getFilmTitle() + " (" + ((TVSeries) film).getStartDate().split("\\.")[2] + "-" + ((TVSeries) film).getEndDate().split("\\.")[2] + ") Ratings: " + avgRating.replace('.', ',') + "/10 from " + film.getRaterCount() + " users\n");
                } else {
                    output.write(film.getFilmTitle() + " (" + ((TVSeries) film).getStartDate().split("\\.")[2] + "-" + ((TVSeries) film).getEndDate().split("\\.")[2] + ") Ratings: " + (int) (film.getRatingScore()) + "/10 from " + film.getRaterCount() + " users\n");
                }
            }
        }
        if (counter == 0) output.write("No result\n");      // If there is not any result for a TV series category
    }
    // This function provides list all the artists from a specified country
    public void ListArtistByCountry(String country, ArrayList<Artist> directors, ArrayList<Artist> writers, ArrayList<Artist> performers, FileWriter output) throws IOException {
        output.write("Directors:\n");
        int counter = 0;
        for (Artist director : directors) {
            if (director.getCountry().equals(country)) {
                counter++;
                output.write(director.getName() + " " + director.getSurname() + " " + ((Director) director).getAgent() + "\n");
            }
        }
        if (counter == 0) output.write("No result\n");      // If there is not any result for directors category

        output.write("\nWriters:\n");
        counter = 0;
        for (Artist writer : writers) {
            if (writer.getCountry().equals(country)) {
                counter++;
                output.write(writer.getName() + " " + writer.getSurname() + " " + ((Writer) writer).getWritingType() + "\n");
            }
        }
        if (counter == 0) output.write("No result\n");      // If there is not any result for writers category

        output.write("\nActors:\n");
        counter = 0;
        for (Artist performer : performers) {
            if (performer.toString().equals("Actor")) {
                if (performer.getCountry().equals(country)) {
                    counter++;
                    output.write(performer.getName() + " " + performer.getSurname() + " " + ((Actor) performer).getHeight() + " cm\n");
                }
            }
        }
        if (counter == 0) output.write("No result\n");      // If there is not any result for actors category
        output.write("\nChildActors:\n");
        counter = 0;
        for (Artist performer : performers) {
            if (performer.toString().equals("ChildActor")) {
                if (performer.getCountry().equals(country)) {
                    counter++;
                    output.write(performer.getName() + " " + performer.getSurname() + " " + ((ChildActor) performer).getAge() + "\n");
                }
            }
        }
        if (counter == 0) output.write("No result\n");      // If there is not any result for child actors category
        output.write("\nStuntPerformers:\n");
        counter = 0;
        for (Artist performer : performers) {
            if (performer.toString().equals("StuntPerformer")) {
                if (performer.getCountry().equals(country)) {
                    counter++;
                    output.write(performer.getName() + " " + performer.getSurname() + " " + ((StuntPerformer) performer).getHeight() + " cm\n");
                }
            }
        }
        if (counter == 0) output.write("No result\n");      // If there is not any result for stunt performer category
    }
}






