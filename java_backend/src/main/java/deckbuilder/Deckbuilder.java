package deckbuilder;

import hero.Hero;

public class Deckbuilder {

    //private CardCollection allCards;
    private Hero selectedHero;
    private String[] filterOptions;
    private String sortOption;


    public void addCardToDeck() {

    }

    public void deleteCardToDeck() {

    }

    public void saveDeck() {
        // checkIfDeckIsFull()
        // INSERT_DECK(deckName, getSelectedHero.getId)
        // INSERT_CARDSINDECK
        //TODO
    }

    public void showCardsOfHero(Hero selectedHero) {
        //TODO
    }

    public void filterCards() {
        //TODO
    }

    public void sortCards() {
        //TODO
    }

    public void searchCards() {
        //TODO
    }

    public void switchDeck() {
        if (deckIsSaved()) {
            //TODO
            final boolean fixError = true;
        } else {
            throw new IllegalArgumentException("Your current deck hasn't been saved yet!");
        }
    }

    private boolean deckIsSaved() {
        return true;
        //TODO
    }

    public Hero getSelectedHero() {
        return selectedHero;
    }
}
