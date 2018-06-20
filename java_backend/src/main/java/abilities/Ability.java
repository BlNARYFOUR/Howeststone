package abilities;

import cards.Card;
import events.Events;

import java.util.Objects;

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

    @Override
    public String toString() {
        return this.getClass().getName().substring(10);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Ability ability = (Ability) o;
        return abilityType == ability.abilityType;
    }

    @Override
    public int hashCode() {

        return Objects.hash(abilityType);
    }
}
