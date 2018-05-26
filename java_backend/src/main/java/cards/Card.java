package cards;

import abilities.Abilities;
import abilities.Ability;

import java.util.ArrayList;
import java.util.List;

// Een kaart exhausted = true altijd enkel false als het op het speelveld ligt aan het begin van de beurt. (er zijn uitzonderingen)
public abstract class Card {

    private int maxAmountOfAttacks;
    private boolean exhausted;
    private int amountAttacked;

    private int cardId;
    private String cardName;
    private String img;
    private String rarity;
    private int manaCost;
    protected int health;
    private String race;
    protected int attack;
    protected int durability;
    private int heroId;
    private List<Ability> cardAbilities;
    /* TODO private List<Abilities> cardAbilities;
    private List<Mechanics> cardMechanics;
    */

    public Card(int cardId, String cardName, String race, String img, String rarity, int health, int attack, int manaCost, int durability, int heroId) {
        this.cardId = cardId;
        this.cardName = cardName;
        this.race = race;
        this.img = img;
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

    public int getcardId() {
        return cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", cardName='" + cardName  +
                ", img='" + img  +
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

    public String getImg() {
        return img;
    }

    // list with all abilities and mechanics?
    // method playCard

}
