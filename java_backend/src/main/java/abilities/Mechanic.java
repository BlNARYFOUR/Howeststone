package abilities;

public abstract class Mechanic {
    private final Mechanics mechanicType;

    public Mechanic(Mechanics mechanicType) {
        this.mechanicType = mechanicType;
    }

    public Mechanics getMechanicType() {
        return mechanicType;
    }
}


