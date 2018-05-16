package cards;

public class Minion extends Card{
    private int attack;
    private int health;
    private int howManyTimesThisTurnCanIStillAttack;

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHowManyTimesThisTurnCanIStillAttack() {
        return howManyTimesThisTurnCanIStillAttack;
    }

    public void setHowManyTimesThisTurnCanIStillAttack(int howManyTimesThisTurnCanIStillAttack) {
        this.howManyTimesThisTurnCanIStillAttack = howManyTimesThisTurnCanIStillAttack;
    }

    // method attack
}
