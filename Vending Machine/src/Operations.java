import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Operations {
    public static void readPart(String fileName, ArrayList<Stack> parts) throws FileNotFoundException {
        Scanner readFile = new Scanner(new File(fileName));
        while (readFile.hasNextLine()) {
            String partName = readFile.nextLine();
            parts.add(new Stack(partName));                                 // Adding new Stacks in parts list
        }
    }

    public static void readItem(String fileName,ArrayList<Stack> parts) throws FileNotFoundException {
        Scanner readFile = new Scanner(new File(fileName));
        while (readFile.hasNextLine()) {
            String item = readFile.nextLine();
            String[] itemData = item.split("\\s+");
            for(Stack part:parts){
                if(part.getPartName().equals(itemData[1])){
                    part.push(new Item(itemData[0]));                       // Pushing new Items with push method of Stack
                }
            }
        }
    }

    public static void readToken(String fileName,Queue tokenBox) throws FileNotFoundException {
        Scanner readFile = new Scanner(new File(fileName));
        while (readFile.hasNextLine()) {
            String token = readFile.nextLine();
            String[] tokenData = token.split("\\s+");
            tokenBox.enqueue(new Token(tokenData[0],Integer.parseInt(tokenData[2]),tokenData[1]));      // Enqueue new Tokens with enqueue method of Queue
        }
    }

    public static void readTask(String fileName,ArrayList<Stack> parts,Queue tokenBox) throws FileNotFoundException {
        Scanner readFile = new Scanner(new File(fileName));
        while (readFile.hasNextLine()) {
            String task = readFile.nextLine();
            String[] taskData = task.split("\t");
            if (taskData[0].equals("BUY")){                             // Check block for BUY task
                for (int i = 1; i < taskData.length ; i++) {
                    for (Stack part:parts){
                        if (part.getPartName().equals(taskData[i].split(",")[0])){
                            part.pop(Integer.parseInt(taskData[i].split(",")[1])+1);                    // Pop method in order to get item from related stack
                            tokenBox.dequeue(part.getPartName(),Integer.parseInt(taskData[i].split(",")[1]));   // Dequeue method for using token to buy item
                        }
                    }
                }
            }else if (taskData[0].equals("PUT")){                       // Check block for PUT task
                for (int i = 1; i < taskData.length ; i++) {
                    for (Stack part:parts){
                        if (part.getPartName().equals(taskData[i].split(",")[0])){
                            for (int j = 1; j < taskData[i].split(",").length ; j++) {
                                part.push(new Item(taskData[i].split(",")[j]));             // Push method for putting new items in related stack
                            }
                        }
                    }
                }
            }
        }
    }

    // Writer method for printing the status of each part of the vending machine
    public static void writer(FileWriter output,ArrayList<Stack> parts,Queue tokenBox) throws IOException {
        for(Stack part:parts) {
            output.write(part.getPartName() + ":\n");
            if (part.partItems.size() == 0) output.write("\n---------------\n");
            else {
                for (int i = part.partItems.size() - 1; i > -1; i--) {
                    output.write(part.partItems.get(i).getItemId() + "\n");
                }
                output.write("---------------\n");
            }
        }
        output.write("Token Box:\n");
        if (tokenBox.tokens.size() == 0) output.write("---------------\n");
        for (int i = tokenBox.tokens.size()-1; i > -1 ; i--) {
            output.write(tokenBox.tokens.get(i).getTokenId()+" "+tokenBox.tokens.get(i).getTokenPart()+" "+tokenBox.tokens.get(i).getTokenValue()+"\n");
        }
    }
}
