package abilities;

public class Ability {
    private final Abilities ABILITY_TYPE;

    public Ability(Abilities ABILITY_TYPE) {
        this.ABILITY_TYPE = ABILITY_TYPE;
    }

    public Abilities getABILITY_TYPE() {
        return ABILITY_TYPE;
    }

    public boolean executeAbility(Card self, Card target, Game );
}
