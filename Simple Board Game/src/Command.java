import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Command {
    // Method for reading commands.txt and execute the moves of the characters
    public static void readCommands(String fileName, Warrior[][] board, ArrayList<Calliance> calliances, ArrayList<Zorde> zordes, FileWriter output) throws IOException {
        Scanner initialFile = new Scanner(new File(fileName));
        while (initialFile.hasNextLine()) {
            String line = initialFile.nextLine();
            String[] lineInfo = line.split("\\s+");
            String[] steps = lineInfo[1].split(";");
            if (lineInfo[0].charAt(0) == 'H') {                 // If the character is a human
                try {
                    if (steps.length == 6) {
                        for (Calliance human : calliances) {
                            if (human.getCharacterId().equals(lineInfo[0])) {
                                if (human.move(human.getCharacterId(), Integer.parseInt(steps[1]), Integer.parseInt(steps[0]), Constants.humanAP, board, calliances, zordes, false, true, false, output)) {
                                    if (human.move(human.getCharacterId(), Integer.parseInt(steps[3]), Integer.parseInt(steps[2]), Constants.humanAP, board, calliances, zordes, false, false, false, output)) {
                                        if (human.move(human.getCharacterId(), Integer.parseInt(steps[5]), Integer.parseInt(steps[4]), Constants.humanAP, board, calliances, zordes, true, false, false, output)) {
                                            Main.showBoard(board, board.length, output);
                                            Main.showWarriors(calliances, zordes, output);
                                        } else break;
                                    } else break;
                                } else break;
                            }
                        }
                    } else throw new MoveCountException();     // Exception throws if move count given incorrect

                } catch (MoveCountException moveCount) {
                    output.write("Error : Move sequence contains wrong number of move steps. Input line ignored.\n\n");
                }
            } else if (lineInfo[0].charAt(0) == 'E') {          // If the character is an elf
                try {
                    if (steps.length == 8) {
                        for (Calliance elf : calliances) {
                            if (elf.getCharacterId().equals(lineInfo[0])) {
                                if (elf.move(elf.getCharacterId(), Integer.parseInt(steps[1]), Integer.parseInt(steps[0]), Constants.elfAP, board, calliances, zordes, true, true, false, output)) {
                                    if (elf.move(elf.getCharacterId(), Integer.parseInt(steps[3]), Integer.parseInt(steps[2]), Constants.elfAP, board, calliances, zordes, true, false, false, output)) {
                                        if (elf.move(elf.getCharacterId(), Integer.parseInt(steps[5]), Integer.parseInt(steps[4]), Constants.elfAP, board, calliances, zordes, true, false, false, output)) {
                                            if (elf.move(elf.getCharacterId(), Integer.parseInt(steps[7]), Integer.parseInt(steps[6]), 15, board, calliances, zordes, true, false, true, output)) {
                                                Main.showBoard(board, board.length, output);
                                                Main.showWarriors(calliances, zordes, output);
                                            } else break;
                                        } else break;
                                    } else break;
                                } else break;
                            }
                        }
                    } else throw new MoveCountException();     // Exception throws if move count given incorrect

                } catch (MoveCountException moveCount) {
                    output.write("Error : Move sequence contains wrong number of move steps. Input line ignored.\n\n");
                }
            } else if (lineInfo[0].charAt(0) == 'D') {          // If the character is a dwarf
                try {
                    if (steps.length == 4) {
                        for (Calliance dwarf : calliances) {
                            if (dwarf.getCharacterId().equals(lineInfo[0])) {
                                if (dwarf.move(dwarf.getCharacterId(), Integer.parseInt(steps[1]), Integer.parseInt(steps[0]), Constants.dwarfAP, board, calliances, zordes, true, true, false, output)) {
                                    if (dwarf.move(dwarf.getCharacterId(), Integer.parseInt(steps[3]), Integer.parseInt(steps[2]), Constants.dwarfAP, board, calliances, zordes, true, false, false, output)) {
                                        Main.showBoard(board, board.length, output);
                                        Main.showWarriors(calliances, zordes, output);
                                    } else break;
                                } else break;
                            }
                        }
                    } else throw new MoveCountException();      // Exception throws if move count given incorrect
                } catch (MoveCountException moveCount) {
                    output.write("Error : Move sequence contains wrong number of move steps. Input line ignored.\n\n");
                }
            } else if (lineInfo[0].charAt(0) == 'O') {          // If the character is an ork
                try {
                    if (steps.length == 2) {
                        for (Zorde ork : zordes) {
                            if (ork.getCharacterId().equals(lineInfo[0])) {
                                if (ork.move(ork.getCharacterId(), Integer.parseInt(steps[1]), Integer.parseInt(steps[0]), Constants.orkAP, board, calliances, zordes, true, output)) {
                                    Main.showBoard(board, board.length, output);
                                    Main.showWarriors(calliances, zordes, output);
                                } else break;
                            }
                        }
                    } else throw new MoveCountException();      // Exception throws if move count given incorrect

                } catch (MoveCountException moveCount) {
                    output.write("Error : Move sequence contains wrong number of move steps. Input line ignored.\n\n");
                }
            } else if (lineInfo[0].charAt(0) == 'T') {          // If the character is a troll
                try {
                    if (steps.length == 2) {
                        for (Zorde troll : zordes) {
                            if (troll.getCharacterId().equals(lineInfo[0])) {
                                if (troll.move(troll.getCharacterId(), Integer.parseInt(steps[1]), Integer.parseInt(steps[0]), Constants.trollAP, board, calliances, zordes, true, output)) {
                                    Main.showBoard(board, board.length, output);
                                    Main.showWarriors(calliances, zordes, output);
                                } else break;
                            }
                        }
                    } else throw new MoveCountException();        // Exception throws if move count given incorrect
                } catch (MoveCountException moveCount) {
                    output.write("Error : Move sequence contains wrong number of move steps. Input line ignored.\n\n");
                }
            } else if (lineInfo[0].charAt(0) == 'G') {          // If the character is a goblin
                try {
                    if (steps.length == 8) {
                        for (Zorde goblin : zordes) {
                            if (goblin.getCharacterId().equals(lineInfo[0])) {
                                if (goblin.move(goblin.getCharacterId(), Integer.parseInt(steps[1]), Integer.parseInt(steps[0]), Constants.goblinAP, board, calliances, zordes, true, output)) {
                                    if (goblin.move(goblin.getCharacterId(), Integer.parseInt(steps[3]), Integer.parseInt(steps[2]), Constants.goblinAP, board, calliances, zordes, false, output)) {
                                        if (goblin.move(goblin.getCharacterId(), Integer.parseInt(steps[5]), Integer.parseInt(steps[4]), Constants.goblinAP, board, calliances, zordes, false, output)) {
                                            if (goblin.move(goblin.getCharacterId(), Integer.parseInt(steps[7]), Integer.parseInt(steps[6]), Constants.goblinAP, board, calliances, zordes, false, output)) {
                                                Main.showBoard(board, board.length, output);
                                                Main.showWarriors(calliances, zordes, output);
                                            } else break;
                                        } else break;
                                    } else break;
                                } else break;
                            }
                        }
                    } else throw new MoveCountException();        // Exception throws if move count given incorrect
                } catch (MoveCountException moveCount) {
                    output.write("Error : Move sequence contains wrong number of move steps. Input line ignored.\n\n");
                }
            }
            if (zordes.size() == 0) {
                output.write("\nGame Finished\n");
                output.write("Calliance Wins");
                return;
            } else if (calliances.size() == 0) {
                output.write("\nGame Finished\n");
                output.write("Zorde Wins");
                return;
            }
        }
    }
}
