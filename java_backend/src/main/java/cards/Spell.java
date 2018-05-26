package cards;

public class Spell extends Card {
    public Spell(int cardId, String cardName, String race, String urlOfImg, String rarity, int health, int attack, int manaCost, int durability, int heroId) {
        super(cardId, cardName, race, urlOfImg, rarity, health, attack, manaCost, durability, heroId);
    }

    public String getCardType (Card card) {
        return "spell";
    }
}
