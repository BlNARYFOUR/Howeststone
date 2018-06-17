package game;

import cards.Card;
import cards.CardCollection;
import formatters.ColorFormats;
import hero.Hero;

import java.util.List;

public class Player {
    private int heroDamageOnEmptyDeck;
    private Hero hero;
    private CardCollection cardsInHand;
    private CardCollection cardsOnPlayingField;
    private CardCollection cardsInDeck;
    private int activeMana;
    private int totalMana;
    private Card weapon;

    public Player() {
        heroDamageOnEmptyDeck = 1;
        hero = new Hero("Mage");
        cardsInHand = new CardCollection();
        cardsOnPlayingField = new CardCollection();
        cardsInDeck = new CardCollection();
        activeMana = 0;
        totalMana = 0;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public void setHero(String heroName) {
        this.hero = new Hero(heroName);
    }

    public CardCollection getCardsInHand() {
        return cardsInHand;
    }

    public CardCollection getCardsOnPlayingField() {
        return cardsOnPlayingField;
    }

    public int getTotalMana() {
        return totalMana;
    }

    public void setTotalMana(int totalMana) {
        this.totalMana = totalMana;
    }

    public Card getWeapon() {
        return weapon;
    }

    public void setWeapon(Card weapon) {
        this.weapon = weapon;
    }

    public void beginTurn() {
        if (totalMana < 10) {
            totalMana++;
        }

        activeMana = totalMana;

        final Card card = cardsInDeck.drawCard();

        if (card != null) {
            if(cardsInHand.size() < 10)
                cardsInHand.addCard(card);
        } else {
            hero.addHealth(-heroDamageOnEmptyDeck++);
        }
    }

    public void setDeck(CardCollection deckName) {
        cardsInDeck = new CardCollection(deckName);

    }

    public CardCollection getDeck() {
        return cardsInDeck;
    }

    public void setCardsInHand(CardCollection cardsInHand) {
        this.cardsInHand = cardsInHand;
    }

    public void setCardsInHand(List<String> cardsInHandList) {
        cardsInHand = new CardCollection();
        // TODO cardsInHand.addCards(cardsInHandList);
    }

    public boolean playCard(int cardID) {
        boolean succeeded = false;

        try {
            final int manaCost = cardsInHand.getCard(cardID).getManaCost();
            final String type = cardsInHand.getCard(cardID).getType();

            if (manaCost <= getActiveMana()) {
                switch (type) {
                    case "Minion":
                        cardsOnPlayingField.addCard(cardsInHand.getCard(cardID));
                        break;
                    case "Weapon":
                        // TODO
                        final boolean fixError = true;
                        break;
                    default:
                        // TODO
                        final boolean fixError2 = true;
                        break;
                }

                cardsInHand.removeCard(cardsInHand.getCard(cardID));
                activeMana -= manaCost;

                succeeded = true;
            }
        } catch (IllegalArgumentException error) {
            System.out.println(ColorFormats.red("Thou shall not hack!"));
            return false;
        }

        return succeeded;
    }


    public Card getRandomTargetMinion() {
        final List<Card> targetCards = this.getCardsOnPlayingField().getCards();
        final int resultIndex = (int) Math.round(Math.random()) * (targetCards.size() - 1);

        return this.getCardsOnPlayingField().getCards().get(resultIndex);

    }

    @Override
    public String toString() {
        // deck hero
        // mss nog extra zoals health totalMana
        return "Hero: " + hero.getHeroName() + "\nDeck: " + cardsInDeck.getName();
    }

    public int getActiveMana() {
        return activeMana;
    }

    public void resetMana() {
        activeMana = 0;
        totalMana = 0;
    }
}
