package cards;

import java.util.Collections;
import java.util.List;

public class CardCollection {
    private List<Card> cards;

    private void shuffleCardCollection(){
        // beneath or with parameter ?
        Collections.shuffle(cards);
    }
}
