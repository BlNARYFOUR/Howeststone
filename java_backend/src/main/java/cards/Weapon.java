package cards;

public class Weapon extends Card {
    private int attack;
    private int durability;

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

    public String getCardType (Card card) {
        return  "weapon";
    }


    // method attack
}
