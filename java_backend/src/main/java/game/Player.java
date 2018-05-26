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
    private Card weapon;

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

    public Card getWeapon() {
        return weapon;
    }

    public void setWeapon(Card weapon) {
        this.weapon = weapon;
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

    public void playCard(Card card) {
        String cardType = card.getCardType();
        switch (cardType) {
            case "minion":
                if (this.cardsOnPlayingField.getCards().size() < 7) {
                    this.cardsInHand.removeCard(card.getCardID());
                    this.cardsOnPlayingField.addCard(card);
                    //TODO execute ability;
                }
                break;
            case "spell":
                //TODO this.executeSpell(card);
                this.cardsInHand.removeCard(card.getCardID());
                break;
            case "weapon":
                this.cardsInHand.removeCard(card.getCardID());
                this.setWeapon(card);
                break;
        }
    }

    public Card getRandomTargetMinion() {
        List<Card> targetCards = this.getCardsOnPlayingField().getCards();
        int resultIndex = (int)Math.round(Math.random())*(targetCards.size()-1);

        return this.getCardsOnPlayingField().getCards().get(resultIndex);

    }

}
