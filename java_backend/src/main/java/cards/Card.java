package cards;

import java.util.ArrayList;
import java.util.List;

public class Card {
    private static int mockID;
    private int cardID;
    private String cardName;
    private String urlOfImg;
    private String rarity;
    private int manaCost;
    private List<Abilities> cardAbilities;
    private boolean canAttack = false;
    // TODO: private List<Mechanics> cardMechanics;

    public Card(){
        // cannot be all cards
        this.cardID = mockID;
        mockID ++;
        getOtherInfo(cardID);
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

    public Card(int cardID){
        this.cardID = cardID;
        getOtherInfo(cardID);
        // TODO get other cardSpecifications
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

    public boolean getCanAttack() {
        return canAttack;
    }


    // list with all abilities and mechanics?
    // method playCard


}
