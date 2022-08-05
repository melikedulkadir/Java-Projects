import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<Stack> parts = new ArrayList<>();                     // Arraylist for keeping parts
        Queue tokenBox = new Queue();                                   // Creating new Queue for tokenBox
        Operations.readPart(args[0],parts);                             // Reading parts.txt
        Operations.readItem(args[1],parts);                             // Reading items.txt
        Operations.readToken(args[2],tokenBox);                         // Reading tokens.txt
        Operations.readTask(args[3],parts,tokenBox);                    // Reading tasks.txt
        FileWriter output = new FileWriter(args[4]);                    // Creating output file
        Operations.writer(output,parts,tokenBox);                       // Writing operations
        output.close();
    }
}
