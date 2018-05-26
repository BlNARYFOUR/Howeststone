package cards;

import db.SqlDatabase;
import db.SqlStatements;
import formatters.ColorFormats;
import hero.Hero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        this.cards = new ArrayList<>();
        this.name = name;
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        final Card DRAW = cards.get(1);
        cards.remove(1);
        return DRAW;
    }

    public void addCard(Card card) {
        cards.add(card);
        shuffle();
    }

    public boolean hasCard(int cardId) {
        for (Card card : cards) {
            if (card.getcardId() == cardId) {
                return true;
            }
        }

        return false;
    }

    public void removeCard(int cardId) {
        //Card card = new Card(cardId);
        //cards.remove(card);
    }

    @Override
    public String toString() {
        String str = "";

        for (Card card : cards) {
            str += "\n" + card;
        }

        return "CardCollection{" +
                "name='" + name + '\'' +
                ", cards=" + str +
                '}';
    }

    public String getNameOfCardCollection() {
        return name;
    }

    public Card getCard(int cardId) {
        for (Card card : cards) {
            if (card.getcardId() == cardId) {
                return card;
            }
        }

        throw new IllegalArgumentException("Card not found.");
    }

    public List<Card> getCards() {
        return cards;
    }
    /*
    public Card getCheapestCard() {
        Card cheapestCard = this.cards.get(0);
        for(Card x : this.cards ){
            if (x.getManaCost() < cheapestCard.getManaCost()) {
                cheapestCard = x;
            }
        }
        return cheapestCard;
    }*/

    public Card getMostExpensiveCard() {
        Card mostExpensiveCard = this.cards.get(0);
        for (Card x : this.cards) {
            if (x.getManaCost() > mostExpensiveCard.getManaCost()) {
                mostExpensiveCard = x;
            }
        }
        return mostExpensiveCard;
    }

    public CardCollection getSubCollection(List<Integer> cardIds) {
        CardCollection subCardCollection = new CardCollection();
        for (Card card : cards) {
            if (cardIds.contains(card.getcardId())) {
                subCardCollection.addCard(card);
            }
        }
        return subCardCollection;
    }

    public String checkIfCardCanBeAddedToDeck(String body) {
        int cardId = Integer.parseInt(body);
        int amount = 0;
        String rarity = "";
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getcardId() == cardId) {
                amount++;
                rarity = cards.get(i).getRarity();
                // TODO uncollectable
            }
        }
        if (amount >= 2) {
            return "cannot add more";
        }

        if (cards.size() > 29) {
            return "too much cards";
        }
        if (amount >= 1 && rarity.equals("Legendary")) {
            return "cannot add more";
        }
        return "SUCCES";
    }
}
