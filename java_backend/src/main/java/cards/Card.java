package cards;

import java.util.List;

// Een kaart exhausted = true altijd enkel false als het op het speelveld ligt aan het begin van de beurt. (er zijn uitzonderingen)
public abstract class Card {

    private int maxAmountOfAttacks;
    private boolean exhausted;
    private int amountAttacked;

    private int cardID;
    private String cardName;
    private String urlOfImg;
    private String rarity;
    private int manaCost;
    protected int health;
    private String race;
    protected int attack;
    protected int durability;
    private int heroId;

    /* TODO private List<Abilities> cardAbilities;
    private List<Mechanics> cardMechanics;
    */

    public Card(int cardId, String cardName, String race, String urlOfImg, String rarity, int health, int attack, int manaCost, int durability, int heroId) {
        this.cardID = cardId;
        this.cardName = cardName;
        this.race = race;
        this.urlOfImg = urlOfImg;
        this.rarity = rarity;
        this.health = health;
        this.attack = attack;
        this.manaCost = manaCost;
        this.durability = durability;
        this.heroId = heroId;

        // PREDEFINED
        this.exhausted = true;
        this.amountAttacked = 0;
        this.maxAmountOfAttacks = 1;
    }

    public int getManaCost() {
        return manaCost;
    }

    /* TODO public List<Abilities> getCardAbilities() {
        return cardAbilities;
    }*/

    public int getMaxAmountOfAttacks() {
        return maxAmountOfAttacks;
    }

    public boolean isExhausted() {
        return exhausted;
    }

    public void setExhausted(boolean exhausted) {
        this.exhausted = exhausted;
    }

    public int getCardID() {
        return cardID;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardID=" + cardID +
                ", cardName='" + cardName  +
                ", urlOfImg='" + urlOfImg  +
                ", rarity='" + rarity  +
                ", manaCost=" + manaCost +
                ", health=" + health +
                ", race='" + race  +
                ", attack=" + attack +
                ", durability=" + durability +
                ", heroId=" + heroId +
                '}';
    }

    // method attack
    // boolean returns whether you can attack
    public boolean attack(Card card) {
        // TODO: card.doDamage();

        return false;
    }

    public void awaken() {
        exhausted = false;
    }

    public int getAmountAttacked() {
        return amountAttacked;
    }

    public void increaseMaxAmountOfAttacks() {
        maxAmountOfAttacks++;
    }

    public void increaseAmountAttacked() {
        amountAttacked++;
    }

    public void addHealth(int health) {
        this.health += health;
    }


    // list with all abilities and mechanics?
    // method playCard


}
