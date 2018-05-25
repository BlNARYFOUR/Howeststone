package cards;

import hero.Hero;

import java.util.ArrayList;
import java.util.List;
// Een kaart exhausted = true altijd enkel false als het op het speelveld ligt aan het begin van de beurt. (er zijn uitzonderingen)
public class Card {
    private static int mockID;

    private int maxAmountOfAttacks;
    private boolean exhausted;
    private int cardID;
    private String cardName;
    private String urlOfImg;
    private String rarity;
    private int manaCost;
    private int health;
    private int attack;
    private List<Abilities> cardAbilities;
    private int amountAttacked;
    // TODO: private List<Mechanics> cardMechanics;

    public Card(){
        // cannot be all cards
        this.cardID = mockID;
        mockID ++;
        getOtherInfo(cardID);
        this.exhausted = true;
        this.amountAttacked = 0;
        this.maxAmountOfAttacks = 1;
    }

    private void getOtherInfo(int mockID) {
        this.cardName = "mock";
        this.urlOfImg = "http://wow.zamimg.com/images/hearthstone/cards/enus/original/GAME_002.png";
        this.rarity = "epic";
        this.manaCost = 0;
        this.cardAbilities = new ArrayList<>();
        cardAbilities.add(new Abilities());
    }

    public int getManaCost() {
        return manaCost;
    }

    public List<Abilities> getCardAbilities() {
        return cardAbilities;
    }

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
    public String toString() {
        return String.valueOf(cardID);
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

    public void attackHero(Hero target) {
        target.setHealth(target.getHealth()-this.attack);
    }

    // list with all abilities and mechanics?
    // method playCard


}
