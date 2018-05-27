package abilities;

import cards.Card;
import events.Events;

public class DivineShield extends Ability {

    public DivineShield(Abilities abilityType) {
        super(abilityType);
    }

    // In value comes the damage the card would 've taken.
    @Override
    public boolean executeAbility(Card self, Card target, Events event, int value) {
        if (super.isUseable() && event == Events.ON_DAMAGE) {
            self.addHealth(value);
            super.use();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void initiateTurn(Card self) {

    }
}
