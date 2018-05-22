package db;

public class SqlStatements {
    public static final String SELECT_DECKS =
            "SELECT deckName, heroId FROM Decks;";

    public static final String SELECT_HEROES =
            "SELECT * FROM .Decks;";

    public static final String SELECT_CARDS =
            "SELECT * FROM howeststone.Decks;";

    public static final String SELECT_CARDSINDECK =
            "SELECT * FROM howeststone.Decks;";

    public static final String SELECT_MECHANICS =
            "SELECT * FROM howeststone.Decks;";

    public static final String INSERT_DECK =
            "INSERT INTO `howeststone`.`Decks` (`deckName`, `heroId`) VALUES (?, ?);";

    public static final String INSERT_CARDS_IN_DECK =
            "INSERT INTO `howeststone`.`CardsInDecks` (`deckId`, `cardId`) VALUES (?, ?);";

    public static final String INSERT_ABILITY =
            "INSERT INTO `howeststone`.`Abilities` (`abilityName`) VALUES (?)";
}
