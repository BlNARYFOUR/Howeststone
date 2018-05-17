package hero; /*
  Created by Bert on 16/05/2018.
  Have a nice day!
 */

import cards.Card;

public class Hero {
    private int health;
    private String heroName;
    private int heroId;
    private int heroPowerId;
    private int attack;

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

    public void heroAttack(Card weapon) {
        // damage target
        // eventueel damage hero
        // -1 durability weapon
        // if durability == 0 ==> make weapon go away
    }

    public void executeHeroPower(int heroPowerId) {
        // check For Two Mana;
        // heroIdPower  = Paladin ==> check field is full ==> add 1/1 minion to field
        // heroIdPower = Mage ==> frontend activeren om target te selecteren ==> levens target in backend -1
        // check if dead here? or with function of lives decrease
    }

}