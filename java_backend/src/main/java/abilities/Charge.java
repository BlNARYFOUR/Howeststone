package abilities;

import cards.Card;

public class Charge extends Ability {
    private boolean exhausted = false;

    public Charge() {

    }

    @Override
    public boolean executeAbility(Card self, Card target) {
        return true;
    }
}
