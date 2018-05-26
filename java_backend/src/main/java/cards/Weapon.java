package cards;

public class Weapon extends Card {
    private int attack;
    private int durability;

    public Weapon(int cardId, String type, String cardName, String race, String urlOfImg, String rarity, int health, int attack, int manaCost, int durability, int heroId) {
        super(cardId, type, cardName, race, urlOfImg, rarity, health, attack, manaCost, durability, heroId);
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

    public String getCardType (Card card) {
        return  "weapon";
    }


    // method attack
}
