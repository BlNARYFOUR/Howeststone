package cards;

public class Card {
    private int cardID;
    private String cardName;
    private String urlOfImg;
    private String rarity;
    private int manaCost;

    public Card() {
        // TODO random card
        this.cardID = 1;
    }

    public String toString() {
        return String.valueOf(cardID);
    }

    // list with all abilities and mechanics?
    // method playCard


}
