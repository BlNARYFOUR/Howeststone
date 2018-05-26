package cards;

import java.util.Comparator;

public class alfazCardCollectionComparator implements Comparator<Card> {

    @Override
    public int compare(Card a, Card b) {
        return a.getCardName().compareTo(b.getCardName());
    }
}
