package hero;

public class Hero {
    private int mageAttack;
    private int health;
    private String heroName;
    private int heroID;
    private int heroPowerID;

    public Hero(String heroName){
        this.health = 30;
        if(heroName.equals("Mage")){
            this.heroName = "Mage";
            this.heroPowerID = 0;
            this.mageAttack = 1;
        }if(heroName.equals("Paladin")){
            this.heroName = "Paladin";
            this.heroPowerID = 1;
        }

    }
// method hasWeapon
    // method use heroPower
}
