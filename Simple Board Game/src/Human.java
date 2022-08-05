public class Human extends Calliance {
    private int humanHP = Constants.humanMaxHP;
    private int humanMaxHp = Constants.humanMaxHP;

    public Human(String characterId) {
        super(characterId);
    }

    public int getHP() {
        return humanHP;
    }

    public void setHP(int humanHP) {
        this.humanHP = humanHP;
    }

    public int getMaxHP() {
        return humanMaxHp;
    }
}
