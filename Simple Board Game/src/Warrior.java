import java.util.ArrayList;

public class Warrior {
    private String characterId;
    private int warriorHP;
    private int warriorMaxHP;

    public Warrior(String characterId){
        this.characterId = characterId;
    }

    public String getCharacterId() {
        return characterId;
    }

    public int getHP() {
        return warriorHP;
    }

    public void setHP(int warriorHP) {
        this.warriorHP = warriorHP;
    }

    public int getMaxHP() {
        return warriorMaxHP;
    }

    // Method for a case where two characters fight
    public void fight(int row, int column, int i, int j, int attackerAP, Zorde warrior1, Calliance warrior2, Warrior[][] board, ArrayList<Calliance> calliances, ArrayList<Zorde> zordes) {

        if (warrior1.getHP() == warrior2.getHP()) {     // If two warriors have same HP value they both die
            board[i + row][j + column] = null;
            board[i][j] = null;
            calliances.remove(warrior2);
            zordes.remove(warrior1);
        }
    }
}
