package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardCollection {
    private String name;
    private List<Card> cards;

    public CardCollection() {
        this.cards = new ArrayList<Card>();
    }

    public CardCollection(String name) {
        this.cards = new ArrayList<Card>();
        this.name = name;
        /*if (name.equals("Standard")){
            for(int i = 0; i < 30; i ++){
                Card card = new Card();
                cards.add(card);
            }
        }else{
            // TODO check if deck exist
        }*/
    }

    private void shuffleCardCollection() {
        // beneath or with parameter ?
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        final Card DRAW = cards.get(1);
        cards.remove(1);
        return DRAW;
    }

    @Override
    public String toString() {
        final StringBuilder RES = new StringBuilder();
        RES.append(name);
        for (Card card : cards) {
            RES.append("\nID: ").append(card);
        }
        RES.append("\n").append(cards.size());
        return String.valueOf(RES);
    }
}
