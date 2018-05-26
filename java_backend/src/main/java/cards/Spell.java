package cards;

public class Spell extends Card {
    public Spell(int cardId, String type, String cardName, String race, String urlOfImg, String rarity, int health, int attack, int manaCost, int durability, int heroId) {
        super(cardId, type, cardName, race, urlOfImg, rarity, health, attack, manaCost, durability, heroId);
    }

    public String getCardType (Card card) {
        return "spell";
    }
}
