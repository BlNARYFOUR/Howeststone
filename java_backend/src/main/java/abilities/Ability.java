package abilities;

import cards.Card;

public abstract class Ability {
    private final Abilities ABILITY_TYPE;

    public Ability(Abilities ABILITY_TYPE) {
        this.ABILITY_TYPE = ABILITY_TYPE;
    }

    public Abilities getABILITY_TYPE() {
        return ABILITY_TYPE;
    }

    public abstract boolean executeAbility(Card self, Card target);
}
