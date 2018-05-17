package cards;

import java.util.List;

public class Card {
    private int cardID;
    private String cardName;
    private String urlOfImg;
    private String rarity;
    private int manaCost;
    private List<Abilities> cardAbilities;
    private List<Mechanics> cardMechanics;

    public Card(){
        // TODO random card
    }
    public Card(int cardID){
        // TODO get other cardSpecifications
    }
    public String toString() {
        return String.valueOf(cardID);
    }



    // list with all abilities and mechanics?
    // method playCard


}
