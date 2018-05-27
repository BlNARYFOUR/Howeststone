package abilities;

import cards.Card;
import events.Events;

public class Charge extends Ability {

    public Charge(Abilities abilityType) {
        super(abilityType);
    }

    @Override
    public boolean executeAbility(Card self, Card target, Events event, int value) {
        if (super.isUseable()) {
            self.awaken();
            super.use();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void initiateTurn(Card self) {
        //Charge can only be used one turn!
    }
}
