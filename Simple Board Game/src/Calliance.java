import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Calliance extends Warrior {
    private int callianceHp;
    private int callianceMaxHP;

    public Calliance(String characterId) {
        super(characterId);
    }

    public String toString() {
        return "Calliance";
    }

    public int getHP() {
        return callianceHp;
    }

    public void setHP(int callianceHp) {
        this.callianceHp = callianceHp;
    }

    public int getMaxHP() {
        return callianceMaxHP;
    }

    // Move method for Calliance characters
    public boolean move(String characterId, int row, int column, int attackerAp, Warrior[][] board, ArrayList<Calliance> calliances, ArrayList<Zorde> zordes, boolean isAttack, boolean isFirst, boolean rangeAttack, FileWriter output) throws IOException {
        try {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] != null && board[i][j].getCharacterId().equals(characterId)) {
                        if (i + row < board.length && i + row >= 0 && j + column < board.length && j + column >= 0) {
                            // If the destination is empty
                            if (board[i + row][j + column] == null) {
                                board[i + row][j + column] = board[i][j];
                                board[i][j] = null;
                                // If the character will attack
                                if (isAttack) {
                                    checkNeighbours(i + row - 1, column + j - 1, board.length, attackerAp, board, zordes);
                                    checkNeighbours(i + row - 1, column + j, board.length, attackerAp, board, zordes);
                                    checkNeighbours(i + row - 1, column + j + 1, board.length, attackerAp, board, zordes);
                                    checkNeighbours(i + row, column + j - 1, board.length, attackerAp, board, zordes);
                                    checkNeighbours(i + row, column + j + 1, board.length, attackerAp, board, zordes);
                                    checkNeighbours(i + row + 1, column + j - 1, board.length, attackerAp, board, zordes);
                                    checkNeighbours(i + row + 1, column + j, board.length, attackerAp, board, zordes);
                                    checkNeighbours(i + row + 1, column + j + 1, board.length, attackerAp, board, zordes);
                                    if (rangeAttack) {
                                        checkNeighbours(i + row - 2, column + j - 2, board.length, attackerAp, board, zordes);
                                        checkNeighbours(i + row - 2, column + j - 1, board.length, attackerAp, board, zordes);
                                        checkNeighbours(i + row - 2, column + j, board.length, attackerAp, board, zordes);
                                        checkNeighbours(i + row - 2, column + j + 1, board.length, attackerAp, board, zordes);
                                        checkNeighbours(i + row - 2, column + j + 2, board.length, attackerAp, board, zordes);

                                        checkNeighbours(i + row - 1, column + j - 2, board.length, attackerAp, board, zordes);
                                        checkNeighbours(i + row - 1, column + j + 2, board.length, attackerAp, board, zordes);

                                        checkNeighbours(i + row, column + j - 2, board.length, attackerAp, board, zordes);
                                        checkNeighbours(i + row, column + j + 2, board.length, attackerAp, board, zordes);

                                        checkNeighbours(i + row + 1, column + j - 2, board.length, attackerAp, board, zordes);
                                        checkNeighbours(i + row + 1, column + j + 2, board.length, attackerAp, board, zordes);

                                        checkNeighbours(i + row + 2, column + j - 2, board.length, attackerAp, board, zordes);
                                        checkNeighbours(i + row + 2, column + j - 1, board.length, attackerAp, board, zordes);
                                        checkNeighbours(i + row + 2, column + j, board.length, attackerAp, board, zordes);
                                        checkNeighbours(i + row + 2, column + j + 1, board.length, attackerAp, board, zordes);
                                        checkNeighbours(i + row + 2, column + j + 2, board.length, attackerAp, board, zordes);
                                    }
                                }
                                return true;
                            // If the destination is an enemy character
                            } else if (board[i + row][j + column].toString().equals("Zorde")) {
                                if (board[i + row][j + column].getCharacterId().startsWith("O")) {
                                    Ork enemy = (Ork) board[i + row][j + column];
                                    defineCallianceAttacker(i, j, row, column, board, enemy, calliances, zordes);
                                    Main.showBoard(board, board.length, output);
                                    Main.showWarriors(calliances, zordes, output);
                                    return false;
                                } else if (board[i + row][j + column].getCharacterId().startsWith("T")) {
                                    Troll enemy = (Troll) board[i + row][j + column];
                                    defineCallianceAttacker(i, j, row, column, board, enemy, calliances, zordes);
                                    Main.showBoard(board, board.length, output);
                                    Main.showWarriors(calliances, zordes, output);
                                    return false;
                                } else if (board[i + row][j + column].getCharacterId().startsWith("G")) {
                                    Goblin enemy = (Goblin) board[i + row][j + column];
                                    defineCallianceAttacker(i, j, row, column, board, enemy, calliances, zordes);
                                    Main.showBoard(board, board.length, output);
                                    Main.showWarriors(calliances, zordes, output);
                                    return false;
                                }
                            // If the destination is an ally character
                            } else if (board[i + row][j + column].toString().equals("Calliance")) {
                                Main.showBoard(board, board.length, output);
                                Main.showWarriors(calliances, zordes, output);
                                return false;
                            }
                        } else {
                            throw new BoundaryException();      //// Exception throws if move command exceeded the board
                        }
                    }
                }
            }
        } catch (BoundaryException exceed) {

            if (!isFirst) {
                Main.showBoard(board, board.length, output);
                Main.showWarriors(calliances, zordes, output);
            }
            output.write("Error : Game board boundaries are exceeded. Input line ignored.\n");
            output.write("\n");
            return false;
        }
        return true;
    }

    // Method for normal attack
    public void checkNeighbours(int row, int column, int size, int attackerAp, Warrior[][] board, ArrayList<Zorde> zordes) {
        if (row < size && row >= 0 && column < size && column >= 0) {
            if (board[row][column] != null && board[row][column].toString().equals("Zorde")) {
                board[row][column].setHP(board[row][column].getHP() - attackerAp);
                if (board[row][column].getHP() <= 0) {
                    zordes.remove(board[row][column]);
                    board[row][column] = null;
                }
            }
        }
    }

    // Method for define Calliance attacker
    public void defineCallianceAttacker(int i, int j, int row, int column, Warrior[][] board, Zorde enemy, ArrayList<Calliance> calliances, ArrayList<Zorde> zordes) {
        if (board[i][j].getCharacterId().startsWith("H")) {
            Human attacker = (Human) board[i][j];
            fight(row, column, i, j, Constants.humanAP, enemy, attacker, board, calliances, zordes);
        } else if (board[i][j].getCharacterId().startsWith("E")) {
            Elf attacker = (Elf) board[i][j];
            fight(row, column, i, j, Constants.elfAP, enemy, attacker, board, calliances, zordes);
        } else if (board[i][j].getCharacterId().startsWith("D")) {
            Dwarf attacker = (Dwarf) board[i][j];
            fight(row, column, i, j, Constants.dwarfAP, enemy, attacker, board, calliances, zordes);
        }
    }

    @Override
    public void fight(int row, int column, int i, int j, int attackerAP, Zorde warrior1, Calliance warrior2, Warrior[][] board, ArrayList<Calliance> calliances, ArrayList<Zorde> zordes) {
        warrior1.setHP(warrior1.getHP() - attackerAP);
        super.fight(row, column, i, j, attackerAP, warrior1, warrior2, board, calliances, zordes);

        if (warrior2.getHP() > warrior1.getHP()) {
            if (warrior1.getHP() > 0) {
                warrior2.setHP(warrior2.getHP() - warrior1.getHP());
            }
            board[i + row][j + column] = warrior2;
            board[i][j] = null;
            zordes.remove(warrior1);
        } else if (warrior2.getHP() < warrior1.getHP()) {
            if (warrior2.getHP() > 0) {
                warrior1.setHP(warrior1.getHP() - warrior2.getHP());
            }
            calliances.remove(warrior2);
            board[i][j] = null;
        }
    }
}
