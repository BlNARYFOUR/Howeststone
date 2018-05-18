package cards; /*
  Created by Bert on 18/05/2018.
  Have a nice day!
 */

import java.util.ArrayList;
import java.util.Collections;

public class CardCollection {
    private ArrayList<Card> cardsInDeck;

    public Card getRandomCardFromDeck(int max) {
        int randomIndex = generateRandomIndexOfCardsInDeck(max);
        return cardsInDeck.get(randomIndex);
    }

    private int generateRandomIndexOfCardsInDeck(int max)
    {
        int range = (max) + 1;
        return (int)(Math.random() * range);
    }

    public void shuffleDeck() {
        Collections.shuffle(cardsInDeck);
    }

    public Card getFirstCard() {
        Card result = cardsInDeck.get(0);
        cardsInDeck.remove(0);
        return result;
    }
}