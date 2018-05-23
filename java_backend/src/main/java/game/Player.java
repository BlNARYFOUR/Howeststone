package game;

import cards.Card;
import cards.CardCollection;
import hero.Hero;

import java.util.List;

public class Player {
    private Hero hero;
    private CardCollection cardsInHand;
    private CardCollection cardsOnPlayingField;
    private CardCollection cardsInDeck;
    private int mana = 0;

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

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public Player() {

    }

    public void beginTurn() {

        // +1 mana until 10
        // draw card
    }

    @Override
    public String toString() {
        // deck hero
        // mss nog extra zoals health mana
        return "Hero: " + hero.getHeroName() + "\nDeck: " +cardsInDeck.getNameOfCardCollection();
    }

    public void setDeck(String deckName) {
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
        cardsInHand.addCards(cardsInHandList);
    }

    /*public Card getRandomTarget() {
        //TODO hoe zorg ik dat ik een kaart OF de hero kan teruggeven?

        List<Card> targetCards = this.getCardsOnPlayingField().getCards();
        int resultIndex = (int)Math.round(Math.random())*(targetCards.size());

        if (resultIndex == targetCards.size()) {
            //dan is het de Hero
        } else {
            return this.getCardsOnPlayingField().getCards().get(resultIndex);
        }

    }*/
}
