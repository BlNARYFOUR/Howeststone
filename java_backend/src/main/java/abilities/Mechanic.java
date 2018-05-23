package abilities;

import cards.Card;

public abstract class Mechanic {
    private final Mechanics MECHANIC_TYPE;

    public Mechanic(Mechanics MECHANIC_TYPE) {
        this.MECHANIC_TYPE = MECHANIC_TYPE;
    }

    public Mechanics getMECHANIC_TYPE() {
        return MECHANIC_TYPE;
    }
    ;
}


