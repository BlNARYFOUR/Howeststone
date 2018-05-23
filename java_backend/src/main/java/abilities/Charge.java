package abilities;

import cards.Card;

public class Charge extends Ability {
    private boolean used;

    public Charge(Abilities ABILITY_TYPE) {
        super(ABILITY_TYPE);
        this.used = false;
    }

    @Override
    public boolean executeAbility(Card self, Card target) {
        if (!used) {
            self.awaken();
            used = true;
            return true;
        } else {
            return false;
        }
    }
}
