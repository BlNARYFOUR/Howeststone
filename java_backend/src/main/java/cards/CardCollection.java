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

    private void shuffleCardCollection(){
        // beneath or with parameter ?
        Collections.shuffle(cards);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(name);
        for (Card card : cards) {
            res.append("\nID: ").append(card);
        }
        res.append("\n").append(cards.size());
        return String.valueOf(res);
    }
}
