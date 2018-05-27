package cards;

public class Minion extends Card {

    public Minion(int cardId, String[] strArgs, int[] intArgs) {
        super(cardId, strArgs, intArgs);
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

}
