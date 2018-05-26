package cards;

import abilities.Abilities;
import hero.Hero;
import java.util.ArrayList;
import java.util.List;

// Een kaart exhausted = true altijd enkel false als het op het speelveld ligt aan het begin van de beurt. (er zijn uitzonderingen)
public abstract class Card {

    private int maxAmountOfAttacks;
    private boolean exhausted;
    private int amountAttacked;

    private int cardID;
    private String type;
    private String cardName;
    private String img;
    private String rarity;
    private int manaCost;
    protected int attack;
    protected int health;
    protected int durability;
    private int heroId;
    private String race;
    private List<Abilities> cardAbilities;
    // TODO: private List<Mechanics> cardMechanics;

    /* TODO private List<Abilities> cardAbilities;
    private List<Mechanics> cardMechanics;
    */

    public Card(int cardId, String type, String cardName, String race, String urlOfImg, String rarity, int health, int attack, int manaCost, int durability, int heroId) {
        this.cardID = cardId;
        this.type = type;
        this.cardName = cardName;
        this.race = race;
        this.img = urlOfImg;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Card(int cardID){
        this.cardID = cardID;
        // TODO get other cardSpecifications
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
        return "Card{" +
                "cardID=" + cardID +
                ", type=" + type +
                ", cardName='" + cardName  +
                ", img='" + img +
                ", rarity='" + rarity  +
                ", manaCost=" + manaCost +
                ", health=" + health +
                ", race='" + race  +
                ", attack=" + attack +
                ", durability=" + durability +
                ", heroId=" + heroId +
                '}';
    }

    public void attack(Card card) {
        if (!this.isExhausted()) {
            this.doDamage(card);
        }
    }

    public void doDamage(Card target) {
        target.setHealth(target.health-this.attack);
        this.setHealth(this.health-target.attack);
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

    public void attackHero(Hero target) {
        target.setHealth(target.getHealth()-this.attack);
        if (target.getWeapon() != null) {
            this.setHealth(this.getHealth()-target.getWeapon().attack);
        }
        //TODO voer abilities uit bv enrage
    }

    public boolean isDead() {
        return health <= 0;
    }

    // list with all abilities and mechanics?
    // method playCard


}
