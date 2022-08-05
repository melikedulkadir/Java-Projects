public class Elf extends Calliance {
    private int elfHP = Constants.elfMaxHP;
    private int elfMaxHp = Constants.elfMaxHP;

    public Elf(String characterId) {
        super(characterId);
    }

    public int getHP() {
        return elfHP;
    }

    public void setHP(int elfHP) {
        this.elfHP = elfHP;
    }

    public int getMaxHP() {
        return elfMaxHp;
    }
}
