package game;

import cards.Card;
import cards.CardCollection;
import formatters.ColorFormats;
import hero.Hero;

import java.util.List;

public class Player {
    private Hero hero;
    private CardCollection cardsInHand;
    private CardCollection cardsOnPlayingField;
    private CardCollection cardsInDeck;
    private int activeMana;
    private int totalMana;
    private Card weapon;

    public Player() {
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

    public void setHero(String heroName){
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

    public Player() {

    }

    public void beginTurn() {
        // +1 totalMana until 10
        // draw card
        totalMana++;
        activeMana = totalMana;
        cardsInHand.addCard(cardsInDeck.drawCard());
    }

    public void setDeck(CardCollection deckName) {
        cardsInDeck = deckName;
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

    public boolean playCard(int cardId) {
        try {
            int manaCost = cardsInHand.getCard(cardId).getManaCost();

            if (manaCost <= getActiveMana()) {
                cardsOnPlayingField.addCard(cardsInHand.getCard(cardId));
                cardsInHand.removeCard(cardId);
                activeMana -= manaCost;

                return true;
            } else {
                return false;
            }
        } catch (IllegalArgumentException error) {
            System.out.println(ColorFormats.red("Thou shall not hack!"));
            return false;
        }
    }


    public Card getRandomTargetMinion() {
        List<Card> targetCards = this.getCardsOnPlayingField().getCards();
        int resultIndex = (int)Math.round(Math.random())*(targetCards.size()-1);

        return this.getCardsOnPlayingField().getCards().get(resultIndex);

    }

    @Override
    public String toString() {
        // deck hero
        // mss nog extra zoals health totalMana
        return "Hero: " + hero.getHeroName() + "\nDeck: " +cardsInDeck.getNameOfCardCollection();
    }

    public int getActiveMana() {
        return activeMana;
    }
}
