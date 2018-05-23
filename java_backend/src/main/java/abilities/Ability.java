package abilities;

import cards.Card;

public abstract class Ability {
    private final Abilities ABILITY_TYPE;
    private boolean used;

    public Ability(Abilities ABILITY_TYPE) {
        this.ABILITY_TYPE = ABILITY_TYPE;
        this.used = false;
    }

    public Abilities getABILITY_TYPE() {
        return ABILITY_TYPE;
    }

    public void use() {
        used = true;
    }

    public void makeUseable() {
        used = false;
    }

    public boolean isUseable() {
        return !used;
    }

    public abstract boolean executeAbility(Card self, Card target);

    public abstract void initiateTurn(Card self);
}
