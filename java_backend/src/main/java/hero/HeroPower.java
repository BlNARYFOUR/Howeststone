package hero;

public class HeroPower {
    private int heroPowerId;
    private String heroPowerName;
    private int manaCost;
    private int attack;

    /*public HeroPower(int heroPowerId, String heroPowerName, int manaCost, int attack) {
        this.heroPowerId = heroPowerId;
        this.heroPowerName = heroPowerName;
        this.manaCost = manaCost;
        this.attack = attack;
    }

    public HeroPower(int heroPowerId, String heroPowerName, int manaCost) {
        this(heroPowerId, heroPowerName, manaCost, 0);
    }*/

    public void executeHeroPower(int heroPowerId) {
        // check For Two Mana;
        // heroIdPower  = Paladin ==> check field is full ==> add 1/1 minion to field
        // heroIdPower = Mage ==> frontend activeren om target te selecteren ==> levens target in backend -1
        // check if dead here? or with function of lives decrease
    }
}