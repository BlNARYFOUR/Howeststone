package cards;

import abilities.*;
import java.util.*;
import hero.Hero;

// Een kaart exhausted = true altijd enkel false als het op het speelveld ligt aan het begin van de beurt.
public abstract class Card {

    protected int attack;
    protected int health;
    protected int durability;

    private int maxAmountOfAttacks;
    private boolean exhausted;
    private int amountAttacked;

    // TODO blublublu
    private int cardID;
    private String type;
    private String cardName;
    private String img;
    private String rarity;
    private int manaCost;
    private int heroId;
    private String race;
    private List<Abilities> cardAbilities;
    // TODO: private List<Mechanics> cardMechanics;

    public Card(int cardId, String[] strArgs, int[] intArgs) {
        this.cardID = cardId;
        this.type = strArgs[0];
        this.cardName = strArgs[1];
        this.race = strArgs[2];
        this.img = strArgs[3];
        this.rarity = strArgs[4];
        this.health = intArgs[0];
        this.attack = intArgs[1];
        this.manaCost = intArgs[2];
        this.durability = intArgs[3];
        this.heroId = intArgs[4];

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

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCardID() {
        return cardID;
    }

    public String getType() {
        return type;
    }

    public String getRarity() {
        return rarity;
    }

    @Override
    public String toString() {
        return "Card{"
                + "cardID=" + cardID
                + ", type=" + type
                + ", cardName='" + cardName
                + ", img='" + img
                + ", rarity='" + rarity
                + ", manaCost=" + manaCost
                + ", health=" + health
                + ", race='" + race
                + ", attack=" + attack
                + ", durability=" + durability
                + ", heroId=" + heroId
                + '}';
    }

    public void attack(Card card) {
        if (!this.isExhausted()) {
            this.doDamage(card);
        }
    }

    public void doDamage(Card target) {
        target.setHealth(target.health - this.attack);
        this.setHealth(this.health - target.attack);
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

    public int getHeroId() {
        return heroId;
    }

    public void attackHero(Hero target) {
        target.setHealth(target.getHealth() - this.attack);
        if (target.getWeapon() != null) {
            this.setHealth(this.getHealth() - target.getWeapon().attack);
        }
        //TODO voer abilities uit bv enrage
    }

    public boolean isDead() {
        return health <= 0;
    }

    // list with all abilities and mechanics?
    // method playCard

}
