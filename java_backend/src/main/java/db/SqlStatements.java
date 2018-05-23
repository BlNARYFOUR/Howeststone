package db;

public class SqlStatements {
    public static final String SELECT_DECKS =
            "SELECT deckName, heroId FROM Decks;";

    public static final String SELECT_HEROES =
            "SELECT * FROM howeststone.Decks;";

    public static final String SELECT_CARDS =
            "SELECT * FROM howeststone.Decks;";

    public static final String SELECT_DECK_ID =
            "SELECT deckId FROM howeststone.Decks WHERE deckName = ?";

    public static final String SELECT_HERO_ID =
            "SELECT heroId FROM howeststone.Heroes WHERE heroName = ?";

    public static final String SELECT_MECHANIC_ID =
            "SELECT mechanicId FROM howeststone.Mechanics WHERE mechanicType = ?";

    public static final String SELECT_ABILITY_ID =
            "SELECT abilityId FROM howeststone.Abilities WHERE abilityName = ?";

    public static final String SELECT_CARDS_IN_DECK =
            "SELECT cardId, amount FROM howeststone.CardsInDecks WHERE deckId = ?";

    public static final String SELECT_MECHANICS =
            "SELECT * FROM howeststone.Decks;";

    public static final String SELECT_AMOUNT_OF_CARDS_IN_DECK =
            "SELECT amount FROM howeststone.CardsInDecks WHERE deckId = ? AND cardId = ?";

    public static final String INSERT_DECK =
            "INSERT INTO `howeststone`.`Decks` (`deckName`, `heroId`) VALUES (?, ?);";

    public static final String INSERT_CARD_TO_DECK =
            "INSERT INTO `howeststone`.`CardsInDecks` (`deckId`, `cardId`, `amount`) VALUES (?, ?, ?);";

    public static final String INSERT_ABILITY =
            "INSERT INTO `howeststone`.`Abilities` (`abilityName`) VALUES (?)";

    public static final String INSERT_HERO =
            "INSERT INTO `howeststone`.`Heroes` (`heroName`) VALUES (?)";

    public static final String INSERT_MECHANIC =
            "INSERT INTO `howeststone`.`Mechanics` (`mechanicType`) VALUES (?)";

    public static final String INSERT_CARD_MECHANIC =
            "INSERT INTO `howeststone`.`CardMechanics` (`mechanicId`, `target`, `mechValue`) VALUES (?, ?, ?)";

    public static final String INSERT_CARD_ABILITY =
            "INSERT INTO `howeststone`.`CardAbilities` (`abilityId`, `cardId`, `cardMechId`) VALUES (?, ?, ?)";

    public static final String INSERT_CARD =
            "INSERT INTO `howeststone`.`Cards` (`cardName`, `cardType`,`race` ,`img`, `rarity`, `health`, `attack`, `manaCost`, `durability`, `heroId`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
}
