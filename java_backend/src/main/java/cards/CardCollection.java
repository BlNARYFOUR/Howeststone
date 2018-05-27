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
        shuffle();
        if (0 < cards.size()) {
            final Card draw = cards.get(0);
            cards.remove(0);
            return draw;
        }

        return null;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public boolean hasCard(int cardID) {
        for (Card card : cards) {
            if (card.getCardID() == cardID) {
                return true;
            }
        }
        return false;
    }

    /* TODO blublublu
    public void removeCard(int cardID) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getCardID()() == cardID) {
                cards.remove(i);
                break;
                // if card is 2 times in cards, it will only be deleted once
            }
        }
    }*/

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

    public void setName(String name) {
        this.name = name;
    }

    public Card getCard(int cardID) {
        for (Card card : cards) {
            if (card.getCardID() == cardID) {
                return card;
            }
        }
        throw new IllegalArgumentException("Card not found.");
    }

    public List<Card> getCards() {
        return cards;
    }

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

    public CardCollection getSubCollection(List<Integer> cardIDs) {
        final CardCollection subCardCollection = new CardCollection();
        for (Card card : cards) {
            for (int i : cardIDs) {
                if (card.getCardID() == i) {
                    subCardCollection.addCard(card);
                }
            }
        }
        return subCardCollection;
    }

    public String checkIfCardCanBeAddedToDeck(String body) {
        final String result;
        final int cardID = Integer.parseInt(body);
        int amount = 0;
        String rarity = "";
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getCardID() == cardID) {
                amount++;
                rarity = cards.get(i).getRarity();
                // TODO uncollectable
            }
        }
        if (("Legendary".equals(rarity) && amount >= 1) || amount >= 2) {
            result = "too much cards";
        } else if (cards.size() > 29) {
            result = "cannot add more";
        } else {
            result = "SUCCES";
        }
        return result;
    }

    public CardCollection sortDeck(String body) {
        switch (body) {
            case "alfaz":
                this.getCards().sort(new CardCollectionAlphabeticalComparator());
                break;

            case "alfza":
                this.getCards().sort(new CardCollectionAlphabeticalComparator());
                Collections.reverse(this.getCards());
                break;

            case "mana07":
                this.getCards().sort(new CardCollectionManaComparator());
                Collections.reverse(this.getCards());
                break;

            case "mana70":
                this.getCards().sort(new CardCollectionManaComparator());
                break;
            default:
                throw new IllegalArgumentException("no sort methods where found");
        }
        return this;
    }
}
