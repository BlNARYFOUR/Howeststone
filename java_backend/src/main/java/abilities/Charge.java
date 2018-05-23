package abilities;

import cards.Card;

public class Charge extends Ability {

    public Charge(Abilities ABILITY_TYPE) {
        super(ABILITY_TYPE);
    }

    @Override
    public boolean executeAbility(Card self, Card target) {
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

    }
}
