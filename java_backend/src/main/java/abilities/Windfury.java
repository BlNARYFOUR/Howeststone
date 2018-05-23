package abilities;

import cards.Card;

public class Windfury extends Ability {

    public Windfury(Abilities ABILITY_TYPE) {
        super(ABILITY_TYPE);
    }

    @Override
    public boolean executeAbility(Card self, Card target) {
        return false;
    }

    @Override
    public void initiateTurn(Card self) {

    }
}
