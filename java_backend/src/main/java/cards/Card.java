package cards;

import hero.Hero;
import java.util.ArrayList;
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
    protected int attack;
    protected int health;
    protected int durability;
    private int heroId;
    private String race;
    private List<Abilities> cardAbilities;
    private int amountAttacked;
    // TODO: private List<Mechanics> cardMechanics;

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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Card(int cardID){
        this.cardID = cardID;
        getOtherInfo(cardID);
        // TODO get other cardSpecifications
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

    public String getCardType () {
        return "-1";
    }
    public String getUrlOfImg() {
        return urlOfImg;
    }

    public void attackHero(Hero target) {
        target.setHealth(target.getHealth()-this.attack);
    }

    // list with all abilities and mechanics?
    // method playCard


}
