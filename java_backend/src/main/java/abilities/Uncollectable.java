package abilities;

import cards.Card;
import events.Events;

public class Uncollectable extends Ability {

    public Uncollectable(Abilities abilityType) {
        super(abilityType);
    }

    @Override
    public boolean executeAbility(Card self, Card target, Events event, int value) {
        return false;
    }

    @Override
    public void initiateTurn(Card self) {

    }
}
