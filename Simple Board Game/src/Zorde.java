import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Zorde extends Warrior {
    private int zordeHP;
    private int zordeMaxHP;

    public Zorde(String characterId) {
        super(characterId);
    }

    public int getHP() {
        return zordeHP;
    }

    public void setHP(int zordeHP) {
        this.zordeHP = zordeHP;
    }

    public int getMaxHP() {
        return zordeMaxHP;
    }

    // Move method for Zorde characters
    public boolean move(String characterId, int row, int column, int attackerAp, Warrior[][] board, ArrayList<Calliance> calliances, ArrayList<Zorde> zordes, boolean isFirst, FileWriter output) throws IOException {
        try {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] != null && board[i][j].getCharacterId().equals(characterId)) {
                        if (i + row < board.length && i + row >= 0 && j + column < board.length && j + column >= 0) {
                            // If the destination is empty
                            if (board[i + row][j + column] == null) {
                                board[i + row][j + column] = board[i][j];
                                board[i][j] = null;
                                checkNeighbours(i + row - 1, column + j - 1, board.length, attackerAp, board, calliances, true);
                                checkNeighbours(i + row - 1, column + j, board.length, attackerAp, board, calliances, true);
                                checkNeighbours(i + row - 1, column + j + 1, board.length, attackerAp, board, calliances, true);
                                checkNeighbours(i + row, column + j - 1, board.length, attackerAp, board, calliances, true);
                                checkNeighbours(i + row, column + j + 1, board.length, attackerAp, board, calliances, true);
                                checkNeighbours(i + row + 1, column + j - 1, board.length, attackerAp, board, calliances, true);
                                checkNeighbours(i + row + 1, column + j, board.length, attackerAp, board, calliances, true);
                                checkNeighbours(i + row + 1, column + j + 1, board.length, attackerAp, board, calliances, true);
                                return true;
                             // If the destination is an enemy character
                            } else if (board[i + row][j + column].toString().equals("Calliance")) {
                                if (board[i + row][j + column].getCharacterId().startsWith("H")) {
                                    Human enemy = (Human) board[i + row][j + column];
                                    defineZordeAttacker(i, j, row, column, board, enemy, calliances, zordes);
                                    Main.showBoard(board, board.length, output);
                                    Main.showWarriors(calliances, zordes, output);
                                    return false;
                                } else if (board[i + row][j + column].getCharacterId().startsWith("E")) {
                                    Elf enemy = (Elf) board[i + row][j + column];
                                    defineZordeAttacker(i, j, row, column, board, enemy, calliances, zordes);
                                    Main.showBoard(board, board.length, output);
                                    Main.showWarriors(calliances, zordes, output);
                                    return false;
                                } else if (board[i + row][j + column].getCharacterId().startsWith("D")) {
                                    Dwarf enemy = (Dwarf) board[i + row][j + column];
                                    defineZordeAttacker(i, j, row, column, board, enemy, calliances, zordes);
                                    Main.showBoard(board, board.length, output);
                                    Main.showWarriors(calliances, zordes, output);
                                    return false;
                                }
                            // If the destination is an ally character
                            } else if (board[i + row][j + column].toString().equals("Zorde")) {
                                Main.showBoard(board, board.length, output);
                                Main.showWarriors(calliances, zordes, output);
                                return false;
                            }
                        } else {
                            throw new BoundaryException();      // Exception throws if move command exceeded the board
                        }
                    }
                }
            }
        } catch (BoundaryException exceed) {
            if (!isFirst) {                     // If the move command is not the first command, board will be show
                Main.showBoard(board, board.length, output);
                Main.showWarriors(calliances, zordes, output);
            }
            output.write("Error : Game board boundaries are exceeded. Input line ignored.\n");
            output.write("\n");
            return false;
        }
        return true;
    }

    public String toString() {
        return "Zorde";
    }

    // Method for normal attack, and healing for ork characters
    public void checkNeighbours(int row, int column, int size, int attackerAp, Warrior[][] board, ArrayList<Calliance> calliances, boolean isAttack) {
        if (row < size && row >= 0 && column < size && column >= 0) {
            if (board[row][column] != null && board[row][column].toString().equals("Calliance") && isAttack) {  // Attack
                board[row][column].setHP(board[row][column].getHP() - attackerAp);
                if (board[row][column].getHP() <= 0) {
                    calliances.remove(board[row][column]);
                    board[row][column] = null;
                }
            } else if (board[row][column] != null && board[row][column].toString().equals("Zorde") && !isAttack) {  // Heal
                if (board[row][column].getCharacterId().startsWith("G")) {
                    board[row][column].setHP(board[row][column].getHP() + Constants.orkHealPoints);
                    if (board[row][column].getHP() > Constants.goblinMaxHP)
                        board[row][column].setHP(Constants.goblinMaxHP);
                } else if (board[row][column].getCharacterId().startsWith("T")) {
                    board[row][column].setHP(board[row][column].getHP() + Constants.orkHealPoints);
                    if (board[row][column].getHP() > Constants.trollMaxHP)
                        board[row][column].setHP(Constants.trollMaxHP);
                } else if (board[row][column].getCharacterId().startsWith("O")) {
                    board[row][column].setHP(board[row][column].getHP() + Constants.orkHealPoints);
                    if (board[row][column].getHP() > Constants.orkMaxHP)
                        board[row][column].setHP(Constants.orkMaxHP);
                }
            }
        }
    }
    // Method for define Zorde attacker
    public void defineZordeAttacker(int i, int j, int row, int column, Warrior[][] board, Calliance enemy, ArrayList<Calliance> calliances, ArrayList<Zorde> zordes) {
        if (board[i][j].getCharacterId().startsWith("O")) {
            Ork attacker = (Ork) board[i][j];
            fight(row, column, i, j, Constants.orkAP, attacker, enemy, board, calliances, zordes);
        } else if (board[i][j].getCharacterId().startsWith("T")) {
            Troll attacker = (Troll) board[i][j];
            fight(row, column, i, j, Constants.trollAP, attacker, enemy, board, calliances, zordes);
        } else if (board[i][j].getCharacterId().startsWith("G")) {
            Goblin attacker = (Goblin) board[i][j];
            fight(row, column, i, j, Constants.goblinAP, attacker, enemy, board, calliances, zordes);
        }
    }

    @Override
    public void fight(int row, int column, int i, int j, int attackerAP, Zorde warrior1, Calliance warrior2, Warrior[][] board, ArrayList<Calliance> calliances, ArrayList<Zorde> zordes) {
        warrior2.setHP(warrior2.getHP() - attackerAP);
        super.fight(row, column, i, j, attackerAP, warrior1, warrior2, board, calliances, zordes);
        if (warrior1.getHP() > warrior2.getHP()) {
            if (warrior2.getHP() > 0) {
                warrior1.setHP(warrior1.getHP() - warrior2.getHP());
            }
            board[i + row][j + column] = warrior1;
            board[i][j] = null;
            calliances.remove(warrior2);
        } else if (warrior1.getHP() < warrior2.getHP()) {
            if (warrior1.getHP() > 0) {
                warrior2.setHP(warrior2.getHP() - warrior1.getHP());
            }
            zordes.remove(warrior1);
            board[i][j] = null;
        }
    }
}
