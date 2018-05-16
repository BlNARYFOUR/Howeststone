package deck;

import cards.CardCollection;

public class Deck {
    CardCollection cards;

    public Deck(String deckName, CardCollection cards) {
        if (cards.getLength() == 30){
            // TODO yey
        } else {
            throw new IllegalArgumentException("deck not full");
        }
    }
}
