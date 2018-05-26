package cards;

public class Minion extends Card {

    public Minion(int cardId, String type, String cardName, String race, String urlOfImg, String rarity, int health, int attack, int manaCost, int durability, int heroId) {
        super(cardId, type, cardName, race, urlOfImg, rarity, health, attack, manaCost, durability, heroId);
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

    public String getCardType (Card card) {
        return "minion";
    }

}
