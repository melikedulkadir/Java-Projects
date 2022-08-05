public class Goblin extends Zorde{
    private int goblinHP = Constants.goblinMaxHP;
    private int goblinMaxHP = Constants.goblinMaxHP;

    public Goblin(String characterId){
        super(characterId);
    }

    public int getHP() {
        return goblinHP;
    }

    public void setHP(int goblinHP) {
        this.goblinHP = goblinHP;
    }

    public int getMaxHP() {
        return goblinMaxHP;
    }
}
