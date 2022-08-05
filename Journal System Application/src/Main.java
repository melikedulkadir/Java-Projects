// Melike Nur DULKADİR
// 21992919

import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<Author> authors = new ArrayList<Author>();                // Arraylist for saving authors
        ArrayList<Article> articles = new ArrayList<Article>();              // Arraylist for saving articles

        FileWriter output = new FileWriter("output.txt");
        fileRead(args[0], authors, articles);                          // Reading author.txt
        Scanner commandFile = new Scanner(new File(args[1]));          // Reading command.txt

        JournalOperations operations = new JournalOperations();

        boolean flag = false;
        while (commandFile.hasNextLine()) {
            String command = commandFile.nextLine();          // Command.txt lines
            String[] commands = command.split("\\s+");
            if (commands[0].equals("read")) {
                operations.readArticle(commands[1], articles);
            } else if (commands[0].equals("list")) {
                output.write("\n");
                output.write("--------------------------------List--------------------------------\n");
                operations.listMethod(output, authors, articles, flag);
                output.write("--------------------------------End--------------------------------\n");

            } else if (commands[0].equals("completeAll")) {
                output.write("\n");
                operations.completeAll(authors, articles);
                output.write("***********************CompleteAll Successful***********************\n");

            } else if (commands[0].equals("sortedAll")) {
                output.write("\n");
                output.write("************************SortedAll Successful************************\n");
                flag = true;
            } else if (commands[0].equals("del")) {
                output.write("\n");
                operations.del(authors, Integer.parseInt(commands[1]));
                output.write("***************************del Successful***************************\n");
            }

        }
        output.close();
    }

    public static void fileRead(String fileName, ArrayList<Author> authors, ArrayList<Article> articles) throws FileNotFoundException {
        Scanner readFile = new Scanner(new File(fileName));

        while (readFile.hasNextLine()) {

            String authorInfo = readFile.nextLine();                        // Reading author.txt line by line
            String[] authorsData = authorInfo.split("\\s+");
            String[] dataArray = {"", "", "", ""};                          // A string array for author data
            int[] articleArray = new int[5];                                // An integer array for author's articles

            // Checking length of author.txt line
            if (authorsData.length == 2) {         // If data length is 2, create author object with only id.
                authors.add(new Author(Integer.parseInt(authorsData[1]), dataArray, articleArray));

            } else if (authorsData.length == 6) {  // If data length is 6, create author object without article data.
                for (int i = 2; i < authorsData.length; i++) {
                    dataArray[i - 2] = authorsData[i];
                }
                authors.add(new Author(Integer.parseInt(authorsData[1]), dataArray, articleArray));

            } else if (authorsData.length > 6) {   // If data length is more than 6, create author object with data.
                for (int i = 2; i < 6; i++) {      // Filling data array to set author info.
                    dataArray[i - 2] = authorsData[i];
                }
                for (int i = 0; i < (authorsData.length - 6); i++) {
                    articleArray[i] = Integer.parseInt(authorsData[i + 6]); // Filling article array to set author's article info'.


                    /* Checking global article array in order to add new articles.
                     If they are not inside of it, add new article objects. */
                    boolean flag = false;
                    for (Article article : articles) {
                        if (article.getPaperid() == Integer.parseInt(authorsData[i + 6])) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        articles.add(new Article(Integer.parseInt(authorsData[i + 6])));
                    }
                }
                //Creating author object and adding it to global author arraylist.
                authors.add(new Author(Integer.parseInt(authorsData[1]), dataArray, articleArray));
            }
        }
    }
}
