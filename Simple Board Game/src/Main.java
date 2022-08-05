import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        ArrayList<Calliance> calliances = new ArrayList<>();        // Arraylist to keep Calliance characters
        ArrayList<Zorde> zordes  = new ArrayList<>();               // Arraylist to keep Zorde characters

        FileWriter output = new FileWriter(args[2]);                // Creating output.txt file for writing results

        int size = findSize(args[0]);                               // Finding board size
        Warrior[][] board = new Warrior[size][size];                // Creating board with given size
        readInitials(args[0],board,calliances,zordes);              // Reading initials.txt
        showBoard(board,size,output);                               // Showing the initial status of the board
        showWarriors(calliances,zordes,output);                     // Showing the initial status of the warriors

        Command.readCommands(args[1],board,calliances,zordes,output);  // Reading commands.txt and execute the moves of the characters

        output.close();
    }

    // Method for finding board size
    public static int findSize(String fileName) throws FileNotFoundException {
        int counter= 0;
        int size = 0;
        Scanner initialFile = new Scanner(new File(fileName));
        while (initialFile.hasNextLine()) {
            String line = initialFile.nextLine();
            counter++;
            if (counter == 2) {
                size = Integer.parseInt(line.split("x")[0]);
                return size;
            }
        }return size;
    }

    // Method for reading initials.txt
    public static void readInitials(String fileName,Warrior[][] board, ArrayList<Calliance> calliances,ArrayList<Zorde> zordes) throws FileNotFoundException {
        Scanner initialFile = new Scanner(new File(fileName));
        while (initialFile.hasNextLine()){
            String line = initialFile.nextLine();
            String[] lineInfo = line.split("\\s+");

            if (lineInfo[0].equals("ELF")){
                Elf elf = new Elf(lineInfo[1]);
                board[Integer.parseInt(lineInfo[3])][Integer.parseInt(lineInfo[2])] = elf;      // Placing the Elf characters in the initial position
                calliances.add(elf);

            }else if (lineInfo[0].equals("DWARF")){
                Dwarf dwarf = new Dwarf(lineInfo[1]);
                board[Integer.parseInt(lineInfo[3])][Integer.parseInt(lineInfo[2])] = dwarf;    // Placing the Dwarf characters in the initial position
                calliances.add(dwarf);
            }else if (lineInfo[0].equals("HUMAN")){
                Human human = new Human(lineInfo[1]);
                board[Integer.parseInt(lineInfo[3])][Integer.parseInt(lineInfo[2])] = human;    // Placing the Human characters in the initial position
                calliances.add(human);
            }else if (lineInfo[0].equals("GOBLIN")){
                Goblin goblin = new Goblin(lineInfo[1]);
                board[Integer.parseInt(lineInfo[3])][Integer.parseInt(lineInfo[2])] = goblin;   // Placing the Goblin characters in the initial position
                zordes.add(goblin);
            }else if (lineInfo[0].equals("TROLL")){
                Troll troll = new Troll(lineInfo[1]);
                board[Integer.parseInt(lineInfo[3])][Integer.parseInt(lineInfo[2])] = troll;    // Placing the Troll characters in the initial position
                zordes.add(troll);
            }else if (lineInfo[0].equals("ORK")){
                Ork ork = new Ork(lineInfo[1]);
                board[Integer.parseInt(lineInfo[3])][Integer.parseInt(lineInfo[2])] = ork;      // Placing the Ork characters in the initial position
                zordes.add(ork);
            }
        }
    }

    // Method for shows the current status of the board
    public static void showBoard(Warrior[][] board, int size,FileWriter output) throws IOException {
        String str = "";
        for (int i = 0; i <(size*2)+2; i++) {
            str = str + "*";
        }
        output.write(str+"\n");
        for (Warrior[] boardRow:board){
            output.write("*");
            for (Warrior character:boardRow){
                if (character==null)
                    output.write("  ");
                else
                    output.write(character.getCharacterId());
            }
            output.write("*");
            output.write("\n");
        }
        output.write(str+"\n");
        output.write("\n");
    }
    // Method for showing characters current and default HP values
    public static void showWarriors(ArrayList<Calliance> calliances,ArrayList<Zorde> zordes,FileWriter output) throws IOException {
        ArrayList<Warrior> warriors = new ArrayList<>();
        warriors.addAll(calliances);
        warriors.addAll(zordes);
        warriors.sort(Comparator.comparing(Warrior::getCharacterId));
        for (Warrior warrior:warriors){
            output.write(warrior.getCharacterId()+"\t"+warrior.getHP()+"\t("+warrior.getMaxHP()+")\n");
        }
        output.write("\n");
    }
}
