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
        if (name.equals("Standard")){
            for(int i = 0; i < 30; i ++){
                Card card = new Card();
                cards.add(card);
            }
            // underneath or function in game shuffleDecks() or both?
            shuffle();
        }
    }
    private void shuffle(){
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        final Card DRAW = cards.get(1);
        cards.remove(1);
        return DRAW;
    }
    
    public void addCards(List<String> replace) {
        for (String cardID : replace) {
            addCard(Integer.parseInt(cardID));
        }
    }

    private void addCard(int cardID) {
        Card card = new Card(cardID);
        cards.add(card);
        shuffle();
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

    public String getNameOfCardCollection() {
        return name;
    }

}
