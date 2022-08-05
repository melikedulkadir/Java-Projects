public class Troll extends Zorde{
    private int trollHP = Constants.trollMaxHP;
    private int trollMaxHP = Constants.trollMaxHP;

    public Troll(String characterId){
        super(characterId);
    }

    public int getHP() {
        return trollHP;
    }

    public void setHP(int trollHP) {
        this.trollHP = trollHP;
    }

    public int getMaxHP() {
        return trollMaxHP;
    }
}
