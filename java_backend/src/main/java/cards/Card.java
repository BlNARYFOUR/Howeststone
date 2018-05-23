package cards;

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


    // list with all abilities and mechanics?
    // method playCard


}
