package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardCollection {
    private List<Card> cards;

    public CardCollection() {
        this.cards = new ArrayList<Card>();
    }

    private void addCard(Card card){
        cards.add(card);
    }

    public int getLength(){
        return cards.size();
    }

    private void shuffleCardCollection(){
        // beneath or with parameter ?
        Collections.shuffle(cards);
    }
}
