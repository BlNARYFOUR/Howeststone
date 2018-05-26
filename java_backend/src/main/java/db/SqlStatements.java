package db;

public class SqlStatements {
    public static final String SELECT_DECKS =
            "SELECT deckName, heroId FROM Decks;";

    public static final String SELECT_HEROES =
            "SELECT * FROM HOWESTSTONE.Decks;";

    public static final String SELECT_CARDS =
            "SELECT * FROM HOWESTSTONE.Decks;";

    public static final String SELECT_DECK_ID =
            "SELECT deckId FROM HOWESTSTONE.Decks WHERE deckName = ?";

    public static final String SELECT_HERO_ID =
            "SELECT heroId FROM HOWESTSTONE.Heroes WHERE heroName = ?";

    public static final String SELECT_MECHANIC_ID =
            "SELECT mechanicId FROM HOWESTSTONE.Mechanics WHERE mechanicType = ?";

    public static final String SELECT_ABILITY_ID =
            "SELECT abilityId FROM HOWESTSTONE.Abilities WHERE abilityName = ?";

    public static final String SELECT_CARDS_IN_DECK =
            "SELECT cardId, amount FROM HOWESTSTONE.CardsInDecks WHERE deckId = ?";

    public static final String SELECT_MECHANICS =
            "SELECT * FROM HOWESTSTONE.Decks;";

    public static final String IS_LEGENDARY =
            "SELECT rarity = \"Legendary\" AS `isLegendary` FROM cards WHERE cardId = ?";

    public static final String IS_UNCOLLECTABLE =
            "SELECT MAX(abilities.abilityName = \"Uncollectable\") AS `isUncollectable`"
                    + " FROM cards"
                    + " LEFT JOIN cardabilities ON cards.cardId=cardabilities.cardId"
                    + " JOIN abilities ON cardabilities.abilityId=abilities.abilityId"
                    + " WHERE cards.cardId = ?";

    public static final String SELECT_AMOUNT_OF_CARDS_IN_DECK =
            "SELECT amount FROM HOWESTSTONE.CardsInDecks WHERE deckId = ? AND cardId = ?";

    public static final String INSERT_DECK =
            "INSERT INTO `HOWESTSTONE`.`Decks` (`deckName`, `heroId`) VALUES (?, ?);";

    public static final String INSERT_CARD_TO_DECK =
            "INSERT INTO `HOWESTSTONE`.`CardsInDecks` (`deckId`, `cardId`, `amount`) VALUES (?, ?, ?);";

    public static final String INSERT_ABILITY =
            "INSERT INTO `HOWESTSTONE`.`Abilities` (`abilityName`) VALUES (?)";

    public static final String INSERT_HERO =
            "INSERT INTO `HOWESTSTONE`.`Heroes` (`heroName`) VALUES (?)";

    public static final String INSERT_MECHANIC =
            "INSERT INTO `HOWESTSTONE`.`Mechanics` (`mechanicType`) VALUES (?)";

    public static final String INSERT_CARD_MECHANIC =
            "INSERT INTO `HOWESTSTONE`.`CardMechanics` (`mechanicId`, `target`, `mechValue`) VALUES (?, ?, ?)";

    public static final String INSERT_CARD_ABILITY =
            "INSERT INTO `HOWESTSTONE`.`CardAbilities` (`abilityId`, `cardId`, `cardMechId`) VALUES (?, ?, ?)";

    public static final String INSERT_CARD =
            "INSERT INTO `HOWESTSTONE`.`Cards` (`cardName`, `cardType`,`race` ,`img`, `rarity`, `health`, `attack`, `manaCost`, `durability`, `heroId`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_AMOUNT_OF_CARD_IN_DECK =
            "UPDATE `HOWESTSTONE`.`CardsInDecks` SET amount = ? WHERE deckId = ? AND cardId = ?";

    public static final String GET_TOTAL_AMOUNT_OF_CARDS =
            "SELECT MAX(cardId) AS amountOfCards FROM cards";

    public static final String GET_HEROES =
            "SELECT * FROM heroes WHERE heroName != \"Neutral\"";

    public static final String GET_DECKS =
            "SELECT * FROM decks JOIN heroes ON decks.heroId = heroes.heroId WHERE heroName = ?";

    public static final String GET_CARD_IN_DECK = "SELECT cards.*, cardsindecks.amount" +
            " FROM decks" +
            " JOIN cardsindecks ON cardsindecks.deckId=decks.deckId" +
            " JOIN cards ON cards.cardId=  cardsindecks.cardId" +
            " WHERE deckName = ?";


    public static final String GET_ABILITIES_OF_CARD = "SELECT cardabilities.abilityId, cardabilities.cardMechId, abilities.abilityName, cardmechanics.target, cardmechanics.mechValue, mechanics.mechanicType" +
            " FROM cardabilities" +
            " JOIN abilities ON cardabilities.abilityId=abilities.abilityId" +
            " JOIN cardmechanics ON cardmechanics.cardMechId=cardabilities.cardMechId" +
            " JOIN mechanics ON mechanics.mechanicId=cardmechanics.mechanicId" +
            " WHERE cardId = ?";
    public static final String GET_CARDS = "SELECT *" +
            " FROM cards";
    public static final String FILTER_CARDS = "SELECT *" +
            " FROM  cards" +
            " JOIN heroes ON cards.heroId = heroes.heroId" +
            " WHERE  heroes.heroName LIKE ? AND cards.cardType LIKE ? AND cards.manaCost LIKE ? AND cards.rarity LIKE ?";
}
