package deckbuilder;/*
  Created by Bert on 16/05/2018.
  Have a nice day!
 */

import hero.Hero;
import be.howest.ti.threebeesandme.howeststone.db.DoThingsWithDb;

public class Deckbuilder {

    //private CardCollection allCards;
    private Hero selectedHero;
    private String[] filterOptions;
    private String sortOption;


    public void addCardToDeck() {
        // check if card isn't already in deck ==> if ! legendary card: check if there are 2 copies ==> ADD_CARD_COPY
        // INSERT_CARDSINDECK(deckId, cardId)
    }

    public void deleteCardToDeck() {
        // check number of copies
        // if number is 2 ==> DELETE_CARD_COPY
        // else DELETE_CARD
    }

    public void saveDeck() {
        // checkIfDeckIsFull()
        // insertDeck(deckName, getSelectedHero.getId);
        // INSERT_CARDSINDECK
    }

    public void showCardsOfHero(Hero selectedHero) {
        // for (Card card: allcards) {
        //      if ( ! card.getHeroId = selectedHero.getId) {
        //              zet card hidden
        //      }
        // }
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
            // openDeck()
            // resetFilters()
        } else {
            throw new IllegalArgumentException("Your current deck hasn't been saved yet!");
        }
    }

    private boolean deckIsSaved() {
        return true; //false
        //TODO
    }

    public Hero getSelectedHero() {
        return selectedHero;
    }
}