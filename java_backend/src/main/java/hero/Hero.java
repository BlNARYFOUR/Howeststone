package hero;

import cards.Weapon;

public class Hero {
    private final String paladinStr = "Paladin";
    private final String mageStr = "Mage";

    private int mageAttack;
    private int health;
    private String heroName;
    private int heroId;
    private int heroPowerID;
    private Weapon weapon;

    public Hero(String heroName) {
        this.health = 30;
        switch (heroName) {
            case mageStr:
                this.heroName = mageStr;
                this.heroId = 1;
                this.heroPowerID = 0;
                this.mageAttack = 1;
                break;
            case paladinStr:
                this.heroName = paladinStr;
                this.heroId = 3;
                this.heroPowerID = 1;
                break;
            default:
                throw new IllegalArgumentException("Hero only paladin or mage");
        }

    }

    public String getHeroName() {
        return heroName;
    }

    public int getHeroId() {
        return heroId;
    }

    public int getMageAttack() {
        return mageAttack;
    }

    public int getHeroPowerID() {
        return heroPowerID;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return heroName;
    }

    // method hasWeapon
    // method use heroPower
    /*public Hero(int health, String heroName, int heroId, int heroPowerId, int attack) {
        this.health = health;
        this.heroName = heroName;
        this.heroId = heroId;
        this.heroPowerId = heroPowerId;
        this.attack = attack;
    }

    public Hero(int health, String heroName, int heroId, int heroPowerId) {
        this(health, heroName, heroId, heroPowerId, 0);
    }*/

    public void executeHeroPower(int heroPowerId, Object randomTarget) {
        if (heroPowerId == 0) {
            // dan is het mage
            // TODO
            final boolean getErrorAway = true;
        }
        // heroIdPower  = Paladin ==> check field is full ==> add 1/1 minion to field
        // heroIdPower = Mage ==> frontend activeren om target te selecteren ==> levens target in backend -1
        // check if dead here? or with function of lives decrease
    }
}
