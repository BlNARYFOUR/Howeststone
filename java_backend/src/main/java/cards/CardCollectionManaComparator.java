package cards;

import java.io.Serializable;
import java.util.Comparator;

public class CardCollectionManaComparator implements Comparator<Card>, Serializable {
    @Override
    public int compare(Card a, Card b) {
        return b.getManaCost() - a.getManaCost();
    }
}
