package hero;

import cards.CardCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Hero {
    private int mageAttack;
    private int health;
    private String heroName;
    private int heroID;
    private int heroPowerID;

    public Hero(String heroName){
        this.health = 30;
        switch (heroName) {
            case "mage":
                this.heroName = "mage";
                this.heroPowerID = 0;
                this.mageAttack = 1;
                break;
            case "paladin":
                this.heroName = "paladin";
                this.heroPowerID = 1;
                break;
            default:
                throw new IllegalArgumentException("Hero only paladin or mage");
        }

    }

    public String getHeroName() {
        return heroName;
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

    public void executeHeroPower(int heroPowerId) {
        // check For Two Mana;
        // heroIdPower  = Paladin ==> check field is full ==> add 1/1 minion to field
        // heroIdPower = Mage ==> frontend activeren om target te selecteren ==> levens target in backend -1
        // check if dead here? or with function of lives decrease
    }

    public List<String> getDeckNames() {
        List<String> mocked = new ArrayList<String>();

        mocked.add("Standard");
        mocked.add("Deck 1");
        mocked.add("Deck 2");

        return mocked;
    }
}