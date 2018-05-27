package cards;

public class Weapon extends Card {

    public Weapon(int cardId, String[] strArgs, int[] intArgs) {
        super(cardId, strArgs, intArgs);
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    // method attack
}
