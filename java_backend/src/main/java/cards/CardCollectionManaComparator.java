package cards;

import java.util.Comparator;

public class CardCollectionManaComparator implements Comparator<Card> {
    @Override
    public int compare(Card a, Card b) {
        return b.getManaCost() - a.getManaCost();
    }
}
