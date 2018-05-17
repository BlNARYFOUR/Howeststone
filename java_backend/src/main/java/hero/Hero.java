package hero;

import cards.CardCollection;

public class Hero {
    private int mageAttack;
    private int health;
    private String heroName;
    private int heroID;
    private int heroPowerID;

    public Hero(String heroName){
        this.health = 30;
        switch (heroName) {
            case "Mage":
                this.heroName = "Mage";
                this.heroPowerID = 0;
                this.mageAttack = 1;
                break;
            case "Paladin":
                this.heroName = "Paladin";
                this.heroPowerID = 1;
                break;
            default:
                throw new IllegalArgumentException("Hero only Paladin or Mage");
        }

    }

    @Override
    public String toString() {
        return heroName;
    }

    // method hasWeapon
    // method use heroPower
}
