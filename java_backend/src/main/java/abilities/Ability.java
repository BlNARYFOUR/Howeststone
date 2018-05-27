package abilities;

import cards.Card;
import events.Events;

public abstract class Ability {
    private final Abilities abilityType;
    private boolean used;

    public Ability(Abilities abilityType) {
        this.abilityType = abilityType;
        this.used = false;
    }

    public Abilities getAbilityType() {
        return abilityType;
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

    public abstract boolean executeAbility(Card self, Card target, Events event, int value);

    public abstract void initiateTurn(Card self);
}
