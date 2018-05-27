package abilities;

import cards.Card;
import events.Events;

public class Windfury extends Ability {

    public Windfury(Abilities abilityType) {
        super(abilityType);
    }

    @Override
    public boolean executeAbility(Card self, Card target, Events event, int value) {
        if (super.isUseable() && self.getAmountAttacked() <= 1) {
            self.increaseMaxAmountOfAttacks();
            super.use();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void initiateTurn(Card self) {
        super.makeUseable();
    }
}
