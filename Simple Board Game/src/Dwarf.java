public class Dwarf extends Calliance {
    private int dwarfHP = Constants.dwarfMaxHP;
    private int dwarfMaxHP = Constants.dwarfMaxHP;

    public Dwarf(String characterId) {
        super(characterId);
    }

    public int getHP() {
        return dwarfHP;
    }

    public void setHP(int dwarfHP) {
        this.dwarfHP = dwarfHP;
    }

    public int getMaxHP() {
        return dwarfMaxHP;
    }
}
