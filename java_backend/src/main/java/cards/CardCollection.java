package cards;

import abilities.Ability;

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

    public CardCollection(CardCollection cardCollection) {
        this(cardCollection.getName());
        this.cards = new ArrayList<>(cardCollection.getCards());
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (0 < cards.size()) {
            final Card draw = cards.get(0);
            cards.remove(0);
            return draw;
        }

        return null;
    }

    public void addCard(Card card) {
        cards.add(card);
        shuffle();
    }

    public boolean hasCard(int cardId) {
        for (Card card : cards) {
            if (card.getCardID() == cardId) {
                return true;
            }
        }

        return false;
    }

    public void removeCard(int cardID) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getCardID() == cardID) {
                cards.remove(i);
                break;
                // if card is 2 times in cards, it will only be deleted once
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();

        for (Card card : cards) {
            stringBuilder.append('\n');
            stringBuilder.append(card);
        }

        return "CardCollection{"
                + "name='" + name + '\''
                + ", cards=" + stringBuilder.toString()
                + '}';
    }

    public String getName() {
        return name;
    }

    public Card getCard(int cardId) {
        for (Card card : cards) {
            if (card.getCardID() == cardId) {
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
        if (0 < this.cards.size()) {
            Card mostExpensiveCard = this.cards.get(0);
            for (Card x : this.cards) {
                if (x.getManaCost() > mostExpensiveCard.getManaCost()) {
                    mostExpensiveCard = x;
                }
            }
            return mostExpensiveCard;
        } else {
            return null;
        }
    }

    public boolean checkIfCardCanAttack(int cardID) {
        boolean check = true;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getCardID() == cardID) {
                if (!cards.get(i).isExhausted()) {
                    check = false;
                }
                if ((cards.get(i).getAmountAttacked() < cards.get(i).getMaxAmountOfAttacks())) {
                    check = false;
                }
            }
        }
        return true;
    }
}
